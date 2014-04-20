// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.RawMaterialDeliveryNote;
import cm.adorsys.gpao.model.RawMaterialDeliveryNoteItem;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect RawMaterialDeliveryNoteItem_Roo_Finder {
    
    public static Long RawMaterialDeliveryNoteItem.countFindRawMaterialDeliveryNoteItemsByRawMaterial(Product rawMaterial) {
        if (rawMaterial == null) throw new IllegalArgumentException("The rawMaterial argument is required");
        EntityManager em = RawMaterialDeliveryNoteItem.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM RawMaterialDeliveryNoteItem AS o WHERE o.rawMaterial = :rawMaterial", Long.class);
        q.setParameter("rawMaterial", rawMaterial);
        return ((Long) q.getSingleResult());
    }
    
    public static Long RawMaterialDeliveryNoteItem.countFindRawMaterialDeliveryNoteItemsByRawMaterialDelveryNote(RawMaterialDeliveryNote rawMaterialDelveryNote) {
        if (rawMaterialDelveryNote == null) throw new IllegalArgumentException("The rawMaterialDelveryNote argument is required");
        EntityManager em = RawMaterialDeliveryNoteItem.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM RawMaterialDeliveryNoteItem AS o WHERE o.rawMaterialDelveryNote = :rawMaterialDelveryNote", Long.class);
        q.setParameter("rawMaterialDelveryNote", rawMaterialDelveryNote);
        return ((Long) q.getSingleResult());
    }
    
    public static TypedQuery<RawMaterialDeliveryNoteItem> RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemsByRawMaterial(Product rawMaterial) {
        if (rawMaterial == null) throw new IllegalArgumentException("The rawMaterial argument is required");
        EntityManager em = RawMaterialDeliveryNoteItem.entityManager();
        TypedQuery<RawMaterialDeliveryNoteItem> q = em.createQuery("SELECT o FROM RawMaterialDeliveryNoteItem AS o WHERE o.rawMaterial = :rawMaterial", RawMaterialDeliveryNoteItem.class);
        q.setParameter("rawMaterial", rawMaterial);
        return q;
    }
    
    public static TypedQuery<RawMaterialDeliveryNoteItem> RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemsByRawMaterial(Product rawMaterial, String sortFieldName, String sortOrder) {
        if (rawMaterial == null) throw new IllegalArgumentException("The rawMaterial argument is required");
        EntityManager em = RawMaterialDeliveryNoteItem.entityManager();
        String jpaQuery = "SELECT o FROM RawMaterialDeliveryNoteItem AS o WHERE o.rawMaterial = :rawMaterial";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<RawMaterialDeliveryNoteItem> q = em.createQuery(jpaQuery, RawMaterialDeliveryNoteItem.class);
        q.setParameter("rawMaterial", rawMaterial);
        return q;
    }
    
    public static TypedQuery<RawMaterialDeliveryNoteItem> RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemsByRawMaterialDelveryNote(RawMaterialDeliveryNote rawMaterialDelveryNote) {
        if (rawMaterialDelveryNote == null) throw new IllegalArgumentException("The rawMaterialDelveryNote argument is required");
        EntityManager em = RawMaterialDeliveryNoteItem.entityManager();
        TypedQuery<RawMaterialDeliveryNoteItem> q = em.createQuery("SELECT o FROM RawMaterialDeliveryNoteItem AS o WHERE o.rawMaterialDelveryNote = :rawMaterialDelveryNote", RawMaterialDeliveryNoteItem.class);
        q.setParameter("rawMaterialDelveryNote", rawMaterialDelveryNote);
        return q;
    }
    
    public static TypedQuery<RawMaterialDeliveryNoteItem> RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemsByRawMaterialDelveryNote(RawMaterialDeliveryNote rawMaterialDelveryNote, String sortFieldName, String sortOrder) {
        if (rawMaterialDelveryNote == null) throw new IllegalArgumentException("The rawMaterialDelveryNote argument is required");
        EntityManager em = RawMaterialDeliveryNoteItem.entityManager();
        String jpaQuery = "SELECT o FROM RawMaterialDeliveryNoteItem AS o WHERE o.rawMaterialDelveryNote = :rawMaterialDelveryNote";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<RawMaterialDeliveryNoteItem> q = em.createQuery(jpaQuery, RawMaterialDeliveryNoteItem.class);
        q.setParameter("rawMaterialDelveryNote", rawMaterialDelveryNote);
        return q;
    }
    
}
