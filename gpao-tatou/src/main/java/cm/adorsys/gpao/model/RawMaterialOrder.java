package cm.adorsys.gpao.model;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import cm.adorsys.gpao.utils.GpaoSequenceGenerator;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findRawMaterialOrdersByDocRefEquals", "findRawMaterialOrdersByReferenceEquals", "findRawMaterialOrdersByDeliveredNot", "findRawMaterialOrdersByValidatedByEquals" })
public class RawMaterialOrder extends GpaoBaseEntity {

    /**
     */
    private String reference;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
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
    private String validatedBy;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Taxe> taxes = new HashSet<Taxe>();

    /**
     */
    @Enumerated
    private DocumentStates orderState;

    /**
     */
    private String docRef;
    
    @PostPersist
    protected void postPersist() {
    	reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.RAWMATERIAL_SEQUENSE_PREFIX);
    }

    public static TypedQuery<cm.adorsys.gpao.model.RawMaterialOrder> findRawMaterialOrdersByIdUpperThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<RawMaterialOrder> q = em.createQuery("SELECT o FROM RawMaterialOrder AS o WHERE  o.id > :id ORDER BY o.id ", RawMaterialOrder.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.RawMaterialOrder> findRawMaterialOrdersByIdLowerThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<RawMaterialOrder> q = em.createQuery("SELECT o FROM RawMaterialOrder AS o WHERE  o.id < :id ORDER BY o.id ", RawMaterialOrder.class);
        q.setParameter("id", id);
        return q;
    }
}
