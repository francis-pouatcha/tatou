// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductionProductLigneConfig;
import cm.adorsys.gpao.model.ProductionProductLigneType;
import cm.adorsys.gpao.model.ProductionStepConfig;
import cm.adorsys.gpao.model.ProductionTaskConfig;
import java.math.BigDecimal;

privileged aspect ProductionProductLigneConfig_Roo_JavaBean {
    
    public Product ProductionProductLigneConfig.getProduct() {
        return this.product;
    }
    
    public void ProductionProductLigneConfig.setProduct(Product product) {
        this.product = product;
    }
    
    public BigDecimal ProductionProductLigneConfig.getQuantity() {
        return this.quantity;
    }
    
    public void ProductionProductLigneConfig.setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    public ProductionProductLigneType ProductionProductLigneConfig.getProductLigneType() {
        return this.productLigneType;
    }
    
    public void ProductionProductLigneConfig.setProductLigneType(ProductionProductLigneType productLigneType) {
        this.productLigneType = productLigneType;
    }
    
    public ProductionStepConfig ProductionProductLigneConfig.getProductionStepConfig() {
        return this.productionStepConfig;
    }
    
    public void ProductionProductLigneConfig.setProductionStepConfig(ProductionStepConfig productionStepConfig) {
        this.productionStepConfig = productionStepConfig;
    }
    
    public ProductionTaskConfig ProductionProductLigneConfig.getProductionTaskConfig() {
        return this.productionTaskConfig;
    }
    
    public void ProductionProductLigneConfig.setProductionTaskConfig(ProductionTaskConfig productionTaskConfig) {
        this.productionTaskConfig = productionTaskConfig;
    }
    
}