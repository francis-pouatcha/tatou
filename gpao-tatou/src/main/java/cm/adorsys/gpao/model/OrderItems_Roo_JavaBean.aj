// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.UnitOfMesures;
import java.math.BigDecimal;

privileged aspect OrderItems_Roo_JavaBean {
    
    public String OrderItems.getReference() {
        return this.reference;
    }
    
    public void OrderItems.setReference(String reference) {
        this.reference = reference;
    }
    
    public Product OrderItems.getProduct() {
        return this.product;
    }
    
    public void OrderItems.setProduct(Product product) {
        this.product = product;
    }
    
    public UnitOfMesures OrderItems.getUdm() {
        return this.udm;
    }
    
    public void OrderItems.setUdm(UnitOfMesures udm) {
        this.udm = udm;
    }
    
    public BigDecimal OrderItems.getQuantity() {
        return this.quantity;
    }
    
    public void OrderItems.setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal OrderItems.getSubTotal() {
        return this.subTotal;
    }
    
    public void OrderItems.setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
    
    public PurchaseOrder OrderItems.getPurchaseOrder() {
        return this.purchaseOrder;
    }
    
    public void OrderItems.setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }
    
}
