// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.UnitOfMesures;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect UnitOfMesures_Roo_Jpa_Entity {
    
    declare @type: UnitOfMesures: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long UnitOfMesures.id;
    
    @Version
    @Column(name = "version")
    private Integer UnitOfMesures.version;
    
    public Long UnitOfMesures.getId() {
        return this.id;
    }
    
    public void UnitOfMesures.setId(Long id) {
        this.id = id;
    }
    
    public Integer UnitOfMesures.getVersion() {
        return this.version;
    }
    
    public void UnitOfMesures.setVersion(Integer version) {
        this.version = version;
    }
    
}