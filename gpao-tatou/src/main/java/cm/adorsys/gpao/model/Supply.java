package cm.adorsys.gpao.model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
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
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import cm.adorsys.gpao.utils.TaxeUtils;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
@RooJson
public class Supply extends GpaoBaseEntity {

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

    private BigDecimal taxAmount = BigDecimal.ZERO;

    private BigDecimal taxedAmount = BigDecimal.ZERO;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "supply")
    private Set<SupplyItems> supplyItems = new HashSet<SupplyItems>();

    @NotNull
    @ManyToOne
    private Devise currency;

    @NotNull
    @ManyToOne
    private Company company;

    @NotNull
    private String docRef;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Taxe> taxes = new HashSet<Taxe>();

    public Supply() {
    }

    public Supply(Company company) {
        this.company = Company.findCompany(Long.valueOf(1));
        currency = company.getDevise();
    }

    @Override
    public String toString() {
        return reference + " : " + docRef;
    }

    public boolean isOpen() {
        return DocumentStates.OUVERT.equals(status);
    }

    public Supply(PurchaseOrder order) {
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
        reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.DELIVERY_SEQUENCE_PREFIX);
        createBy = SecurityUtil.getUserName();
    }

    public void initAmount() {
        unTaxeAmount = BigDecimal.ZERO;
        taxAmount = BigDecimal.ZERO;
        taxedAmount = BigDecimal.ZERO;
    }

    public void increaseAmountFromDeliveryItem(SupplyItems deliveryItem) {
        unTaxeAmount = unTaxeAmount.add(deliveryItem.getAmountHt());
    }

    public boolean hasDeliveryItem() {
        return !SupplyItems.findSupplyItemsesBySupply(this).getResultList().isEmpty();
    }

    public static TypedQuery<cm.adorsys.gpao.model.Supply> findDeliveryByStatusAndOriginAndReceiveDateBetween(DocumentStates status, String reference, Date beginReceivedDate, Date endReceivedDate, String docRef, String receiveBy, DeliveryOrigin origin) {
        EntityManager em = Supply.entityManager();
        StringBuilder query = new StringBuilder("SELECT o FROM Supply AS o WHERE  o.id IS NOT NULL ");
        if (StringUtils.isNotBlank(reference)) {
            query.append(" AND o.reference = :reference");
        }
        if (StringUtils.isNotBlank(docRef)) {
            query.append(" AND o.docRef = :docRef");
        }
        if (StringUtils.isNotBlank(receiveBy)) {
            query.append(" AND o.receiveBy = :receiveBy");
        }
        if (origin != null) {
            query.append(" AND o.origin = :origin");
        }
        if (!(status == null || DocumentStates.TOUS.equals(status))) {
            query.append(" AND o.status = :status");
        }
        if (beginReceivedDate != null) {
            query.append(" AND o.receivedDate >= :beginReceivedDate");
        }
        if (endReceivedDate != null) {
            query.append(" AND o.receivedDate <= :endReceivedDate");
        }
        TypedQuery<Supply> q = em.createQuery(query.append(" ORDER BY o.id ").toString(), Supply.class);
        if (StringUtils.isNotBlank(reference)) q.setParameter("reference", reference);
        if (StringUtils.isNotBlank(receiveBy)) q.setParameter("receiveBy", receiveBy);
        if (StringUtils.isNotBlank(docRef)) q.setParameter("docRef", docRef);
        if (!(status == null || DocumentStates.TOUS.equals(status))) q.setParameter("status", status);
        if (origin != null) q.setParameter("origin", origin);
        if (beginReceivedDate != null) q.setParameter("receivedDate", beginReceivedDate);
        if (endReceivedDate != null) q.setParameter("receivedDate", endReceivedDate);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Supply> findDeliverysByIdUpperThan(Long id) {
        EntityManager em = Supply.entityManager();
        TypedQuery<Supply> q = em.createQuery("SELECT o FROM Supply AS o WHERE  o.id > :id ORDER BY o.id ", Supply.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Supply> findDeliverysByReferenceEquals(String reference) {
        EntityManager em = Supply.entityManager();
        TypedQuery<Supply> q = em.createQuery("SELECT o FROM Supply AS o WHERE  o.reference = :reference ", Supply.class);
        q.setParameter("reference", reference);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Supply> findDeliverysByIdLowerThan(Long id) {
        EntityManager em = Supply.entityManager();
        TypedQuery<Supply> q = em.createQuery("SELECT o FROM Supply AS o WHERE  o.id < :id ORDER BY o.id DESC ", Supply.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Supply> findDeliverysByDocRef(String docRef) {
        EntityManager em = Supply.entityManager();
        TypedQuery<Supply> q = em.createQuery("SELECT o FROM Supply AS o WHERE  o.docRef = :docRef ORDER BY o.id DESC ", Supply.class);
        q.setParameter("docRef", docRef);
        return q;
    }

    public void computeTaxeAmountAndTaxedAmount() {
        Iterator<Taxe> salesTaxeIterator = taxes.iterator();
        BigDecimal taxesAmount = BigDecimal.ZERO;
        while (salesTaxeIterator.hasNext()) {
            Taxe taxe = (Taxe) salesTaxeIterator.next();
            TaxeType taxeType = taxe.getTaxeType();
            if (taxeType.equals(TaxeType.PAR_POURCENTAGE)) {
                taxesAmount = taxesAmount.add(TaxeUtils.computeTaxeByPercentage(this.unTaxeAmount, taxe.getTaxeValue()));
            } else {
                //it is a #TaxeType.PAR_VALEUR
                taxesAmount = taxesAmount.add(TaxeUtils.computeTaxeByValue(this.unTaxeAmount, taxe.getTaxeValue()));
            }
        }
        this.taxAmount = taxesAmount;
        this.taxedAmount = this.unTaxeAmount.add(this.taxAmount);
    }
}
