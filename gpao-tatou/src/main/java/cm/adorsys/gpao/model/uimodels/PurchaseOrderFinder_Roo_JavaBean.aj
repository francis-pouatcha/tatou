// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model.uimodels;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.uimodels.PurchaseOrderFinder;
import java.util.Date;

privileged aspect PurchaseOrderFinder_Roo_JavaBean {
    
    public String PurchaseOrderFinder.getReference() {
        return this.reference;
    }
    
    public void PurchaseOrderFinder.setReference(String reference) {
        this.reference = reference;
    }
    
    public String PurchaseOrderFinder.getTenderRef() {
        return this.tenderRef;
    }
    
    public void PurchaseOrderFinder.setTenderRef(String tenderRef) {
        this.tenderRef = tenderRef;
    }
    
    public Date PurchaseOrderFinder.getBeginDate() {
        return this.beginDate;
    }
    
    public void PurchaseOrderFinder.setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    
    public Date PurchaseOrderFinder.getEndDate() {
        return this.endDate;
    }
    
    public void PurchaseOrderFinder.setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public Partner PurchaseOrderFinder.getSupplier() {
        return this.supplier;
    }
    
    public void PurchaseOrderFinder.setSupplier(Partner supplier) {
        this.supplier = supplier;
    }
    
    public DocumentStates PurchaseOrderFinder.getOrderState() {
        return this.orderState;
    }
    
    public void PurchaseOrderFinder.setOrderState(DocumentStates orderState) {
        this.orderState = orderState;
    }
    
}
