// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.PurchaseOrder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect PurchaseOrder_Roo_Jpa_Entity {
    
    declare @type: PurchaseOrder: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long PurchaseOrder.id;
    
    @Version
    @Column(name = "version")
    private Integer PurchaseOrder.version;
    
    public Long PurchaseOrder.getId() {
        return this.id;
    }
    
    public void PurchaseOrder.setId(Long id) {
        this.id = id;
    }
    
    public Integer PurchaseOrder.getVersion() {
        return this.version;
    }
    
    public void PurchaseOrder.setVersion(Integer version) {
        this.version = version;
    }
    
}
