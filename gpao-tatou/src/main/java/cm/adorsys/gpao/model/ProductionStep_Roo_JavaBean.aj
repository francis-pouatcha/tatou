// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Production;
import cm.adorsys.gpao.model.ProductionStep;
import cm.adorsys.gpao.model.ProductionStepConfig;
import java.util.Date;

privileged aspect ProductionStep_Roo_JavaBean {
    
    public Production ProductionStep.getProduction() {
        return this.production;
    }
    
    public void ProductionStep.setProduction(Production production) {
        this.production = production;
    }
    
    public ProductionStepConfig ProductionStep.getProductionStepConfig() {
        return this.productionStepConfig;
    }
    
    public void ProductionStep.setProductionStepConfig(ProductionStepConfig productionStepConfig) {
        this.productionStepConfig = productionStepConfig;
    }
    
    public Date ProductionStep.getStartDate() {
        return this.startDate;
    }
    
    public void ProductionStep.setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date ProductionStep.getEndDate() {
        return this.endDate;
    }
    
    public void ProductionStep.setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public DocumentStates ProductionStep.getStepState() {
        return this.stepState;
    }
    
    public void ProductionStep.setStepState(DocumentStates stepState) {
        this.stepState = stepState;
    }
    
}
