package cm.adorsys.gpao.model;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findCustomerOrdersByReferenceEquals", "findCustomerOrdersByOrderState", "findCustomerOrdersByOrderDateBetween", "findCustomerOrdersByCustomer" })
public class CustomerOrder extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String reference;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date orderDate;

    /**
     */
    private String createdBy;

    /**
     */
    @Value("false")
    private Boolean delivered;

    /**
     */
    @Value("false")
    private Boolean validated;

    /**
     */
    private String validatedBy;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Taxe> taxes = new HashSet<Taxe>();

    /**
     */
    @Min(0L)
    private BigDecimal taxeAmount = BigDecimal.ZERO;

    /**
     */
    @Min(0L)
    private BigDecimal amountHt = BigDecimal.ZERO;

    /**
     */
    @Min(0L)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    /**
     */
    @Enumerated
    private DocumentStates orderState;

    /**
     */
    @ManyToOne
    private Devise currency;

    @PostPersist
    public void postPersist() {
        reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.CUSTOMER_ORDER_SEQUENCE_PREFIX);
        createdBy = SecurityUtil.getUserName();
    }

    /**
     */
    @NotNull
    @ManyToOne
    private Partner customer;

    public static TypedQuery<cm.adorsys.gpao.model.CustomerOrder> findCustomerOrdersByIdUpperThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<CustomerOrder> q = em.createQuery("SELECT o FROM CustomerOrder AS o WHERE  o.id > :id ORDER BY o.id ", CustomerOrder.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.CustomerOrder> findCustomerOrdersByIdLowerThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<CustomerOrder> q = em.createQuery("SELECT o FROM CustomerOrder AS o WHERE  o.id < :id ORDER BY o.id ", CustomerOrder.class);
        q.setParameter("id", id);
        return q;
    }
}
