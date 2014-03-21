package cm.adorsys.gpao.model;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findSpecificitysByDesignationEquals", "findSpecificitysByDesignationLike", "findSpecificitysByDesignationLikeAndActiveNot" })
@RooJson
public class Specificity extends GpaoBaseEntity {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String designation;

    /**
     */
    private String description;

    /**
     */
    @Value("true")
    private Boolean active;

    public static TypedQuery<Specificity> findSpecificitysByDesignationLikeAndActiveNot(String designation, Boolean active, String sortFieldName, String sortOrder) {
        if (designation == null || designation.length() == 0) designation = "*";
        designation = designation.replace('*', '%');
        if (designation.charAt(0) != '%') {
            designation = "%" + designation;
        }
        if (designation.charAt(designation.length() - 1) != '%') {
            designation = designation + "%";
        }
        if (active == null) throw new IllegalArgumentException("The active argument is required");
        EntityManager em = Specificity.entityManager();
        String jpaQuery = "SELECT o FROM Specificity AS o WHERE LOWER(o.designation) LIKE LOWER(:designation)  AND o.active IS NOT :active";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Specificity> q = em.createQuery(jpaQuery, Specificity.class);
        q.setParameter("designation", designation);
        q.setParameter("active", active);
        return q;
    }

    public static TypedQuery<Specificity> findSpecificitysByDesignationLike(String designation) {
        if (designation == null || designation.length() == 0) designation = "*";
        designation = designation.replace('*', '%');
        if (designation.charAt(0) != '%') {
            designation = "%" + designation;
        }
        if (designation.charAt(designation.length() - 1) != '%') {
            designation = designation + "%";
        }
        EntityManager em = Specificity.entityManager();
        TypedQuery<Specificity> q = em.createQuery("SELECT o FROM Specificity AS o WHERE LOWER(o.designation) LIKE LOWER(:designation)", Specificity.class);
        q.setParameter("designation", designation);
        return q;
    }

    public static TypedQuery<Specificity> findSpecificitysByDesignationLikeAndActiveNot(String designation, Boolean active) {
        if (designation == null || designation.length() == 0) designation = "*";
        designation = designation.replace('*', '%');
        if (designation.charAt(0) != '%') {
            designation = "%" + designation;
        }
        if (designation.charAt(designation.length() - 1) != '%') {
            designation = designation + "%";
        }
        if (active == null) throw new IllegalArgumentException("The active argument is required");
        EntityManager em = Specificity.entityManager();
        TypedQuery<Specificity> q = em.createQuery("SELECT o FROM Specificity AS o WHERE LOWER(o.designation) LIKE LOWER(:designation)  AND o.active IS NOT :active", Specificity.class);
        q.setParameter("designation", designation);
        q.setParameter("active", active);
        return q;
    }
}
