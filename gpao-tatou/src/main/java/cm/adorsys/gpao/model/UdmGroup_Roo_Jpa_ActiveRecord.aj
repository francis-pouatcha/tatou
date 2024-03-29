// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.UdmGroup;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect UdmGroup_Roo_Jpa_ActiveRecord {
    
    public static final List<String> UdmGroup.fieldNames4OrderClauseFilter = java.util.Arrays.asList("name", "description", "unitOfMesures");
    
    public static long UdmGroup.countUdmGroups() {
        return entityManager().createQuery("SELECT COUNT(o) FROM UdmGroup o", Long.class).getSingleResult();
    }
    
    public static List<UdmGroup> UdmGroup.findAllUdmGroups() {
        return entityManager().createQuery("SELECT o FROM UdmGroup o", UdmGroup.class).getResultList();
    }
    
    public static List<UdmGroup> UdmGroup.findAllUdmGroups(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM UdmGroup o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, UdmGroup.class).getResultList();
    }
    
    public static UdmGroup UdmGroup.findUdmGroup(Long id) {
        if (id == null) return null;
        return entityManager().find(UdmGroup.class, id);
    }
    
    public static List<UdmGroup> UdmGroup.findUdmGroupEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM UdmGroup o", UdmGroup.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<UdmGroup> UdmGroup.findUdmGroupEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM UdmGroup o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, UdmGroup.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public UdmGroup UdmGroup.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        UdmGroup merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
