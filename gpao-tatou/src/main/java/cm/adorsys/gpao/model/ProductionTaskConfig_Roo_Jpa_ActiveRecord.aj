// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.ProductionTaskConfig;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ProductionTaskConfig_Roo_Jpa_ActiveRecord {
    
    public static final List<String> ProductionTaskConfig.fieldNames4OrderClauseFilter = java.util.Arrays.asList("name", "productionStepConfig", "assignee", "duration", "rank");
    
    public static long ProductionTaskConfig.countProductionTaskConfigs() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ProductionTaskConfig o", Long.class).getSingleResult();
    }
    
    public static List<ProductionTaskConfig> ProductionTaskConfig.findAllProductionTaskConfigs() {
        return entityManager().createQuery("SELECT o FROM ProductionTaskConfig o", ProductionTaskConfig.class).getResultList();
    }
    
    public static List<ProductionTaskConfig> ProductionTaskConfig.findAllProductionTaskConfigs(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ProductionTaskConfig o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ProductionTaskConfig.class).getResultList();
    }
    
    public static ProductionTaskConfig ProductionTaskConfig.findProductionTaskConfig(Long id) {
        if (id == null) return null;
        return entityManager().find(ProductionTaskConfig.class, id);
    }
    
    public static List<ProductionTaskConfig> ProductionTaskConfig.findProductionTaskConfigEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ProductionTaskConfig o", ProductionTaskConfig.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<ProductionTaskConfig> ProductionTaskConfig.findProductionTaskConfigEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ProductionTaskConfig o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ProductionTaskConfig.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public ProductionTaskConfig ProductionTaskConfig.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ProductionTaskConfig merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
