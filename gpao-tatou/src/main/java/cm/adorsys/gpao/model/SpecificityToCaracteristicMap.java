package cm.adorsys.gpao.model;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findSpecificityToCaracteristicMapsByCaracteristic", "findSpecificityToCaracteristicMapsBySpecificity", "findSpecificityToCaracteristicMapsBySpecificityAndCaracteristicEquals" })
@RooJson
public class SpecificityToCaracteristicMap extends GpaoBaseEntity {

    /**
     */
    @NotNull
    @ManyToOne
    private Caracteristic caracteristic;

    /**
     */
    @NotNull
    @ManyToOne
    private Specificity specificity;

    public SpecificityToCaracteristicMap() {
	}
    
    public SpecificityToCaracteristicMap(Caracteristic caracteristic,
			Specificity specificity) {
		super();
		this.caracteristic = caracteristic;
		this.specificity = specificity;
	}

	public static TypedQuery<Specificity> findSpecificitysByCaracteristicsEquals(Caracteristic caracteristic) {
        if (caracteristic == null) throw new IllegalArgumentException("Invalid caracteristic value");
        EntityManager em = SpecificityToCaracteristicMap.entityManager();
        String jpaQuery = "SELECT o.specificity FROM SpecificityToCaracteristicMap AS o WHERE o.caracteristic = :caracteristic";
        TypedQuery<Specificity> q = em.createQuery(jpaQuery, Specificity.class);
        q.setParameter("caracteristic", caracteristic);
        return q;
    }

	public static TypedQuery<Caracteristic> findCaracteristicsBySpecificityEquals(Specificity specificity) {
        if (specificity == null) throw new IllegalArgumentException("Invalid caracteristic value");
        EntityManager em = SpecificityToCaracteristicMap.entityManager();
        String jpaQuery = "SELECT o.caracteristic FROM SpecificityToCaracteristicMap AS o WHERE o.specificity = :specificity";
        TypedQuery<Caracteristic> q = em.createQuery(jpaQuery, Caracteristic.class);
        q.setParameter("specificity", specificity);
        return q;
    }
}
