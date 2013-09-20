package cm.adorsys.gpao.model;

import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Delivery {

	private String reference;

	private String createBy;

	private String receiveBy;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date receivedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "M-")
	private Date createdate = new Date();

	@NotNull
	@Enumerated(EnumType.STRING)
	private DeliveryOrigin origin;

	@Enumerated(EnumType.STRING)
	private DocumentStates status = DocumentStates.OUVERT;

	private BigDecimal unTaxeAmount = BigDecimal.ZERO;

	private BigDecimal taxAmount  = BigDecimal.ZERO;

	private BigDecimal taxedAmount  = BigDecimal.ZERO;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "delivery")
	private Set<DeliveryItems> deliveryItems = new HashSet<DeliveryItems>();

	@NotNull
	@ManyToOne
	private Devise currency;

	@NotNull
	@ManyToOne
	private Company company;

	@NotNull
	private String docRef;

	public Delivery() {

	}
	public Delivery( Company company) {
		this.company = Company.findCompany(Long.valueOf(1));
		currency = company.getDevise();

	}



	public Delivery(PurchaseOrder order) {
		this.createBy = SecurityUtil.getUserName();
		this.createdate = new Date();
		this.origin = DeliveryOrigin.ACHAT;
		this.status = DocumentStates.OUVERT;
		this.currency = order.getCurrency();
		this.company = order.getCompany();
		this.docRef = order.getReference();
		initAmount();
	}

	@PostPersist
	public void postPersist() {
		reference =GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.DELIVERY_SEQUENCE_PREFIX);
		createBy = SecurityUtil.getUserName();
	}

	public void initAmount() {
		unTaxeAmount = BigDecimal.ZERO;
		taxAmount = BigDecimal.ZERO;
		taxedAmount = BigDecimal.ZERO;

	}
	public void increaseAmountFromDeliveryItem(DeliveryItems deliveryItem) {
		unTaxeAmount = unTaxeAmount.add(deliveryItem.getAmountHt());
		taxAmount = taxAmount.add(deliveryItem.getTaxAmount());
		taxedAmount = taxedAmount.add(deliveryItem.getTaxedAmount());
	}

	public static TypedQuery<cm.adorsys.gpao.model.Delivery> findDeliverysByIdUpperThan(Long id) {
		EntityManager em = Delivery.entityManager();
		TypedQuery<Delivery> q = em.createQuery("SELECT o FROM Delivery AS o WHERE  o.id > :id ORDER BY o.id ", Delivery.class);
		q.setParameter("id", id);
		return q;
	}
	
	public static TypedQuery<cm.adorsys.gpao.model.Delivery> findDeliverysByReferenceEquals(String reference) {
		EntityManager em = Delivery.entityManager();
		TypedQuery<Delivery> q = em.createQuery("SELECT o FROM Delivery AS o WHERE  o.reference = :reference ", Delivery.class);
		q.setParameter("reference", reference);
		return q;
	}

	public static TypedQuery<cm.adorsys.gpao.model.Delivery> findDeliverysByIdLowerThan(Long id) {
		EntityManager em = Delivery.entityManager();
		TypedQuery<Delivery> q = em.createQuery("SELECT o FROM Delivery AS o WHERE  o.id < :id ORDER BY o.id DESC ", Delivery.class);
		q.setParameter("id", id);
		return q;
	}
	public static TypedQuery<cm.adorsys.gpao.model.Delivery> findDeliverysByDocRef(String docRef) {
		EntityManager em = Delivery.entityManager();
		TypedQuery<Delivery> q = em.createQuery("SELECT o FROM Delivery AS o WHERE  o.docRef = :docRef ORDER BY o.id DESC ", Delivery.class);
		q.setParameter("docRef", docRef);
		return q;
	}
}
