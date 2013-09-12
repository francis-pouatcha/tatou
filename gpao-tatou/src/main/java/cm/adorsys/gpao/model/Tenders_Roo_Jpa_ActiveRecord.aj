// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Tenders;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Tenders_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Tenders.entityManager;
    
    public static final EntityManager Tenders.entityManager() {
        EntityManager em = new Tenders().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Tenders.countTenderses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Tenders o", Long.class).getSingleResult();
    }
    
    public static List<Tenders> Tenders.findAllTenderses() {
        return entityManager().createQuery("SELECT o FROM Tenders o", Tenders.class).getResultList();
    }
    
    public static Tenders Tenders.findTenders(Long id) {
        if (id == null) return null;
        return entityManager().find(Tenders.class, id);
    }
    
    public static List<Tenders> Tenders.findTendersEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Tenders o", Tenders.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Tenders.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Tenders.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Tenders attached = Tenders.findTenders(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Tenders.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Tenders.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Tenders Tenders.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Tenders merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
