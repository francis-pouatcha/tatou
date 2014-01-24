// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect DeliveryItems_Roo_Finder {
    
    public static Long DeliveryItems.countFindDeliveryItemsesByDelivery(Delivery delivery) {
        if (delivery == null) throw new IllegalArgumentException("The delivery argument is required");
        EntityManager em = DeliveryItems.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM DeliveryItems AS o WHERE o.delivery = :delivery", Long.class);
        q.setParameter("delivery", delivery);
        return ((Long) q.getSingleResult());
    }
    
    public static Long DeliveryItems.countFindDeliveryItemsesByReferenceEquals(String reference) {
        if (reference == null || reference.length() == 0) throw new IllegalArgumentException("The reference argument is required");
        EntityManager em = DeliveryItems.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM DeliveryItems AS o WHERE o.reference = :reference", Long.class);
        q.setParameter("reference", reference);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<DeliveryItems> DeliveryItems.findDeliveryItemsesByDelivery(Delivery delivery) {
        if (delivery == null) throw new IllegalArgumentException("The delivery argument is required");
        EntityManager em = DeliveryItems.entityManager();
        TypedQuery<DeliveryItems> q = em.createQuery("SELECT o FROM DeliveryItems AS o WHERE o.delivery = :delivery", DeliveryItems.class);
        q.setParameter("delivery", delivery);
        return q;
    }
    
    public static TypedQuery<DeliveryItems> DeliveryItems.findDeliveryItemsesByDelivery(Delivery delivery, String sortFieldName, String sortOrder) {
        if (delivery == null) throw new IllegalArgumentException("The delivery argument is required");
        EntityManager em = DeliveryItems.entityManager();
        String jpaQuery = "SELECT o FROM DeliveryItems AS o WHERE o.delivery = :delivery";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<DeliveryItems> q = em.createQuery(jpaQuery, DeliveryItems.class);
        q.setParameter("delivery", delivery);
        return q;
    }
    
    public static TypedQuery<DeliveryItems> DeliveryItems.findDeliveryItemsesByReferenceEquals(String reference) {
        if (reference == null || reference.length() == 0) throw new IllegalArgumentException("The reference argument is required");
        EntityManager em = DeliveryItems.entityManager();
        TypedQuery<DeliveryItems> q = em.createQuery("SELECT o FROM DeliveryItems AS o WHERE o.reference = :reference", DeliveryItems.class);
        q.setParameter("reference", reference);
        return q;
    }
    
    public static TypedQuery<DeliveryItems> DeliveryItems.findDeliveryItemsesByReferenceEquals(String reference, String sortFieldName, String sortOrder) {
        if (reference == null || reference.length() == 0) throw new IllegalArgumentException("The reference argument is required");
        EntityManager em = DeliveryItems.entityManager();
        String jpaQuery = "SELECT o FROM DeliveryItems AS o WHERE o.reference = :reference";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<DeliveryItems> q = em.createQuery(jpaQuery, DeliveryItems.class);
        q.setParameter("reference", reference);
        return q;
    }
    
}