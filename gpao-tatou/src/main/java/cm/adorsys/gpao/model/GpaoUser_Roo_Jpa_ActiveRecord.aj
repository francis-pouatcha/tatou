// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.GpaoUser;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect GpaoUser_Roo_Jpa_ActiveRecord {
    
    public static final List<String> GpaoUser.fieldNames4OrderClauseFilter = java.util.Arrays.asList("serialVersionUID", "userNumber", "gender", "userName", "firstName", "lastName", "fullName", "password", "gpaoUserGroups", "phoneNumber", "accountExpiration", "disableLogin", "accountLocked", "credentialExpiration", "roleNames", "userImage", "userImagePath", "PASSWORD_SALT", "adresse", "email", "company");
    
    public static long GpaoUser.countGpaoUsers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM GpaoUser o", Long.class).getSingleResult();
    }
    
    public static List<GpaoUser> GpaoUser.findAllGpaoUsers() {
        return entityManager().createQuery("SELECT o FROM GpaoUser o", GpaoUser.class).getResultList();
    }
    
    public static List<GpaoUser> GpaoUser.findAllGpaoUsers(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM GpaoUser o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, GpaoUser.class).getResultList();
    }
    
    public static GpaoUser GpaoUser.findGpaoUser(Long id) {
        if (id == null) return null;
        return entityManager().find(GpaoUser.class, id);
    }
    
    public static List<GpaoUser> GpaoUser.findGpaoUserEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM GpaoUser o", GpaoUser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<GpaoUser> GpaoUser.findGpaoUserEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM GpaoUser o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, GpaoUser.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public GpaoUser GpaoUser.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        GpaoUser merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
