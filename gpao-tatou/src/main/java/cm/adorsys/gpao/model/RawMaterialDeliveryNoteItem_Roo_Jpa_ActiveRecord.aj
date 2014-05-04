// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.RawMaterialDeliveryNoteItem;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect RawMaterialDeliveryNoteItem_Roo_Jpa_ActiveRecord {
    
    public static final List<String> RawMaterialDeliveryNoteItem.fieldNames4OrderClauseFilter = java.util.Arrays.asList("rawMaterial", "orderedQty", "deliveredQty", "undeliveredQty", "rawMaterialDelveryNote");
    
    public static long RawMaterialDeliveryNoteItem.countRawMaterialDeliveryNoteItems() {
        return entityManager().createQuery("SELECT COUNT(o) FROM RawMaterialDeliveryNoteItem o", Long.class).getSingleResult();
    }
    
    public static List<RawMaterialDeliveryNoteItem> RawMaterialDeliveryNoteItem.findAllRawMaterialDeliveryNoteItems() {
        return entityManager().createQuery("SELECT o FROM RawMaterialDeliveryNoteItem o", RawMaterialDeliveryNoteItem.class).getResultList();
    }
    
    public static List<RawMaterialDeliveryNoteItem> RawMaterialDeliveryNoteItem.findAllRawMaterialDeliveryNoteItems(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM RawMaterialDeliveryNoteItem o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, RawMaterialDeliveryNoteItem.class).getResultList();
    }
    
    public static RawMaterialDeliveryNoteItem RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItem(Long id) {
        if (id == null) return null;
        return entityManager().find(RawMaterialDeliveryNoteItem.class, id);
    }
    
    public static List<RawMaterialDeliveryNoteItem> RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM RawMaterialDeliveryNoteItem o", RawMaterialDeliveryNoteItem.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<RawMaterialDeliveryNoteItem> RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM RawMaterialDeliveryNoteItem o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, RawMaterialDeliveryNoteItem.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public RawMaterialDeliveryNoteItem RawMaterialDeliveryNoteItem.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        RawMaterialDeliveryNoteItem merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}