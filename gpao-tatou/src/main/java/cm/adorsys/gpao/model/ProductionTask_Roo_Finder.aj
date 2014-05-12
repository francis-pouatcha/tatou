// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ProductionStep;
import cm.adorsys.gpao.model.ProductionTask;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect ProductionTask_Roo_Finder {
    
    public static Long ProductionTask.countFindProductionTasksByProductionStep(ProductionStep productionStep) {
        if (productionStep == null) throw new IllegalArgumentException("The productionStep argument is required");
        EntityManager em = ProductionTask.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM ProductionTask AS o WHERE o.productionStep = :productionStep", Long.class);
        q.setParameter("productionStep", productionStep);
        return ((Long) q.getSingleResult());
    }
    
    public static Long ProductionTask.countFindProductionTasksByProductionStepAndTaskState(ProductionStep productionStep, DocumentStates taskState) {
        if (productionStep == null) throw new IllegalArgumentException("The productionStep argument is required");
        if (taskState == null) throw new IllegalArgumentException("The taskState argument is required");
        EntityManager em = ProductionTask.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM ProductionTask AS o WHERE o.productionStep = :productionStep AND o.taskState = :taskState", Long.class);
        q.setParameter("productionStep", productionStep);
        q.setParameter("taskState", taskState);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<ProductionTask> ProductionTask.findProductionTasksByProductionStep(ProductionStep productionStep) {
        if (productionStep == null) throw new IllegalArgumentException("The productionStep argument is required");
        EntityManager em = ProductionTask.entityManager();
        TypedQuery<ProductionTask> q = em.createQuery("SELECT o FROM ProductionTask AS o WHERE o.productionStep = :productionStep", ProductionTask.class);
        q.setParameter("productionStep", productionStep);
        return q;
    }
    
    public static TypedQuery<ProductionTask> ProductionTask.findProductionTasksByProductionStep(ProductionStep productionStep, String sortFieldName, String sortOrder) {
        if (productionStep == null) throw new IllegalArgumentException("The productionStep argument is required");
        EntityManager em = ProductionTask.entityManager();
        String jpaQuery = "SELECT o FROM ProductionTask AS o WHERE o.productionStep = :productionStep";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<ProductionTask> q = em.createQuery(jpaQuery, ProductionTask.class);
        q.setParameter("productionStep", productionStep);
        return q;
    }
    
    public static TypedQuery<ProductionTask> ProductionTask.findProductionTasksByProductionStepAndTaskState(ProductionStep productionStep, DocumentStates taskState) {
        if (productionStep == null) throw new IllegalArgumentException("The productionStep argument is required");
        if (taskState == null) throw new IllegalArgumentException("The taskState argument is required");
        EntityManager em = ProductionTask.entityManager();
        TypedQuery<ProductionTask> q = em.createQuery("SELECT o FROM ProductionTask AS o WHERE o.productionStep = :productionStep AND o.taskState = :taskState", ProductionTask.class);
        q.setParameter("productionStep", productionStep);
        q.setParameter("taskState", taskState);
        return q;
    }
    
    public static TypedQuery<ProductionTask> ProductionTask.findProductionTasksByProductionStepAndTaskState(ProductionStep productionStep, DocumentStates taskState, String sortFieldName, String sortOrder) {
        if (productionStep == null) throw new IllegalArgumentException("The productionStep argument is required");
        if (taskState == null) throw new IllegalArgumentException("The taskState argument is required");
        EntityManager em = ProductionTask.entityManager();
        String jpaQuery = "SELECT o FROM ProductionTask AS o WHERE o.productionStep = :productionStep AND o.taskState = :taskState";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<ProductionTask> q = em.createQuery(jpaQuery, ProductionTask.class);
        q.setParameter("productionStep", productionStep);
        q.setParameter("taskState", taskState);
        return q;
    }
    
}