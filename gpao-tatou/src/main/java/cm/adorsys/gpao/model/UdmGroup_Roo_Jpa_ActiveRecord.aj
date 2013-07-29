// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.UdmGroup;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UdmGroup_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager UdmGroup.entityManager;
    
    public static final EntityManager UdmGroup.entityManager() {
        EntityManager em = new UdmGroup().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long UdmGroup.countUdmGroups() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UdmGroup o", Long.class).getSingleResult();
    }
    
    public static List<UdmGroup> UdmGroup.findAllUdmGroups() {
        return entityManager().createQuery("SELECT o FROM UdmGroup o", UdmGroup.class).getResultList();
    }
    
    public static UdmGroup UdmGroup.findUdmGroup(Long id) {
        if (id == null) return null;
        return entityManager().find(UdmGroup.class, id);
    }
    
    public static List<UdmGroup> UdmGroup.findUdmGroupEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UdmGroup o", UdmGroup.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void UdmGroup.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void UdmGroup.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            UdmGroup attached = UdmGroup.findUdmGroup(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void UdmGroup.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void UdmGroup.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public UdmGroup UdmGroup.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UdmGroup merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}