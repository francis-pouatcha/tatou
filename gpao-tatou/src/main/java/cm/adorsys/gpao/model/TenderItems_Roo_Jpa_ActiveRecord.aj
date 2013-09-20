// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.TenderItems;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect TenderItems_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager TenderItems.entityManager;
    
    public static final EntityManager TenderItems.entityManager() {
        EntityManager em = new TenderItems().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long TenderItems.countTenderItemses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM TenderItems o", Long.class).getSingleResult();
    }
    
    public static List<TenderItems> TenderItems.findAllTenderItemses() {
        return entityManager().createQuery("SELECT o FROM TenderItems o", TenderItems.class).getResultList();
    }
    
    public static TenderItems TenderItems.findTenderItems(Long id) {
        if (id == null) return null;
        return entityManager().find(TenderItems.class, id);
    }
    
    public static List<TenderItems> TenderItems.findTenderItemsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM TenderItems o", TenderItems.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void TenderItems.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void TenderItems.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            TenderItems attached = TenderItems.findTenderItems(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void TenderItems.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void TenderItems.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public TenderItems TenderItems.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        TenderItems merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
