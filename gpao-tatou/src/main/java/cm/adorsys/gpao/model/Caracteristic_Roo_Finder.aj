// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Caracteristic;
import cm.adorsys.gpao.model.Product;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Caracteristic_Roo_Finder {
    
    public static Long Caracteristic.countFindCaracteristicsByProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("The product argument is required");
        EntityManager em = Caracteristic.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Caracteristic AS o WHERE o.product = :product", Long.class);
        q.setParameter("product", product);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<Caracteristic> Caracteristic.findCaracteristicsByProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("The product argument is required");
        EntityManager em = Caracteristic.entityManager();
        TypedQuery<Caracteristic> q = em.createQuery("SELECT o FROM Caracteristic AS o WHERE o.product = :product", Caracteristic.class);
        q.setParameter("product", product);
        return q;
    }
    
    public static TypedQuery<Caracteristic> Caracteristic.findCaracteristicsByProduct(Product product, String sortFieldName, String sortOrder) {
        if (product == null) throw new IllegalArgumentException("The product argument is required");
        EntityManager em = Caracteristic.entityManager();
        String jpaQuery = "SELECT o FROM Caracteristic AS o WHERE o.product = :product";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Caracteristic> q = em.createQuery(jpaQuery, Caracteristic.class);
        q.setParameter("product", product);
        return q;
    }
    
}
