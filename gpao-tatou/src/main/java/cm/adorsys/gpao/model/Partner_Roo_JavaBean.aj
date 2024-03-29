// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Contacts;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.model.PartnerType;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

privileged aspect Partner_Roo_JavaBean {
    
    public String Partner.getName() {
        return this.name;
    }
    
    public void Partner.setName(String name) {
        this.name = name;
    }
    
    public Boolean Partner.getIsCustomer() {
        return this.isCustomer;
    }
    
    public void Partner.setIsCustomer(Boolean isCustomer) {
        this.isCustomer = isCustomer;
    }
    
    public Boolean Partner.getIsProvider() {
        return this.isProvider;
    }
    
    public void Partner.setIsProvider(Boolean isProvider) {
        this.isProvider = isProvider;
    }
    
    public String Partner.getContactName() {
        return this.contactName;
    }
    
    public void Partner.setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public String Partner.getContactFunction() {
        return this.contactFunction;
    }
    
    public void Partner.setContactFunction(String contactFunction) {
        this.contactFunction = contactFunction;
    }
    
    public String Partner.getPhone() {
        return this.phone;
    }
    
    public void Partner.setPhone(String phone) {
        this.phone = phone;
    }
    
    public String Partner.getMobile() {
        return this.mobile;
    }
    
    public void Partner.setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String Partner.getFax() {
        return this.fax;
    }
    
    public void Partner.setFax(String fax) {
        this.fax = fax;
    }
    
    public String Partner.getEmail() {
        return this.email;
    }
    
    public void Partner.setEmail(String email) {
        this.email = email;
    }
    
    public String Partner.getWebSite() {
        return this.webSite;
    }
    
    public void Partner.setWebSite(String webSite) {
        this.webSite = webSite;
    }
    
    public MultipartFile Partner.getPartnerLogo() {
        return this.partnerLogo;
    }
    
    public void Partner.setPartnerLogo(MultipartFile partnerLogo) {
        this.partnerLogo = partnerLogo;
    }
    
    public PartnerType Partner.getPartnerType() {
        return this.partnerType;
    }
    
    public void Partner.setPartnerType(PartnerType partnerType) {
        this.partnerType = partnerType;
    }
    
    public PartnerGroup Partner.getPartnerGroup() {
        return this.partnerGroup;
    }
    
    public void Partner.setPartnerGroup(PartnerGroup partnerGroup) {
        this.partnerGroup = partnerGroup;
    }
    
    public Set<Contacts> Partner.getContacts() {
        return this.contacts;
    }
    
    public void Partner.setContacts(Set<Contacts> contacts) {
        this.contacts = contacts;
    }
    
    public String Partner.getCity() {
        return this.city;
    }
    
    public void Partner.setCity(String city) {
        this.city = city;
    }
    
    public String Partner.getCountry() {
        return this.country;
    }
    
    public void Partner.setCountry(String country) {
        this.country = country;
    }
    
    public Devise Partner.getPartnerDevise() {
        return this.partnerDevise;
    }
    
    public void Partner.setPartnerDevise(Devise partnerDevise) {
        this.partnerDevise = partnerDevise;
    }
    
    public String Partner.getLogoPath() {
        return this.logoPath;
    }
    
    public void Partner.setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
    
    public Boolean Partner.getIsActive() {
        return this.isActive;
    }
    
    public void Partner.setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public String Partner.getCode() {
        return this.code;
    }
    
    public void Partner.setCode(String code) {
        this.code = code;
    }
    
}
