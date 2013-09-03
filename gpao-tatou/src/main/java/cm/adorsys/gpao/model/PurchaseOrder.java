package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    private String validatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date validateDate;

    @Min(0L)
    private BigDecimal amountHt;

    @Min(0L)
    private BigDecimal taxeAmount;

    private BigDecimal totalAmount;

    @Enumerated
    private DocumentStates orderState;

    private String createdBy;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();

    @NotNull
    @ManyToOne
    private Devise currency;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder")
    private Set<OrderItems> orderItems = new HashSet<OrderItems>();
    
    public static TypedQuery<PurchaseOrder> findPurchaseOrdersByIdUpperThan(Long id) {
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
}
