// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.ManufacturingVoucher;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ManufacturingVoucher_Roo_Jpa_ActiveRecord {
    
    public static final List<String> ManufacturingVoucher.fieldNames4OrderClauseFilter = java.util.Arrays.asList("reference", "createDate", "createdBy", "documentState", "customerOrder", "customer", "delayDate");
    
    public static long ManufacturingVoucher.countManufacturingVouchers() {
        return entityManager().createQuery("SELECT COUNT(o) FROM ManufacturingVoucher o", Long.class).getSingleResult();
    }
    
    public static List<ManufacturingVoucher> ManufacturingVoucher.findAllManufacturingVouchers() {
        return entityManager().createQuery("SELECT o FROM ManufacturingVoucher o", ManufacturingVoucher.class).getResultList();
    }
    
    public static List<ManufacturingVoucher> ManufacturingVoucher.findAllManufacturingVouchers(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ManufacturingVoucher o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ManufacturingVoucher.class).getResultList();
    }
    
    public static ManufacturingVoucher ManufacturingVoucher.findManufacturingVoucher(Long id) {
        if (id == null) return null;
        return entityManager().find(ManufacturingVoucher.class, id);
    }
    
    public static List<ManufacturingVoucher> ManufacturingVoucher.findManufacturingVoucherEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM ManufacturingVoucher o", ManufacturingVoucher.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<ManufacturingVoucher> ManufacturingVoucher.findManufacturingVoucherEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM ManufacturingVoucher o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, ManufacturingVoucher.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public ManufacturingVoucher ManufacturingVoucher.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        ManufacturingVoucher merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}