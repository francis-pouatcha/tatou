package cm.adorsys.gpao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.web.multipart.MultipartFile;

@RooJavaBean
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findPartnersByNameLike" })
public class Partner extends GpaoBaseEntity {

    @NotNull
    @Column(unique = true)
    private String name;

    @Value("true")
    private Boolean isCustomer;

    @Value("true")
    private Boolean isProvider;

    private String contactName;

    private String contactFunction;

    private String phone;

    private String mobile;

    private String fax;

    private String email;

    private String webSite;

    @Transient
    private MultipartFile partnerLogo;

    @Enumerated
    private PartnerType partnerType = PartnerType.ENTREPRISE;

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

    public Partner() {
        name = "";
    }

    public Partner(String name) {
        this.name = name;
    }

    public static void init() {
        if (Partner.countPartners() <= 0) {
            Partner partner = new Partner("Fournisseur Diver");
        }
    }

    public String toString() {
        return name;
    }
    
    public static TypedQuery<cm.adorsys.gpao.model.Partner> findPartnerByGroupAndType(String name ,PartnerGroup partnerGroup,PartnerType partnerType ,Boolean isCustomer,Boolean isProvider) {
        EntityManager em = Partner.entityManager();
		StringBuilder query = new StringBuilder("SELECT o FROM Partner AS o WHERE  o.id IS NOT NULL ");
		if(StringUtils.isNotBlank(name)){
			query.append(" AND LOWER (o.name) LIKE LOWER (:name) ");
		}
		
		if(partnerGroup != null){
			query.append(" AND o.partnerGroup = :partnerGroup");
		}
		if(partnerType != null){
			query.append(" AND o.partnerType = :partnerType");
		}
		if(isCustomer != null){
			query.append(" AND o.isCustomer IS :isCustomer");
		}
		if(isProvider != null){
			query.append(" AND o.isProvider IS :isProvider");
		}
		TypedQuery<Partner> q = em.createQuery(query.append(" ORDER BY o.id ").toString(), Partner.class);
		if(StringUtils.isNotBlank(name))q.setParameter("name", name);
		if(partnerGroup !=null )q.setParameter("partnerGroup", partnerGroup);
		if(partnerType != null)q.setParameter("partnerType", partnerType);
		if(isCustomer != null)q.setParameter("isCustomer", isCustomer);
		if(isProvider != null)q.setParameter("isProvider", isProvider);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Partner> findPartnersByNameEquals(String name) {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE LOWER(o.name) LIKE LOWER(:name) ORDER BY o.name ", Partner.class);
        q.setParameter("name", name);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Partner> findAllActiveProviders() {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE o.isProvider IS :isProvider AND o.isActive IS :isActive  ORDER BY o.name ", Partner.class);
        q.setParameter("isProvider", Boolean.TRUE);
        q.setParameter("isActive", Boolean.TRUE);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Partner> findAllActiveCustomers() {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE o.isCustomer IS :isCustomer AND o.isActive IS :isActive ORDER BY o.name ", Partner.class);
        q.setParameter("isCustomer", Boolean.TRUE);
        q.setParameter("isActive", Boolean.TRUE);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Partner> findPartnersByIdUpperThan(Long id) {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE  o.id > :id ORDER BY o.id ", Partner.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Partner> findPartnersByIdLowerThan(Long id) {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE  o.id < :id ORDER BY o.id DESC ", Partner.class);
        q.setParameter("id", id);
        return q;
    }
}
