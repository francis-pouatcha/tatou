// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Taxe;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Taxe_Roo_Jpa_ActiveRecord {
    
    public static final List<String> Taxe.fieldNames4OrderClauseFilter = java.util.Arrays.asList("name", "shortName", "taxeValue", "isActive", "taxeType");
    
    public static long Taxe.countTaxes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Taxe o", Long.class).getSingleResult();
    }
    
    public static List<Taxe> Taxe.findAllTaxes() {
        return entityManager().createQuery("SELECT o FROM Taxe o", Taxe.class).getResultList();
    }
    
    public static List<Taxe> Taxe.findAllTaxes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Taxe o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Taxe.class).getResultList();
    }
    
    public static Taxe Taxe.findTaxe(Long id) {
        if (id == null) return null;
        return entityManager().find(Taxe.class, id);
    }
    
    public static List<Taxe> Taxe.findTaxeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Taxe o", Taxe.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Taxe> Taxe.findTaxeEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Taxe o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Taxe.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Taxe Taxe.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Taxe merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
