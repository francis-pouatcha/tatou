package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class ProductionWorkshop extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    @ManyToOne
    private GpaoUser manager;

    /**
     */
    private String materials;

    /**
     */
    private String workflows;

    /**
     */
    private String riskAndDangers;

    /**
     */
    private String specialInstructions;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date dateCreated;
    

    public static TypedQuery<ProductionWorkshop> findProductionWorkshopsByIdUpperThan(Long id) {
    	EntityManager em = ProductionWorkshop.entityManager();
        TypedQuery<ProductionWorkshop> q = em.createQuery("SELECT o FROM ProductionWorkshop AS o WHERE  o.id > :id ORDER BY o.id ", ProductionWorkshop.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<ProductionWorkshop> findProductionWorkshopsByIdLowerThan(Long id) {
        EntityManager em = ProductionWorkshop.entityManager();
        TypedQuery<ProductionWorkshop> q = em.createQuery("SELECT o FROM ProductionWorkshop AS o WHERE  o.id < :id ORDER BY o.id DESC ", ProductionWorkshop.class);
        q.setParameter("id", id);
        return q;
    }
}
