// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.model.ProductType;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.WareHouses;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

privileged aspect Product_Roo_JavaBean {
    
    public ProductType Product.getProductType() {
        return this.productType;
    }
    
    public void Product.setProductType(ProductType productType) {
        this.productType = productType;
    }
    
    public String Product.getReference() {
        return this.reference;
    }
    
    public void Product.setReference(String reference) {
        this.reference = reference;
    }
    
    public String Product.getName() {
        return this.name;
    }
    
    public void Product.setName(String name) {
        this.name = name;
    }
    
    public ProductSubFamily Product.getFamille() {
        return this.famille;
    }
    
    public void Product.setFamille(ProductSubFamily famille) {
        this.famille = famille;
    }
    
    public WareHouses Product.getWarehouse() {
        return this.warehouse;
    }
    
    public void Product.setWarehouse(WareHouses warehouse) {
        this.warehouse = warehouse;
    }
    
    public Boolean Product.getCanBebuy() {
        return this.canBebuy;
    }
    
    public void Product.setCanBebuy(Boolean canBebuy) {
        this.canBebuy = canBebuy;
    }
    
    public Boolean Product.getCanBeSale() {
        return this.canBeSale;
    }
    
    public void Product.setCanBeSale(Boolean canBeSale) {
        this.canBeSale = canBeSale;
    }
    
    public BigInteger Product.getVirtualStock() {
        return this.virtualStock;
    }
    
    public void Product.setVirtualStock(BigInteger virtualStock) {
        this.virtualStock = virtualStock;
    }
    
    public BigInteger Product.getMinStock() {
        return this.minStock;
    }
    
    public void Product.setMinStock(BigInteger minStock) {
        this.minStock = minStock;
    }
    
    public UnitOfMesures Product.getDefaultUdm() {
        return this.defaultUdm;
    }
    
    public void Product.setDefaultUdm(UnitOfMesures defaultUdm) {
        this.defaultUdm = defaultUdm;
    }
    
    public Devise Product.getDefaultCurrency() {
        return this.defaultCurrency;
    }
    
    public void Product.setDefaultCurrency(Devise defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
    
    public BigDecimal Product.getPurchasePrice() {
        return this.purchasePrice;
    }
    
    public void Product.setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    
    public BigDecimal Product.getSalePrice() {
        return this.salePrice;
    }
    
    public void Product.setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }
    
    public String Product.getEpaisseur() {
        return this.epaisseur;
    }
    
    public void Product.setEpaisseur(String epaisseur) {
        this.epaisseur = epaisseur;
    }
    
    public String Product.getColor() {
        return this.color;
    }
    
    public void Product.setColor(String color) {
        this.color = color;
    }
    
    public String Product.getDescription() {
        return this.description;
    }
    
    public void Product.setDescription(String description) {
        this.description = description;
    }
    
    public Set<Taxe> Product.getSaleTaxes() {
        return this.saleTaxes;
    }
    
    public void Product.setSaleTaxes(Set<Taxe> saleTaxes) {
        this.saleTaxes = saleTaxes;
    }
    
    public Set<Taxe> Product.getPurchaseTaxes() {
        return this.purchaseTaxes;
    }
    
    public void Product.setPurchaseTaxes(Set<Taxe> purchaseTaxes) {
        this.purchaseTaxes = purchaseTaxes;
    }
    
    public Boolean Product.getActived() {
        return this.actived;
    }
    
    public void Product.setActived(Boolean actived) {
        this.actived = actived;
    }
    
    public MultipartFile Product.getProductImage() {
        return this.productImage;
    }
    
    public void Product.setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }
    
    public String Product.getProductImagePath() {
        return this.productImagePath;
    }
    
    public void Product.setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
    }
    
    public String Product.getCodeBare() {
        return this.codeBare;
    }
    
    public void Product.setCodeBare(String codeBare) {
        this.codeBare = codeBare;
    }
    
}