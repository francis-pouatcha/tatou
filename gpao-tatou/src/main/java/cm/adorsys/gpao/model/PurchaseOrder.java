package cm.adorsys.gpao.model;

import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class PurchaseOrder {

    @NotNull
    private String reference;

    @NotNull
    @ManyToOne
    private Partner supplier;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date orderDate;

    @Value("false")
    private Boolean received;

    @Value("false")
    private Boolean invoiced;

    @Value("false")
    private Boolean isValided;

    private String validatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date validateDate;

    @Min(0L)
    private BigDecimal amountHt = BigDecimal.ZERO;

    @Min(0L)
    private BigDecimal taxeAmount = BigDecimal.ZERO;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private DocumentStates orderState = DocumentStates.BROULLON;

    private String createdBy;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();

    @NotNull
    @ManyToOne
    private Devise currency;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder" ,orphanRemoval=true)
    private Set<OrderItems> orderItems = new HashSet<OrderItems>();

    @NotNull
    @ManyToOne
    private Company company;

    @ManyToOne
    private Tenders tender;

    public void initAmount() {
        amountHt = BigDecimal.ZERO;
        taxeAmount = BigDecimal.ZERO;
        totalAmount = BigDecimal.ZERO;
    }
    
    public boolean isClosed(){
    	return DocumentStates.FERMER.equals(orderState);
    }
    
    public boolean hasTender(){
    	return (tender != null) ;
    }

    @PostPersist
    public void postPersist() {
        reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.PORCHASE_SEQUENCE_PREFIX);
        createdBy = SecurityUtil.getUserName();
    }

    public void increaseAmountFromOrderItem(OrderItems orderItem) {
        amountHt = amountHt.add(orderItem.getSubTotal());
        taxeAmount = taxeAmount.add(orderItem.getTaxeAmount());
        totalAmount = totalAmount.add(orderItem.getTaxedSubTotal());
    }

    public OrderItems hasProduct(Product product) {
        Set<OrderItems> orderItems2 = getOrderItems();
        for (OrderItems orderItems : orderItems2) {
            if (orderItems.getProduct().equals(product)) return orderItems;
        }
        return null;
    }

    public static TypedQuery<cm.adorsys.gpao.model.PurchaseOrder> findPurchaseOrdersByIdUpperThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE  o.id > :id ORDER BY o.id ", PurchaseOrder.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.PurchaseOrder> findPurchaseOrdersByIdLowerThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE  o.id < :id ORDER BY o.id DESC ", PurchaseOrder.class);
        q.setParameter("id", id);
        return q;
    }
    
    public static TypedQuery<cm.adorsys.gpao.model.PurchaseOrder> findPurchaseOrdersByTenderAndStatus(Tenders tenders, DocumentStates orderState) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE  o.tender = :tender AND o.orderState = :orderState ", PurchaseOrder.class);
        q.setParameter("tender", tenders);
        q.setParameter("orderState", orderState);
        return q;
    }
    public static TypedQuery<cm.adorsys.gpao.model.PurchaseOrder> findPurchaseOrdersByTender(Tenders tenders) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE  o.tender = :tender", PurchaseOrder.class);
        q.setParameter("tender", tenders);
        return q;
    }
    public static TypedQuery<cm.adorsys.gpao.model.PurchaseOrder> findPurchaseOrderByReferenceEquals(String reference) {
		EntityManager em = PurchaseOrder.entityManager();
		TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE  o.reference = :reference ", PurchaseOrder.class);
		q.setParameter("reference", reference);
		return q;
	}
}
