// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductIntrant;
import cm.adorsys.gpao.model.UnitOfMesures;
import java.math.BigDecimal;

privileged aspect ProductIntrant_Roo_JavaBean {
    
    public Product ProductIntrant.getProduct() {
        return this.product;
    }
    
    public void ProductIntrant.setProduct(Product product) {
        this.product = product;
    }
    
    public BigDecimal ProductIntrant.getQuantity() {
        return this.quantity;
    }
    
    public void ProductIntrant.setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    public Product ProductIntrant.getRawMaterial() {
        return this.rawMaterial;
    }
    
    public void ProductIntrant.setRawMaterial(Product rawMaterial) {
        this.rawMaterial = rawMaterial;
    }
    
    public UnitOfMesures ProductIntrant.getUdm() {
        return this.udm;
    }
    
    public void ProductIntrant.setUdm(UnitOfMesures udm) {
        this.udm = udm;
    }
    
}