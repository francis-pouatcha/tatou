// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Delivery;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect Delivery_Roo_Finder {
    
    public static Long Delivery.countFindDeliverysByDocRefEquals(String docRef) {
        if (docRef == null || docRef.length() == 0) throw new IllegalArgumentException("The docRef argument is required");
        EntityManager em = Delivery.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Delivery AS o WHERE o.docRef = :docRef", Long.class);
        q.setParameter("docRef", docRef);
        return ((Long) q.getSingleResult());
    }
    
    public static Long Delivery.countFindDeliverysByReferenceEquals(String reference) {
        if (reference == null || reference.length() == 0) throw new IllegalArgumentException("The reference argument is required");
        EntityManager em = Delivery.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Delivery AS o WHERE o.reference = :reference", Long.class);
        q.setParameter("reference", reference);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<Delivery> Delivery.findDeliverysByDocRefEquals(String docRef) {
        if (docRef == null || docRef.length() == 0) throw new IllegalArgumentException("The docRef argument is required");
        EntityManager em = Delivery.entityManager();
        TypedQuery<Delivery> q = em.createQuery("SELECT o FROM Delivery AS o WHERE o.docRef = :docRef", Delivery.class);
        q.setParameter("docRef", docRef);
        return q;
    }
    
    public static TypedQuery<Delivery> Delivery.findDeliverysByDocRefEquals(String docRef, String sortFieldName, String sortOrder) {
        if (docRef == null || docRef.length() == 0) throw new IllegalArgumentException("The docRef argument is required");
        EntityManager em = Delivery.entityManager();
        String jpaQuery = "SELECT o FROM Delivery AS o WHERE o.docRef = :docRef";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Delivery> q = em.createQuery(jpaQuery, Delivery.class);
        q.setParameter("docRef", docRef);
        return q;
    }
    
    public static TypedQuery<Delivery> Delivery.findDeliverysByReferenceEquals(String reference) {
        if (reference == null || reference.length() == 0) throw new IllegalArgumentException("The reference argument is required");
        EntityManager em = Delivery.entityManager();
        TypedQuery<Delivery> q = em.createQuery("SELECT o FROM Delivery AS o WHERE o.reference = :reference", Delivery.class);
        q.setParameter("reference", reference);
        return q;
    }
    
    public static TypedQuery<Delivery> Delivery.findDeliverysByReferenceEquals(String reference, String sortFieldName, String sortOrder) {
        if (reference == null || reference.length() == 0) throw new IllegalArgumentException("The reference argument is required");
        EntityManager em = Delivery.entityManager();
        String jpaQuery = "SELECT o FROM Delivery AS o WHERE o.reference = :reference";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Delivery> q = em.createQuery(jpaQuery, Delivery.class);
        q.setParameter("reference", reference);
        return q;
    }
    
}
