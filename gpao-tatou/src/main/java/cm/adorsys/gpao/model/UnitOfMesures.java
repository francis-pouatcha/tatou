package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import cm.adorsys.gpao.utils.BussinessValidation;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUnitOfMesuresesByNameLikeAndUnitGroup" })
public class UnitOfMesures implements BussinessValidation {

    @NotNull
    @Column(unique=true)
    private String name;

    @Min(0L)
    private BigDecimal ratio;

    @NotNull
    @ManyToOne
    private UdmGroup unitGroup;

    @Value("true")
    private Boolean isActive;
    
    @Value("false")
    private Boolean isRefUnit;

	@Override
	public void validate(BindingResult bindingResult, Model uiModel) {
	     List<UnitOfMesures> resultList = findUnitOfMesuressByNameEquals(name).getResultList();
	     if(!resultList.isEmpty()){
	    	 bindingResult.reject("name", "ce libelle existe deja !");
	     }
		
	}
	
//finders
    
    public static TypedQuery<UnitOfMesures> findUnitOfMesuressByNameEquals(String name) {
        EntityManager em = UnitOfMesures.entityManager();
        TypedQuery<UnitOfMesures> q = em.createQuery("SELECT o FROM UnitOfMesures AS o WHERE LOWER(o.name) LIKE LOWER(:name) ORDER BY o.name ", UnitOfMesures.class);
        q.setParameter("name", name);
        return q;
    }
}
