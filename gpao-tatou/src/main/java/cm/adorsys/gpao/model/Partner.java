package cm.adorsys.gpao.model;

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
import org.springframework.web.multipart.MultipartFile;

@RooJavaBean
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findPartnersByNameLike" })
public class Partner extends GpaoBaseEntity {

    private static Object en;

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
        if (Partner.countPartners() >= 1) {
            return ;
        }
        Partner partner = new Partner("Fournisseur Diver");
        if(PartnerGroup.countPartnerGroups() >= 0) {
        	partner.setPartnerGroup(PartnerGroup.findAllPartnerGroups().iterator().next());
        }
        partner.setPartnerType(PartnerType.ENTREPRISE);
        partner.setPhone("");
        partner.persist();
    }

    public String toString() {
        return name;
    }
    
    public static TypedQuery<cm.adorsys.gpao.model.Partner> findPartnerByNameLikeAndGroupAndType(String name ,PartnerGroup partnerGroup,PartnerType partnerType ,Boolean isCustomer,Boolean isProvider) {
        EntityManager em = Partner.entityManager();
		StringBuilder query = new StringBuilder("SELECT o FROM Partner AS o WHERE  o.id IS NOT NULL ");
		isCustomer = isCustomer ==null ? Boolean.TRUE : isCustomer ;
		isProvider = isProvider ==null ? Boolean.TRUE : isProvider ;
		if(StringUtils.isNotBlank(name)){
			name = name + "%" ;
			query.append(" AND LOWER (o.name) LIKE LOWER (:name) ");
		}
		
		if(partnerGroup != null){
			query.append(" AND o.partnerGroup = :partnerGroup");
		}
		if(partnerType != null){
			query.append(" AND o.partnerType = :partnerType");
		}
		query.append(" AND (o.isCustomer IS :isCustomer OR o.isProvider IS :isProvider) ");
		
		TypedQuery<Partner> q = em.createQuery(query.append(" ORDER BY o.id ").toString(), Partner.class);
		if(StringUtils.isNotBlank(name))q.setParameter("name", name);
		if(partnerGroup !=null )q.setParameter("partnerGroup", partnerGroup);
		if(partnerType != null)q.setParameter("partnerType", partnerType);
		q.setParameter("isCustomer", isCustomer);
		q.setParameter("isProvider", isProvider);
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
    public static TypedQuery<cm.adorsys.gpao.model.Partner> findAllActiveProviders(int firstResult, int maxResults) {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE o.isProvider IS :isProvider AND o.isActive IS :isActive  ORDER BY o.name ", Partner.class);
        q.setParameter("isProvider", Boolean.TRUE);
        q.setParameter("isActive", Boolean.TRUE);
        q.setFirstResult(firstResult).setMaxResults(maxResults);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Partner> findAllActiveCustomers(int firstResult, int maxResults) {
        EntityManager em = Partner.entityManager();
        TypedQuery<Partner> q = em.createQuery("SELECT o FROM Partner AS o WHERE o.isCustomer IS :isCustomer AND o.isActive IS :isActive ORDER BY o.name ", Partner.class);
        q.setParameter("isCustomer", Boolean.TRUE);
        q.setParameter("isActive", Boolean.TRUE);
        q.setFirstResult(firstResult).setMaxResults(maxResults);
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
