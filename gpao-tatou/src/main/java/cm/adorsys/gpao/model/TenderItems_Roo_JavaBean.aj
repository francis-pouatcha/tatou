// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.TenderItems;
import cm.adorsys.gpao.model.Tenders;
import cm.adorsys.gpao.model.UnitOfMesures;
import java.math.BigDecimal;

privileged aspect TenderItems_Roo_JavaBean {
    
    public Product TenderItems.getProducts() {
        return this.products;
    }
    
    public void TenderItems.setProducts(Product products) {
        this.products = products;
    }
    
    public BigDecimal TenderItems.getQuantity() {
        return this.quantity;
    }
    
    public void TenderItems.setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    public UnitOfMesures TenderItems.getUdm() {
        return this.udm;
    }
    
    public void TenderItems.setUdm(UnitOfMesures udm) {
        this.udm = udm;
    }
    
    public Tenders TenderItems.getTender() {
        return this.tender;
    }
    
    public void TenderItems.setTender(Tenders tender) {
        this.tender = tender;
    }
    
}
