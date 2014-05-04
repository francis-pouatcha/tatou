// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.Production;
import cm.adorsys.gpao.model.ProductionTypeConfig;
import java.util.Date;

privileged aspect Production_Roo_JavaBean {
    
    public String Production.getReference() {
        return this.reference;
    }
    
    public void Production.setReference(String reference) {
        this.reference = reference;
    }
    
    public ManufacturingVoucher Production.getManufacturingVoucher() {
        return this.manufacturingVoucher;
    }
    
    public void Production.setManufacturingVoucher(ManufacturingVoucher manufacturingVoucher) {
        this.manufacturingVoucher = manufacturingVoucher;
    }
    
    public Date Production.getStartDate() {
        return this.startDate;
    }
    
    public void Production.setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date Production.getEndDate() {
        return this.endDate;
    }
    
    public void Production.setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public ProductionTypeConfig Production.getProductionTypeConfig() {
        return this.productionTypeConfig;
    }
    
    public void Production.setProductionTypeConfig(ProductionTypeConfig productionTypeConfig) {
        this.productionTypeConfig = productionTypeConfig;
    }
    
    public DocumentStates Production.getProductionState() {
        return this.productionState;
    }
    
    public void Production.setProductionState(DocumentStates productionState) {
        this.productionState = productionState;
    }
    
    public String Production.getUserName() {
        return this.userName;
    }
    
    public void Production.setUserName(String userName) {
        this.userName = userName;
    }
    
}
