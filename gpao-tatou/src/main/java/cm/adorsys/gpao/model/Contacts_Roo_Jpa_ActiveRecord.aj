// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Contacts;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Contacts_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager Contacts.entityManager;
    
    public static final EntityManager Contacts.entityManager() {
        EntityManager em = new Contacts().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long Contacts.countContactses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Contacts o", Long.class).getSingleResult();
    }
    
    public static List<Contacts> Contacts.findAllContactses() {
        return entityManager().createQuery("SELECT o FROM Contacts o", Contacts.class).getResultList();
    }
    
    public static Contacts Contacts.findContacts(Long id) {
        if (id == null) return null;
        return entityManager().find(Contacts.class, id);
    }
    
    public static List<Contacts> Contacts.findContactsEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Contacts o", Contacts.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void Contacts.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void Contacts.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Contacts attached = Contacts.findContacts(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void Contacts.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void Contacts.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public Contacts Contacts.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Contacts merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}