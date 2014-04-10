package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;

import cm.adorsys.gpao.utils.GpaoSequenceGenerator;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findManufacturingVouchersByDocumentState", "findManufacturingVouchersByReferenceEquals" })
public class ManufacturingVoucher extends GpaoBaseEntity {

    /**
     */
    private String reference;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createDate;

    /**
     */
    @NotNull
    private String createdBy;

    /**
     */
    @Enumerated
    private DocumentStates documentState;

    /**
     */
    @ManyToOne
    private CustomerOrder customerOrder;

    /**
     */
    @ManyToOne
    private Partner customer;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyy HH:mm")
    private Date delayDate;
    
    @PostPersist
    public void postPersist() {
    	reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.MANUFACTURINGVOUCHER_SEQUENSE_PREFIX);
    }
    public static TypedQuery<cm.adorsys.gpao.model.ManufacturingVoucher> findManufacturingVouchersByIdUpperThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<ManufacturingVoucher> q = em.createQuery("SELECT o FROM ManufacturingVoucher AS o WHERE  o.id > :id ORDER BY o.id ", ManufacturingVoucher.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.ManufacturingVoucher> findManufacturingVouchersByIdLowerThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<ManufacturingVoucher> q = em.createQuery("SELECT o FROM ManufacturingVoucher AS o WHERE  o.id < :id ORDER BY o.id ", ManufacturingVoucher.class);
        q.setParameter("id", id);
        return q;
    }
}
