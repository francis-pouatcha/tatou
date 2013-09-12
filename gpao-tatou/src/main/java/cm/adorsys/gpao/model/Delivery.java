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

	@NotNull
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

	@Enumerated
	private DocumentStates status;

	private BigDecimal unTaxeAmount = BigDecimal.ZERO;

	@Min(0L)
	private BigDecimal taxAmount;

	@Min(0L)
	private BigDecimal taxedAmount;

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
		this.company = Company.findCompany(Long.valueOf(1));
		currency = company.getDevise();
		createBy = SecurityUtil.getUserName();

	}

	public Delivery(PurchaseOrder order) {
		this.createBy = SecurityUtil.getUserName();
		this.createdate = new Date();
		this.origin = DeliveryOrigin.PORCHASE;
		this.status = DocumentStates.OPENED;
		this.currency = order.getCurrency();
		this.company = order.getCompany();
		this.docRef = order.getReference();
		initAmount();
	}

	@PostPersist
	public void postPersist() {
		reference =GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.DELIVERY_SEQUENCE_PREFIX);
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

	public static TypedQuery<cm.adorsys.gpao.model.Delivery> findDeliverysByIdLowerThan(Long id) {
		EntityManager em = Delivery.entityManager();
		TypedQuery<Delivery> q = em.createQuery("SELECT o FROM Delivery AS o WHERE  o.id < :id ORDER BY o.id DESC ", Delivery.class);
		q.setParameter("id", id);
		return q;
	}
}
