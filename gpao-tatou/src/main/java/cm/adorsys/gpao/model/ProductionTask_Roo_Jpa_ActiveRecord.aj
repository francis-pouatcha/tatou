// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.ProductionTask;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ProductionTask_Roo_Jpa_ActiveRecord {
    
    public static final List<String> ProductionTask.fieldNames4OrderClauseFilter = java.util.Arrays.asList("productionTaskConfig", "productionStep", "startDate", "endDate", "taskState");
    
    public static long ProductionTask.countProductionTasks() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ProductionTask o", Long.class).getSingleResult();
    }
    
    public static List<ProductionTask> ProductionTask.findAllProductionTasks() {
        return entityManager().createQuery("SELECT o FROM ProductionTask o", ProductionTask.class).getResultList();
    }
    
    public static List<ProductionTask> ProductionTask.findAllProductionTasks(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ProductionTask o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ProductionTask.class).getResultList();
    }
    
    public static ProductionTask ProductionTask.findProductionTask(Long id) {
        if (id == null) return null;
        return entityManager().find(ProductionTask.class, id);
    }
    
    public static List<ProductionTask> ProductionTask.findProductionTaskEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ProductionTask o", ProductionTask.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<ProductionTask> ProductionTask.findProductionTaskEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ProductionTask o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ProductionTask.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public ProductionTask ProductionTask.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ProductionTask merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}