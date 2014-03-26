// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.SpecificityToCaracteristicMap;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect SpecificityToCaracteristicMap_Roo_Jpa_ActiveRecord {
    
    public static final List<String> SpecificityToCaracteristicMap.fieldNames4OrderClauseFilter = java.util.Arrays.asList("caracteristic", "specificity");
    
    public static long SpecificityToCaracteristicMap.countSpecificityToCaracteristicMaps() {
        return entityManager().createQuery("SELECT COUNT(o) FROM SpecificityToCaracteristicMap o", Long.class).getSingleResult();
    }
    
    public static List<SpecificityToCaracteristicMap> SpecificityToCaracteristicMap.findAllSpecificityToCaracteristicMaps() {
        return entityManager().createQuery("SELECT o FROM SpecificityToCaracteristicMap o", SpecificityToCaracteristicMap.class).getResultList();
    }
    
    public static List<SpecificityToCaracteristicMap> SpecificityToCaracteristicMap.findAllSpecificityToCaracteristicMaps(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SpecificityToCaracteristicMap o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SpecificityToCaracteristicMap.class).getResultList();
    }
    
    public static SpecificityToCaracteristicMap SpecificityToCaracteristicMap.findSpecificityToCaracteristicMap(Long id) {
        if (id == null) return null;
        return entityManager().find(SpecificityToCaracteristicMap.class, id);
    }
    
    public static List<SpecificityToCaracteristicMap> SpecificityToCaracteristicMap.findSpecificityToCaracteristicMapEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM SpecificityToCaracteristicMap o", SpecificityToCaracteristicMap.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<SpecificityToCaracteristicMap> SpecificityToCaracteristicMap.findSpecificityToCaracteristicMapEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM SpecificityToCaracteristicMap o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, SpecificityToCaracteristicMap.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public SpecificityToCaracteristicMap SpecificityToCaracteristicMap.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        SpecificityToCaracteristicMap merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}