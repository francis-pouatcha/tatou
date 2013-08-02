package cm.adorsys.gpao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.web.multipart.MultipartFile;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Company {

    @NotNull
    @Column(unique=true)
    private String name;
    
    private String contactName;

    private String contactFunction;

    private String taxePayerNumber;

    private String phone;

    private String mobile;

    private String fax;

    private String email;

    private String webSite;
    
    @Transient
    private MultipartFile uploadedLogo ;

    private String logoPath;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Contacts> contacts = new HashSet<Contacts>();

    private String city;

    private String country;

    @NotNull
    @ManyToOne
    private Devise devise;
    
	public static void init(){
    	if(Company.countCompanys() <= 0){
    		Company company = new Company();
    		company.setDevise(Devise.findAllDevises().iterator().next());
    		company.persist();
    	}
    }
}
