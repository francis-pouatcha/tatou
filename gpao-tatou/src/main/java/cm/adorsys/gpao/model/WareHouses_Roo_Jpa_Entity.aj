// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.WareHouses;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;

privileged aspect WareHouses_Roo_Jpa_Entity {
    
    declare @type: WareHouses: @Entity;
    
    declare @type: WareHouses: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS);
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long WareHouses.id;
    
    @Version
    @Column(name = "version")
    private Integer WareHouses.version;
    
    public Long WareHouses.getId() {
        return this.id;
    }
    
    public void WareHouses.setId(Long id) {
        this.id = id;
    }
    
    public Integer WareHouses.getVersion() {
        return this.version;
    }
    
    public void WareHouses.setVersion(Integer version) {
        this.version = version;
    }
    
}
