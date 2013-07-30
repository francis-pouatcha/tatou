package cm.adorsys.gpao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Company {

    @NotNull
    private String name;
    
    private String contactName;

    private String contactFunction;

    private String taxePayerNumber;

    private String phone;

    private String mobile;

    private String fax;

    private String email;

    private String webSite;

    private String logoPath;
    
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Contacts> contacts = new HashSet<Contacts>();

    private String city;

    private String country;

    @NotNull
    @ManyToOne
    private Devise devise;
}
