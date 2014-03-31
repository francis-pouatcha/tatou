// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.DeliveryItem;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect DeliveryItem_Roo_Jpa_ActiveRecord {
    
    public static final List<String> DeliveryItem.fieldNames4OrderClauseFilter = java.util.Arrays.asList("product", "orderedQty", "deliveredQty", "undeliveredQty", "delivery", "amountHt", "expirationDate", "udm");
    
    public static long DeliveryItem.countDeliveryItems() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DeliveryItem o", Long.class).getSingleResult();
    }
    
    public static List<DeliveryItem> DeliveryItem.findAllDeliveryItems() {
        return entityManager().createQuery("SELECT o FROM DeliveryItem o", DeliveryItem.class).getResultList();
    }
    
    public static List<DeliveryItem> DeliveryItem.findAllDeliveryItems(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM DeliveryItem o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, DeliveryItem.class).getResultList();
    }
    
    public static DeliveryItem DeliveryItem.findDeliveryItem(Long id) {
        if (id == null) return null;
        return entityManager().find(DeliveryItem.class, id);
    }
    
    public static List<DeliveryItem> DeliveryItem.findDeliveryItemEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DeliveryItem o", DeliveryItem.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<DeliveryItem> DeliveryItem.findDeliveryItemEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM DeliveryItem o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, DeliveryItem.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public DeliveryItem DeliveryItem.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DeliveryItem merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
