package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findProductionTaskConfigsByProductionStepConfig", "findProductionTaskConfigsByProductionStepConfigAndRank" })
public class ProductionTaskConfig extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    @ManyToOne
    private ProductionStepConfig productionStepConfig;

    /**
     */
    @ManyToOne
    private GpaoUser assignee;

    /**
     * The estimated task's duration in hours
     */
    private int duration;

    public static TypedQuery<cm.adorsys.gpao.model.ProductionTaskConfig> findProductionTaskConfigByIdUpperThan(Long id) {
        EntityManager em = ProductionTaskConfig.entityManager();
        TypedQuery<ProductionTaskConfig> q = em.createQuery("SELECT o FROM ProductionTaskConfig AS o WHERE  o.id > :id ORDER BY o.id ", ProductionTaskConfig.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<ProductionTaskConfig> findProductionTaskConfigByIdLowerThan(Long id) {
        EntityManager em = ProductionTaskConfig.entityManager();
        TypedQuery<ProductionTaskConfig> q = em.createQuery("SELECT o FROM ProductionTaskConfig AS o WHERE  o.id < :id ORDER BY o.id ", ProductionTaskConfig.class);
        q.setParameter("id", id);
        return q;
    }

    /**
     */
    private int rank;
}
