// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Contacts;
import cm.adorsys.gpao.model.Devise;
import java.util.Set;
import org.springframework.web.multipart.MultipartFile;

privileged aspect Company_Roo_JavaBean {
    
    public String Company.getName() {
        return this.name;
    }
    
    public void Company.setName(String name) {
        this.name = name;
    }
    
    public String Company.getContactName() {
        return this.contactName;
    }
    
    public void Company.setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public String Company.getContactFunction() {
        return this.contactFunction;
    }
    
    public void Company.setContactFunction(String contactFunction) {
        this.contactFunction = contactFunction;
    }
    
    public String Company.getTaxePayerNumber() {
        return this.taxePayerNumber;
    }
    
    public void Company.setTaxePayerNumber(String taxePayerNumber) {
        this.taxePayerNumber = taxePayerNumber;
    }
    
    public String Company.getPhone() {
        return this.phone;
    }
    
    public void Company.setPhone(String phone) {
        this.phone = phone;
    }
    
    public String Company.getMobile() {
        return this.mobile;
    }
    
    public void Company.setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public String Company.getFax() {
        return this.fax;
    }
    
    public void Company.setFax(String fax) {
        this.fax = fax;
    }
    
    public String Company.getEmail() {
        return this.email;
    }
    
    public void Company.setEmail(String email) {
        this.email = email;
    }
    
    public String Company.getWebSite() {
        return this.webSite;
    }
    
    public void Company.setWebSite(String webSite) {
        this.webSite = webSite;
    }
    
    public MultipartFile Company.getUploadedLogo() {
        return this.uploadedLogo;
    }
    
    public void Company.setUploadedLogo(MultipartFile uploadedLogo) {
        this.uploadedLogo = uploadedLogo;
    }
    
    public String Company.getLogoPath() {
        return this.logoPath;
    }
    
    public void Company.setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
    
    public Set<Contacts> Company.getContacts() {
        return this.contacts;
    }
    
    public void Company.setContacts(Set<Contacts> contacts) {
        this.contacts = contacts;
    }
    
    public String Company.getCity() {
        return this.city;
    }
    
    public void Company.setCity(String city) {
        this.city = city;
    }
    
    public String Company.getCountry() {
        return this.country;
    }
    
    public void Company.setCountry(String country) {
        this.country = country;
    }
    
    public Devise Company.getDevise() {
        return this.devise;
    }
    
    public void Company.setDevise(Devise devise) {
        this.devise = devise;
    }
    
}
