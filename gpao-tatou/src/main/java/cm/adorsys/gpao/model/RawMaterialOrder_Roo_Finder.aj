// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.RawMaterialOrder;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect RawMaterialOrder_Roo_Finder {
    
    public static Long RawMaterialOrder.countFindRawMaterialOrdersByDeliveredNot(Boolean delivered) {
        if (delivered == null) throw new IllegalArgumentException("The delivered argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM RawMaterialOrder AS o WHERE o.delivered IS NOT :delivered", Long.class);
        q.setParameter("delivered", delivered);
        return ((Long) q.getSingleResult());
    }
    
    public static Long RawMaterialOrder.countFindRawMaterialOrdersByDocRefEquals(String docRef) {
        if (docRef == null || docRef.length() == 0) throw new IllegalArgumentException("The docRef argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM RawMaterialOrder AS o WHERE o.docRef = :docRef", Long.class);
        q.setParameter("docRef", docRef);
        return ((Long) q.getSingleResult());
    }
    
    public static Long RawMaterialOrder.countFindRawMaterialOrdersByReferenceEquals(String reference) {
        if (reference == null || reference.length() == 0) throw new IllegalArgumentException("The reference argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM RawMaterialOrder AS o WHERE o.reference = :reference", Long.class);
        q.setParameter("reference", reference);
        return ((Long) q.getSingleResult());
    }
    
    public static Long RawMaterialOrder.countFindRawMaterialOrdersByValidatedByEquals(String validatedBy) {
        if (validatedBy == null || validatedBy.length() == 0) throw new IllegalArgumentException("The validatedBy argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM RawMaterialOrder AS o WHERE o.validatedBy = :validatedBy", Long.class);
        q.setParameter("validatedBy", validatedBy);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<RawMaterialOrder> RawMaterialOrder.findRawMaterialOrdersByDeliveredNot(Boolean delivered) {
        if (delivered == null) throw new IllegalArgumentException("The delivered argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        TypedQuery<RawMaterialOrder> q = em.createQuery("SELECT o FROM RawMaterialOrder AS o WHERE o.delivered IS NOT :delivered", RawMaterialOrder.class);
        q.setParameter("delivered", delivered);
        return q;
    }
    
    public static TypedQuery<RawMaterialOrder> RawMaterialOrder.findRawMaterialOrdersByDeliveredNot(Boolean delivered, String sortFieldName, String sortOrder) {
        if (delivered == null) throw new IllegalArgumentException("The delivered argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        String jpaQuery = "SELECT o FROM RawMaterialOrder AS o WHERE o.delivered IS NOT :delivered";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<RawMaterialOrder> q = em.createQuery(jpaQuery, RawMaterialOrder.class);
        q.setParameter("delivered", delivered);
        return q;
    }
    
    public static TypedQuery<RawMaterialOrder> RawMaterialOrder.findRawMaterialOrdersByDocRefEquals(String docRef) {
        if (docRef == null || docRef.length() == 0) throw new IllegalArgumentException("The docRef argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        TypedQuery<RawMaterialOrder> q = em.createQuery("SELECT o FROM RawMaterialOrder AS o WHERE o.docRef = :docRef", RawMaterialOrder.class);
        q.setParameter("docRef", docRef);
        return q;
    }
    
    public static TypedQuery<RawMaterialOrder> RawMaterialOrder.findRawMaterialOrdersByDocRefEquals(String docRef, String sortFieldName, String sortOrder) {
        if (docRef == null || docRef.length() == 0) throw new IllegalArgumentException("The docRef argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        String jpaQuery = "SELECT o FROM RawMaterialOrder AS o WHERE o.docRef = :docRef";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<RawMaterialOrder> q = em.createQuery(jpaQuery, RawMaterialOrder.class);
        q.setParameter("docRef", docRef);
        return q;
    }
    
    public static TypedQuery<RawMaterialOrder> RawMaterialOrder.findRawMaterialOrdersByReferenceEquals(String reference) {
        if (reference == null || reference.length() == 0) throw new IllegalArgumentException("The reference argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        TypedQuery<RawMaterialOrder> q = em.createQuery("SELECT o FROM RawMaterialOrder AS o WHERE o.reference = :reference", RawMaterialOrder.class);
        q.setParameter("reference", reference);
        return q;
    }
    
    public static TypedQuery<RawMaterialOrder> RawMaterialOrder.findRawMaterialOrdersByReferenceEquals(String reference, String sortFieldName, String sortOrder) {
        if (reference == null || reference.length() == 0) throw new IllegalArgumentException("The reference argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        String jpaQuery = "SELECT o FROM RawMaterialOrder AS o WHERE o.reference = :reference";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<RawMaterialOrder> q = em.createQuery(jpaQuery, RawMaterialOrder.class);
        q.setParameter("reference", reference);
        return q;
    }
    
    public static TypedQuery<RawMaterialOrder> RawMaterialOrder.findRawMaterialOrdersByValidatedByEquals(String validatedBy) {
        if (validatedBy == null || validatedBy.length() == 0) throw new IllegalArgumentException("The validatedBy argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        TypedQuery<RawMaterialOrder> q = em.createQuery("SELECT o FROM RawMaterialOrder AS o WHERE o.validatedBy = :validatedBy", RawMaterialOrder.class);
        q.setParameter("validatedBy", validatedBy);
        return q;
    }
    
    public static TypedQuery<RawMaterialOrder> RawMaterialOrder.findRawMaterialOrdersByValidatedByEquals(String validatedBy, String sortFieldName, String sortOrder) {
        if (validatedBy == null || validatedBy.length() == 0) throw new IllegalArgumentException("The validatedBy argument is required");
        EntityManager em = RawMaterialOrder.entityManager();
        String jpaQuery = "SELECT o FROM RawMaterialOrder AS o WHERE o.validatedBy = :validatedBy";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<RawMaterialOrder> q = em.createQuery(jpaQuery, RawMaterialOrder.class);
        q.setParameter("validatedBy", validatedBy);
        return q;
    }
    
}
