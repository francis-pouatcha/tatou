// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model.uimodels;

import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.uimodels.DeliveryFinder;
import java.util.Date;

privileged aspect DeliveryFinder_Roo_JavaBean {
    
    public String DeliveryFinder.getReference() {
        return this.reference;
    }
    
    public void DeliveryFinder.setReference(String reference) {
        this.reference = reference;
    }
    
    public DocumentStates DeliveryFinder.getStatus() {
        return this.status;
    }
    
    public void DeliveryFinder.setStatus(DocumentStates status) {
        this.status = status;
    }
    
    public DeliveryOrigin DeliveryFinder.getOrigin() {
        return this.origin;
    }
    
    public void DeliveryFinder.setOrigin(DeliveryOrigin origin) {
        this.origin = origin;
    }
    
    public String DeliveryFinder.getDocRef() {
        return this.docRef;
    }
    
    public void DeliveryFinder.setDocRef(String docRef) {
        this.docRef = docRef;
    }
    
    public String DeliveryFinder.getReceiveBy() {
        return this.receiveBy;
    }
    
    public void DeliveryFinder.setReceiveBy(String receiveBy) {
        this.receiveBy = receiveBy;
    }
    
    public Date DeliveryFinder.getBeginReceivedDate() {
        return this.beginReceivedDate;
    }
    
    public void DeliveryFinder.setBeginReceivedDate(Date beginReceivedDate) {
        this.beginReceivedDate = beginReceivedDate;
    }
    
    public Date DeliveryFinder.getEndReceivedDate() {
        return this.endReceivedDate;
    }
    
    public void DeliveryFinder.setEndReceivedDate(Date endReceivedDate) {
        this.endReceivedDate = endReceivedDate;
    }
    
}
