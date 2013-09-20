// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.Tenders;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

privileged aspect PurchaseOrder_Roo_JavaBean {
    
    public String PurchaseOrder.getReference() {
        return this.reference;
    }
    
    public void PurchaseOrder.setReference(String reference) {
        this.reference = reference;
    }
    
    public Partner PurchaseOrder.getSupplier() {
        return this.supplier;
    }
    
    public void PurchaseOrder.setSupplier(Partner supplier) {
        this.supplier = supplier;
    }
    
    public Date PurchaseOrder.getOrderDate() {
        return this.orderDate;
    }
    
    public void PurchaseOrder.setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public Boolean PurchaseOrder.getReceived() {
        return this.received;
    }
    
    public void PurchaseOrder.setReceived(Boolean received) {
        this.received = received;
    }
    
    public Boolean PurchaseOrder.getInvoiced() {
        return this.invoiced;
    }
    
    public void PurchaseOrder.setInvoiced(Boolean invoiced) {
        this.invoiced = invoiced;
    }
    
    public Boolean PurchaseOrder.getIsValided() {
        return this.isValided;
    }
    
    public void PurchaseOrder.setIsValided(Boolean isValided) {
        this.isValided = isValided;
    }
    
    public String PurchaseOrder.getValidatedBy() {
        return this.validatedBy;
    }
    
    public void PurchaseOrder.setValidatedBy(String validatedBy) {
        this.validatedBy = validatedBy;
    }
    
    public Date PurchaseOrder.getValidateDate() {
        return this.validateDate;
    }
    
    public void PurchaseOrder.setValidateDate(Date validateDate) {
        this.validateDate = validateDate;
    }
    
    public BigDecimal PurchaseOrder.getAmountHt() {
        return this.amountHt;
    }
    
    public void PurchaseOrder.setAmountHt(BigDecimal amountHt) {
        this.amountHt = amountHt;
    }
    
    public BigDecimal PurchaseOrder.getTaxeAmount() {
        return this.taxeAmount;
    }
    
    public void PurchaseOrder.setTaxeAmount(BigDecimal taxeAmount) {
        this.taxeAmount = taxeAmount;
    }
    
    public BigDecimal PurchaseOrder.getTotalAmount() {
        return this.totalAmount;
    }
    
    public void PurchaseOrder.setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public DocumentStates PurchaseOrder.getOrderState() {
        return this.orderState;
    }
    
    public void PurchaseOrder.setOrderState(DocumentStates orderState) {
        this.orderState = orderState;
    }
    
    public String PurchaseOrder.getCreatedBy() {
        return this.createdBy;
    }
    
    public void PurchaseOrder.setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public Date PurchaseOrder.getCreated() {
        return this.created;
    }
    
    public void PurchaseOrder.setCreated(Date created) {
        this.created = created;
    }
    
    public Devise PurchaseOrder.getCurrency() {
        return this.currency;
    }
    
    public void PurchaseOrder.setCurrency(Devise currency) {
        this.currency = currency;
    }
    
    public Set<OrderItems> PurchaseOrder.getOrderItems() {
        return this.orderItems;
    }
    
    public void PurchaseOrder.setOrderItems(Set<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }
    
    public Company PurchaseOrder.getCompany() {
        return this.company;
    }
    
    public void PurchaseOrder.setCompany(Company company) {
        this.company = company;
    }
    
    public Tenders PurchaseOrder.getTender() {
        return this.tender;
    }
    
    public void PurchaseOrder.setTender(Tenders tender) {
        this.tender = tender;
    }
    
}
