// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Delivery;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Delivery_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Delivery.entityManager;
    
    public static final EntityManager Delivery.entityManager() {
        EntityManager em = new Delivery().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Delivery.countDeliverys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Delivery o", Long.class).getSingleResult();
    }
    
    public static List<Delivery> Delivery.findAllDeliverys() {
        return entityManager().createQuery("SELECT o FROM Delivery o", Delivery.class).getResultList();
    }
    
    public static Delivery Delivery.findDelivery(Long id) {
        if (id == null) return null;
        return entityManager().find(Delivery.class, id);
    }
    
    public static List<Delivery> Delivery.findDeliveryEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Delivery o", Delivery.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Delivery.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Delivery.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Delivery attached = Delivery.findDelivery(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Delivery.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Delivery.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Delivery Delivery.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Delivery merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}