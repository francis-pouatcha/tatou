// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Specificity;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Specificity_Roo_Jpa_ActiveRecord {
    
    public static final List<String> Specificity.fieldNames4OrderClauseFilter = java.util.Arrays.asList("designation", "description", "active");
    
    public static long Specificity.countSpecificitys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Specificity o", Long.class).getSingleResult();
    }
    
    public static List<Specificity> Specificity.findAllSpecificitys() {
        return entityManager().createQuery("SELECT o FROM Specificity o", Specificity.class).getResultList();
    }
    
    public static List<Specificity> Specificity.findAllSpecificitys(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Specificity o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Specificity.class).getResultList();
    }
    
    public static Specificity Specificity.findSpecificity(Long id) {
        if (id == null) return null;
        return entityManager().find(Specificity.class, id);
    }
    
    public static List<Specificity> Specificity.findSpecificityEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Specificity o", Specificity.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Specificity> Specificity.findSpecificityEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Specificity o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Specificity.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Specificity Specificity.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Specificity merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
