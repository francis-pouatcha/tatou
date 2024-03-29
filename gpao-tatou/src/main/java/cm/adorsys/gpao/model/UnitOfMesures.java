package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import cm.adorsys.gpao.utils.BussinessValidation;
import flexjson.JSONSerializer;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS" ,finders = { "findUnitOfMesuresesByNameLikeAndUnitGroup" })
public class UnitOfMesures extends GpaoBaseEntity implements BussinessValidation {

    @NotNull
    @Column(unique=true)
    private String name;

    @Min(0L)
    @NotNull
    private BigDecimal ratio = BigDecimal.ONE;

    @NotNull
    @ManyToOne
    private UdmGroup unitGroup;

    @Value("true")
    private Boolean isActive;
    
    @Value("false")
    private Boolean isRefUnit;
    
    
    public String toString() {
        return name;
    }
    
	@Override
	public void validate(BindingResult bindingResult, Model uiModel) {
	     List<UnitOfMesures> resultList = findUnitOfMesuressByNameEquals(name).getResultList();
	     if(!resultList.isEmpty()){
	    	// bindingResult.reject("name", "ce libelle existe deja !");
	     }
		
	}
	
	 public String toJson() {
	        return new JSONSerializer().exclude("*.class").serialize(this);
	    }
	    
	    public static String toJsonArray(Collection<UnitOfMesures> collection) {
	        return new JSONSerializer().exclude("*.class").serialize(collection);
	    }
	
	public static void init(){
		if(UnitOfMesures.countUnitOfMesureses() <= 0){
			UdmGroup.init();
			UnitOfMesures unitOfMesures = new UnitOfMesures();
			unitOfMesures.setName("Kg") ;
			unitOfMesures.setUnitGroup(UdmGroup.findAllUdmGroups().iterator().next());
			unitOfMesures.persist();
		}
	} 
	
//finders
    
    public static TypedQuery<UnitOfMesures> findUnitOfMesuressByNameEquals(String name) {
        EntityManager em = UnitOfMesures.entityManager();
        TypedQuery<UnitOfMesures> q = em.createQuery("SELECT o FROM UnitOfMesures AS o WHERE LOWER(o.name) LIKE LOWER(:name) ORDER BY o.name ", UnitOfMesures.class);
        q.setParameter("name", name);
        return q;
    }
    public static TypedQuery<UnitOfMesures> findUnitOfMesuressByGroupEqualsAndReference(UdmGroup group,Boolean isRefUnit) {
        EntityManager em = UnitOfMesures.entityManager();
        TypedQuery<UnitOfMesures> q = em.createQuery("SELECT o FROM UnitOfMesures AS o WHERE  o.unitGroup = :unitGroup AND  o.isRefUnit = :isRefUnit ", UnitOfMesures.class);
        q.setParameter("unitGroup", group);
        q.setParameter("isRefUnit", isRefUnit);
        return q;
    }
    
    
    public static TypedQuery<UnitOfMesures> findUnitOfMesuressByGroupEquals(UdmGroup group) {
        EntityManager em = UnitOfMesures.entityManager();
        TypedQuery<UnitOfMesures> q = em.createQuery("SELECT o FROM UnitOfMesures AS o WHERE  o.unitGroup = :unitGroup  ", UnitOfMesures.class);
        q.setParameter("unitGroup", group);
        return q;
    }
}
