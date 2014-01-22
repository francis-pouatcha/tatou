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

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class Delivery extends GpaoBaseEntity {

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

	@Override
	public String toString(){
		return reference+" : "+docRef ;

	}
	
	public boolean isOpen(){
		return DocumentStates.OUVERT.equals(status);
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
	
	public boolean hasDeliveryItem(){
		return !DeliveryItems.findDeliveryItemsesByDelivery(this).getResultList().isEmpty();
	}
	
	public static TypedQuery<cm.adorsys.gpao.model.Delivery> findDeliveryByStatusAndOriginAndReceiveDateBetween(DocumentStates status,
			String reference,Date beginReceivedDate,Date endReceivedDate,String docRef,String receiveBy,DeliveryOrigin origin) {
		EntityManager em = Delivery.entityManager();
		StringBuilder query = new StringBuilder("SELECT o FROM Delivery AS o WHERE  o.id IS NOT NULL ");
		if(StringUtils.isNotBlank(reference)){
			query.append(" AND o.reference = :reference");
		}
		if(StringUtils.isNotBlank(docRef)){
			query.append(" AND o.docRef = :docRef");
		}
		if(StringUtils.isNotBlank(receiveBy)){
			query.append(" AND o.receiveBy = :receiveBy");
		}
		if(origin != null){
			query.append(" AND o.origin = :origin");
		}
		if(!(status ==null || DocumentStates.TOUS.equals(status))){
			query.append(" AND o.status = :status");
		}
		if(beginReceivedDate != null){
			query.append(" AND o.receivedDate >= :beginReceivedDate");
		}
		if(endReceivedDate != null){
			query.append(" AND o.receivedDate <= :endReceivedDate");
		}
		TypedQuery<Delivery> q = em.createQuery(query.append(" ORDER BY o.id ").toString(), Delivery.class);
		if(StringUtils.isNotBlank(reference))q.setParameter("reference", reference);
		if(StringUtils.isNotBlank(receiveBy))q.setParameter("receiveBy", receiveBy);
		if(StringUtils.isNotBlank(docRef))q.setParameter("docRef", docRef);
		if(!(status ==null || DocumentStates.TOUS.equals(status)))q.setParameter("status", status);
		if(origin != null)q.setParameter("origin", origin);
		if(beginReceivedDate != null)q.setParameter("receivedDate", beginReceivedDate);
		if(endReceivedDate != null)q.setParameter("receivedDate", endReceivedDate);
		return q;
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
