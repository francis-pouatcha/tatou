// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Caracteristic;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Caracteristic_Roo_Jpa_ActiveRecord {
    
    public static final List<String> Caracteristic.fieldNames4OrderClauseFilter = java.util.Arrays.asList("productSize", "color");
    
    public static long Caracteristic.countCaracteristics() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Caracteristic o", Long.class).getSingleResult();
    }
    
    public static List<Caracteristic> Caracteristic.findAllCaracteristics() {
        return entityManager().createQuery("SELECT o FROM Caracteristic o", Caracteristic.class).getResultList();
    }
    
    public static List<Caracteristic> Caracteristic.findAllCaracteristics(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Caracteristic o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Caracteristic.class).getResultList();
    }
    
    public static Caracteristic Caracteristic.findCaracteristic(Long id) {
        if (id == null) return null;
        return entityManager().find(Caracteristic.class, id);
    }
    
    public static List<Caracteristic> Caracteristic.findCaracteristicEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Caracteristic o", Caracteristic.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<Caracteristic> Caracteristic.findCaracteristicEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Caracteristic o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Caracteristic.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Caracteristic Caracteristic.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Caracteristic merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
