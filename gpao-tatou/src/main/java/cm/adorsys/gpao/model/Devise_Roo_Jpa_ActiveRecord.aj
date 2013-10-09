// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Devise;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect Devise_Roo_Jpa_ActiveRecord {
    
    public static long Devise.countDevises() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Devise o", Long.class).getSingleResult();
    }
    
    public static List<Devise> Devise.findAllDevises() {
        return entityManager().createQuery("SELECT o FROM Devise o", Devise.class).getResultList();
    }
    
    public static Devise Devise.findDevise(Long id) {
        if (id == null) return null;
        return entityManager().find(Devise.class, id);
    }
    
    public static List<Devise> Devise.findDeviseEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Devise o", Devise.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Devise Devise.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Devise merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
