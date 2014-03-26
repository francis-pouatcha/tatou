// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Supply;
import cm.adorsys.gpao.model.SupplyItems;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect SupplyItems_Roo_Finder {
    
    public static Long SupplyItems.countFindSupplyItemsesBySupply(Supply supply) {
        if (supply == null) throw new IllegalArgumentException("The supply argument is required");
        EntityManager em = SupplyItems.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM SupplyItems AS o WHERE o.supply = :supply", Long.class);
        q.setParameter("supply", supply);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<SupplyItems> SupplyItems.findSupplyItemsesBySupply(Supply supply) {
        if (supply == null) throw new IllegalArgumentException("The supply argument is required");
        EntityManager em = SupplyItems.entityManager();
        TypedQuery<SupplyItems> q = em.createQuery("SELECT o FROM SupplyItems AS o WHERE o.supply = :supply", SupplyItems.class);
        q.setParameter("supply", supply);
        return q;
    }
    
    public static TypedQuery<SupplyItems> SupplyItems.findSupplyItemsesBySupply(Supply supply, String sortFieldName, String sortOrder) {
        if (supply == null) throw new IllegalArgumentException("The supply argument is required");
        EntityManager em = SupplyItems.entityManager();
        String jpaQuery = "SELECT o FROM SupplyItems AS o WHERE o.supply = :supply";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<SupplyItems> q = em.createQuery(jpaQuery, SupplyItems.class);
        q.setParameter("supply", supply);
        return q;
    }
    
}
