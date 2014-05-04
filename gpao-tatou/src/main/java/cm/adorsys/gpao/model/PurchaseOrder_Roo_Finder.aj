// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.PurchaseOrder;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect PurchaseOrder_Roo_Finder {
    
    public static Long PurchaseOrder.countFindPurchaseOrdersByDocRefEquals(String docRef) {
        if (docRef == null || docRef.length() == 0) throw new IllegalArgumentException("The docRef argument is required");
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM PurchaseOrder AS o WHERE o.docRef = :docRef", Long.class);
        q.setParameter("docRef", docRef);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<PurchaseOrder> PurchaseOrder.findPurchaseOrdersByDocRefEquals(String docRef) {
        if (docRef == null || docRef.length() == 0) throw new IllegalArgumentException("The docRef argument is required");
        EntityManager em = PurchaseOrder.entityManager();
        TypedQuery<PurchaseOrder> q = em.createQuery("SELECT o FROM PurchaseOrder AS o WHERE o.docRef = :docRef", PurchaseOrder.class);
        q.setParameter("docRef", docRef);
        return q;
    }
    
    public static TypedQuery<PurchaseOrder> PurchaseOrder.findPurchaseOrdersByDocRefEquals(String docRef, String sortFieldName, String sortOrder) {
        if (docRef == null || docRef.length() == 0) throw new IllegalArgumentException("The docRef argument is required");
        EntityManager em = PurchaseOrder.entityManager();
        String jpaQuery = "SELECT o FROM PurchaseOrder AS o WHERE o.docRef = :docRef";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<PurchaseOrder> q = em.createQuery(jpaQuery, PurchaseOrder.class);
        q.setParameter("docRef", docRef);
        return q;
    }
    
}