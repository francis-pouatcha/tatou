package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class ProductionTypeConfig extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    private String description;

    public static TypedQuery<cm.adorsys.gpao.model.ProductionTypeConfig> findManufacturingVouchersByIdUpperThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<ProductionTypeConfig> q = em.createQuery("SELECT o FROM ProductionTypeConfig AS o WHERE  o.id > :id ORDER BY o.id ", ProductionTypeConfig.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.ProductionTypeConfig> findManufacturingVouchersByIdLowerThan(Long id) {
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<ProductionTypeConfig> q = em.createQuery("SELECT o FROM ProductionTypeConfig AS o WHERE  o.id < :id ORDER BY o.id ", ProductionTypeConfig.class);
        q.setParameter("id", id);
        return q;
    }
}
