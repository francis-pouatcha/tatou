package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.TypedQuery;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findProductionStepConfigsByProductionTypeConf", "findProductionStepConfigsByProductionWorkshop", "findProductionStepConfigsByProductionTypeConfAndRank" })
public class ProductionStepConfig extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    private String description;

    /**
     */
    @NotNull
    @ManyToOne
    private ProductionWorkshop productionWorkshop;

    /**
     */
    private BigDecimal duration;

    /**
     */
    private int rank;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<GpaoUser> employees = new HashSet<GpaoUser>();

    /**
     */
    @ManyToOne
    private ProductionTypeConfig productionTypeConf;

    public static TypedQuery<cm.adorsys.gpao.model.ProductionStepConfig> findProductionStepConfigByIdUpperThan(Long id) {
        EntityManager em = ProductionStepConfig.entityManager();
        TypedQuery<ProductionStepConfig> q = em.createQuery("SELECT o FROM ProductionStepConfig AS o WHERE  o.id > :id ORDER BY o.id ", ProductionStepConfig.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<ProductionStepConfig> findProductionStepConfigByIdLowerThan(Long id) {
        EntityManager em = ProductionStepConfig.entityManager();
        TypedQuery<ProductionStepConfig> q = em.createQuery("SELECT o FROM ProductionStepConfig AS o WHERE  o.id < :id ORDER BY o.id ", ProductionStepConfig.class);
        q.setParameter("id", id);
        return q;
    }
}
