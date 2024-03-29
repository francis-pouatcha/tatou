// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Taxe;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

privileged aspect Delivery_Roo_JavaBean {
    
    public String Delivery.getReference() {
        return this.reference;
    }
    
    public void Delivery.setReference(String reference) {
        this.reference = reference;
    }
    
    public String Delivery.getCreateBy() {
        return this.createBy;
    }
    
    public void Delivery.setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    public String Delivery.getReceiveBy() {
        return this.receiveBy;
    }
    
    public void Delivery.setReceiveBy(String receiveBy) {
        this.receiveBy = receiveBy;
    }
    
    public Date Delivery.getReceivedDate() {
        return this.receivedDate;
    }
    
    public void Delivery.setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }
    
    public Date Delivery.getCreatedate() {
        return this.createdate;
    }
    
    public void Delivery.setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    
    public DeliveryOrigin Delivery.getOrigin() {
        return this.origin;
    }
    
    public void Delivery.setOrigin(DeliveryOrigin origin) {
        this.origin = origin;
    }
    
    public DocumentStates Delivery.getStatus() {
        return this.status;
    }
    
    public void Delivery.setStatus(DocumentStates status) {
        this.status = status;
    }
    
    public BigDecimal Delivery.getUnTaxeAmount() {
        return this.unTaxeAmount;
    }
    
    public void Delivery.setUnTaxeAmount(BigDecimal unTaxeAmount) {
        this.unTaxeAmount = unTaxeAmount;
    }
    
    public BigDecimal Delivery.getTaxAmount() {
        return this.taxAmount;
    }
    
    public void Delivery.setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }
    
    public BigDecimal Delivery.getTaxedAmount() {
        return this.taxedAmount;
    }
    
    public void Delivery.setTaxedAmount(BigDecimal taxedAmount) {
        this.taxedAmount = taxedAmount;
    }
    
    public Set<DeliveryItems> Delivery.getDeliveryItems() {
        return this.deliveryItems;
    }
    
    public void Delivery.setDeliveryItems(Set<DeliveryItems> deliveryItems) {
        this.deliveryItems = deliveryItems;
    }
    
    public Devise Delivery.getCurrency() {
        return this.currency;
    }
    
    public void Delivery.setCurrency(Devise currency) {
        this.currency = currency;
    }
    
    public Company Delivery.getCompany() {
        return this.company;
    }
    
    public void Delivery.setCompany(Company company) {
        this.company = company;
    }
    
    public String Delivery.getDocRef() {
        return this.docRef;
    }
    
    public void Delivery.setDocRef(String docRef) {
        this.docRef = docRef;
    }
    
    public Set<Taxe> Delivery.getTaxes() {
        return this.taxes;
    }
    
    public void Delivery.setTaxes(Set<Taxe> taxes) {
        this.taxes = taxes;
    }
    
}
