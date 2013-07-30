package cm.adorsys.gpao.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Partner {

    @NotNull
    private String name;

    @Value("false")
    private Boolean isCustomer;

    @Value("false")
    private Boolean isProvider;

    private String contactName;

    private String contactFunction;

    private String phone;

    private String mobile;

    private String fax;

    private String email;

    private String webSite;

    @Enumerated
    private PartnerType partnerType;

    @ManyToOne
    private PartnerGroup partnerGroup;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Contacts> contacts = new HashSet<Contacts>();

    private String city;

    private String country;

    @ManyToOne
    private Devise partnerDevise;

    private String logoPath;

    @Value("true")
    private Boolean isActive;

    private String code;
    
//finders
    
    public static TypedQuery<Partner> findPartnersByNameEquals(String name) {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE LOWER(o.name) LIKE LOWER(:name) ORDER BY o.name ", Partner.class);
        q.setParameter("name", name);
        return q;
    }
    public static TypedQuery<Partner> findPartnersByIdUpperThan(Long id) {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE  o.id > :id ORDER BY o.id ", Partner.class);
        q.setParameter("id", id);
        return q;
    }
    
    public static TypedQuery<Partner> findPartnersByIdLowerThan(Long id) {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE  o.id < :id ORDER BY o.id DESC ", Partner.class);
        q.setParameter("id", id);
        return q;
    }
}
