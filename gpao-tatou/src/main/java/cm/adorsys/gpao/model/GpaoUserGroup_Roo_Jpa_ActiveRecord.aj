// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.GpaoUserGroup;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect GpaoUserGroup_Roo_Jpa_ActiveRecord {
    
    public static long GpaoUserGroup.countGpaoUserGroups() {
        return entityManager().createQuery("SELECT COUNT(o) FROM GpaoUserGroup o", Long.class).getSingleResult();
    }
    
    public static List<GpaoUserGroup> GpaoUserGroup.findAllGpaoUserGroups() {
        return entityManager().createQuery("SELECT o FROM GpaoUserGroup o", GpaoUserGroup.class).getResultList();
    }
    
    public static GpaoUserGroup GpaoUserGroup.findGpaoUserGroup(Long id) {
        if (id == null) return null;
        return entityManager().find(GpaoUserGroup.class, id);
    }
    
    public static List<GpaoUserGroup> GpaoUserGroup.findGpaoUserGroupEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM GpaoUserGroup o", GpaoUserGroup.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public GpaoUserGroup GpaoUserGroup.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        GpaoUserGroup merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
