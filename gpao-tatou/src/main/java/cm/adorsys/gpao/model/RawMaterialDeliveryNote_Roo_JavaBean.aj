// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.RawMaterialDeliveryNote;
import java.util.Date;

privileged aspect RawMaterialDeliveryNote_Roo_JavaBean {
    
    public String RawMaterialDeliveryNote.getReference() {
        return this.reference;
    }
    
    public void RawMaterialDeliveryNote.setReference(String reference) {
        this.reference = reference;
    }
    
    public Date RawMaterialDeliveryNote.getOrderDate() {
        return this.orderDate;
    }
    
    public void RawMaterialDeliveryNote.setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public String RawMaterialDeliveryNote.getCreatedBy() {
        return this.createdBy;
    }
    
    public void RawMaterialDeliveryNote.setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public Boolean RawMaterialDeliveryNote.getValidated() {
        return this.validated;
    }
    
    public void RawMaterialDeliveryNote.setValidated(Boolean validated) {
        this.validated = validated;
    }
    
    public DocumentStates RawMaterialDeliveryNote.getOrderState() {
        return this.orderState;
    }
    
    public void RawMaterialDeliveryNote.setOrderState(DocumentStates orderState) {
        this.orderState = orderState;
    }
    
    public String RawMaterialDeliveryNote.getDocRef() {
        return this.docRef;
    }
    
    public void RawMaterialDeliveryNote.setDocRef(String docRef) {
        this.docRef = docRef;
    }
    
    public DeliveryOrigin RawMaterialDeliveryNote.getOrigin() {
        return this.origin;
    }
    
    public void RawMaterialDeliveryNote.setOrigin(DeliveryOrigin origin) {
        this.origin = origin;
    }
    
}