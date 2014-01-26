// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.UnitOfMesures;
import java.math.BigDecimal;
import java.util.Date;

privileged aspect DeliveryItems_Roo_JavaBean {
    
    public String DeliveryItems.getReference() {
        return this.reference;
    }
    
    public void DeliveryItems.setReference(String reference) {
        this.reference = reference;
    }
    
    public Product DeliveryItems.getProduct() {
        return this.product;
    }
    
    public void DeliveryItems.setProduct(Product product) {
        this.product = product;
    }
    
    public BigDecimal DeliveryItems.getOrderQte() {
        return this.orderQte;
    }
    
    public void DeliveryItems.setOrderQte(BigDecimal orderQte) {
        this.orderQte = orderQte;
    }
    
    public BigDecimal DeliveryItems.getQteReceive() {
        return this.qteReceive;
    }
    
    public void DeliveryItems.setQteReceive(BigDecimal qteReceive) {
        this.qteReceive = qteReceive;
    }
    
    public BigDecimal DeliveryItems.getQteUnreceive() {
        return this.qteUnreceive;
    }
    
    public void DeliveryItems.setQteUnreceive(BigDecimal qteUnreceive) {
        this.qteUnreceive = qteUnreceive;
    }
    
    public BigDecimal DeliveryItems.getQteInStock() {
        return this.qteInStock;
    }
    
    public void DeliveryItems.setQteInStock(BigDecimal qteInStock) {
        this.qteInStock = qteInStock;
    }
    
    public Delivery DeliveryItems.getDelivery() {
        return this.delivery;
    }
    
    public void DeliveryItems.setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    
    public BigDecimal DeliveryItems.getAmountHt() {
        return this.amountHt;
    }
    
    public void DeliveryItems.setAmountHt(BigDecimal amountHt) {
        this.amountHt = amountHt;
    }
    
    public Date DeliveryItems.getExpirationDate() {
        return this.expirationDate;
    }
    
    public void DeliveryItems.setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public UnitOfMesures DeliveryItems.getUdm() {
        return this.udm;
    }
    
    public void DeliveryItems.setUdm(UnitOfMesures udm) {
        this.udm = udm;
    }
    
}
