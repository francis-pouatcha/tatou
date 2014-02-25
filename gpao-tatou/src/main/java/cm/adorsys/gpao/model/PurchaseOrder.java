package cm.adorsys.gpao.model;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.impl.TatouPurchaseService;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import cm.adorsys.gpao.utils.TaxeUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToMany;

/**
 * @author clovisgakam
 *
 */
@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class PurchaseOrder extends GpaoBaseEntity {

    @NotNull
    private String reference;

    @NotNull
    @ManyToOne
    private Partner supplier;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyy HH:mm")
    private Date orderDate;

    @Value("false")
    private Boolean received;

    @Value("false")
    private Boolean invoiced;

    @Value("false")
    private Boolean isValided;

    private String validatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyy HH:mm")
    private Date validateDate;

    @Min(0L)
    private BigDecimal amountHt = BigDecimal.ZERO;

    @Min(0L)
    private BigDecimal taxeAmount = BigDecimal.ZERO;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private DocumentStates orderState = DocumentStates.BROUILLON;

    private String createdBy;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyy HH:mm")
    private Date created = new Date();

    @NotNull
    @ManyToOne
    private Devise currency;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder", fetch = FetchType.EAGER)
    private List<OrderItems> orderItems = new ArrayList<OrderItems>();

    /** Used to make jasperreport query much more easier.*/
    @NotNull
    @ManyToOne
    private Company company;

    /** appel d'offre. */
    @ManyToOne
    private Tenders tender;


    /**
     */
    @ManyToMany()
    private Set<Taxe> saleTaxes = new HashSet<Taxe>();

    public void initAmount() {
        amountHt = BigDecimal.ZERO;
        taxeAmount = BigDecimal.ZERO;
        totalAmount = BigDecimal.ZERO;
    }

    public boolean isClosed() {
        return DocumentStates.FERMER.equals(orderState);
    }

    public boolean hasTender() {
        return (tender != null);
    }

    @PostLoad
    public void postLoad() {
        //orderItems = OrderItems.findOrderItemssByPurchaseOrder(this).getResultList();
    }

    @PostPersist
    public void postPersist() {
        reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.PORCHASE_SEQUENCE_PREFIX);
        createdBy = SecurityUtil.getUserName();
    }

    public void increaseAmountFromOrderItem(OrderItems orderItem) {
        amountHt = amountHt.add(orderItem.getSubTotal());
//        taxeAmount = taxeAmount.add(orderItem.getTaxeAmount());
//        totalAmount = totalAmount.add(orderItem.getTaxedSubTotal());
    }

    public OrderItems hasProduct(Product product) {
        List<OrderItems> orderItems2 = getOrderItems();
        for (OrderItems orderItems : orderItems2) {
            if (orderItems.getProduct().equals(product)) return orderItems;
        }
        return null;
    }

    public static TypedQuery<cm.adorsys.gpao.model.PurchaseOrder> findPurchaseOrderByStatusAndTenderRefAndDateBetween(Partner supplier, DocumentStates orderState, String tenderRef, Date beginDate, Date endDate, String reference) {
        EntityManager em = Tenders.entityManager();
        StringBuilder query = new StringBuilder("SELECT o FROM PurchaseOrder AS o WHERE  o.id IS NOT NULL ");
        if (StringUtils.isNotBlank(reference)) {
            query.append(" AND o.reference = :reference");
        }
        if (StringUtils.isNotBlank(tenderRef)) {
            query.append(" AND o.tender.reference = :tenderRef");
        }
        if (!(orderState == null || DocumentStates.TOUS.equals(orderState))) {
            query.append(" AND o.orderState = :orderState");
        }
        if (beginDate != null) {
            query.append(" AND o.created >= :beginDate");
        }
        if (endDate != null) {
            query.append(" AND o.orderState <= :endDate");
        }
        if (supplier != null) {
            query.append(" AND o.supplier = :supplier");
        }
        TypedQuery<PurchaseOrder> q = em.createQuery(query.append(" ORDER BY o.id ").toString(), PurchaseOrder.class);
        if (StringUtils.isNotBlank(reference)) q.setParameter("reference", reference);
        if (StringUtils.isNotBlank(tenderRef)) q.setParameter("tenderRef", tenderRef);
        if (!(orderState == null || DocumentStates.TOUS.equals(orderState))) q.setParameter("orderState", orderState);
        if (beginDate != null) q.setParameter("beginDate", beginDate);
        if (endDate != null) q.setParameter("endDate", endDate);
        if (supplier != null) q.setParameter("supplier", supplier);
        return q;
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

	/**
	 * <p>Compute the taxes amount based on the {@link #amountHt PurchaseOrder#amountHt} and update the {@link #totalAmount PurchaseOrder#amountHt} values. </p>
	 * 
	 * @see TatouPurchaseService#calculatePurchaseTaxAndAmount(PurchaseOrder)
	 */
	public void applyTaxesRateAndComputeTotalAmout() {
		Iterator<Taxe> salesTaxeIterator = saleTaxes.iterator();
		BigDecimal taxesAmount = BigDecimal.ZERO;
		while (salesTaxeIterator.hasNext()) {
			Taxe taxe = (Taxe) salesTaxeIterator.next();
			TaxeType taxeType = taxe.getTaxeType();
			if(taxeType.equals(TaxeType.PAR_POURCENTAGE)) {
				taxesAmount= taxesAmount.add(TaxeUtils.computeTaxeByPercentage(amountHt, taxe.getTaxeValue()));
			}else{//it is a #TaxeType.PAR_VALEUR
				taxesAmount = taxesAmount.add(TaxeUtils.computeTaxeByValue(amountHt, taxe.getTaxeValue()));
			}
		}
		this.taxeAmount = taxesAmount;
		this.totalAmount = this.amountHt.add(taxeAmount);
	}
}
