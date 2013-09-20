package cm.adorsys.gpao.model;

import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord
public class Tenders {

    private String reference;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date created = new Date();

    private String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date closed;

    private String closedBy;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date beginDate;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private DocumentStates status = DocumentStates.OUVERT;

    private String description;

    @ManyToOne
    private Company company;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tender")
    private Set<TenderItems> tenderItems = new HashSet<TenderItems>();

    public TenderItems hasProduct(Product product) {
         Set<TenderItems> tenderItems2 = getTenderItems();
        for (TenderItems tenderItem : tenderItems2) {
            if (tenderItem.getProducts().equals(product)) return tenderItem;
        }
        return null;
    }
    
    public String toString(){
    	if(StringUtils.isBlank(reference)) return "" ;
    	return reference ;
    }
    @PostPersist
    public void postPersist() {
        reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.TENDER_SEQUENCE_PREFIX);
        created = new Date();
        createBy = SecurityUtil.getUserName();
    }

    public static TypedQuery<cm.adorsys.gpao.model.Tenders> findTenderByStatus(DocumentStates status) {
        EntityManager em = Tenders.entityManager();
        TypedQuery<Tenders> q = em.createQuery("SELECT o FROM Tenders AS o WHERE  o.status = :status ORDER BY o.id ", Tenders.class);
        q.setParameter("status", status);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Tenders> findTenderssByIdUpperThan(Long id) {
        EntityManager em = Tenders.entityManager();
        TypedQuery<Tenders> q = em.createQuery("SELECT o FROM Tenders AS o WHERE  o.id > :id ORDER BY o.id ", Tenders.class);
        q.setParameter("id", id);
        return q;
    }

    public static TypedQuery<cm.adorsys.gpao.model.Tenders> findTenderssByIdLowerThan(Long id) {
        EntityManager em = Tenders.entityManager();
        TypedQuery<Tenders> q = em.createQuery("SELECT o FROM Tenders AS o WHERE  o.id < :id ORDER BY o.id DESC ", Tenders.class);
        q.setParameter("id", id);
        return q;
    }
    public static TypedQuery<cm.adorsys.gpao.model.Tenders> findTendersByReferenceEquals(String reference) {
		EntityManager em = Tenders.entityManager();
		TypedQuery<Tenders> q = em.createQuery("SELECT o FROM Tenders AS o WHERE  o.reference = :reference ", Tenders.class);
		q.setParameter("reference", reference);
		return q;
	}
}
