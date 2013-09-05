// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.OrderItems;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect OrderItems_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager OrderItems.entityManager;
    
    public static final EntityManager OrderItems.entityManager() {
        EntityManager em = new OrderItems().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long OrderItems.countOrderItemses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM OrderItems o", Long.class).getSingleResult();
    }
    
    public static List<OrderItems> OrderItems.findAllOrderItemses() {
        return entityManager().createQuery("SELECT o FROM OrderItems o", OrderItems.class).getResultList();
    }
    
    public static OrderItems OrderItems.findOrderItems(Long id) {
        if (id == null) return null;
        return entityManager().find(OrderItems.class, id);
    }
    
    public static List<OrderItems> OrderItems.findOrderItemsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM OrderItems o", OrderItems.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void OrderItems.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void OrderItems.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            OrderItems attached = OrderItems.findOrderItems(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void OrderItems.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void OrderItems.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public OrderItems OrderItems.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        OrderItems merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}