// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.ProductFamily;
import cm.adorsys.gpao.model.ProductSubFamily;
import java.util.Set;

privileged aspect ProductFamily_Roo_JavaBean {
    
    public String ProductFamily.getName() {
        return this.name;
    }
    
    public void ProductFamily.setName(String name) {
        this.name = name;
    }
    
    public String ProductFamily.getDescription() {
        return this.description;
    }
    
    public void ProductFamily.setDescription(String description) {
        this.description = description;
    }
    
    public Set<ProductSubFamily> ProductFamily.getProductSubFamily() {
        return this.productSubFamily;
    }
    
    public void ProductFamily.setProductSubFamily(Set<ProductSubFamily> productSubFamily) {
        this.productSubFamily = productSubFamily;
    }
    
}
