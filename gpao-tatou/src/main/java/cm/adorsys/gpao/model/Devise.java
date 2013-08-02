package cm.adorsys.gpao.model;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Devise {

    @NotNull
    private String name;

    @NotNull
    @Size(min = 1)
    private String shortName;

    @NotNull
    @Min(0L)
    private BigDecimal ratio;
    
    public static void init(){
    	if(Devise.countDevises() <= 0){
    		Devise devise = new Devise();
    		devise.setName("FRANCS CFA");
    		devise.setShortName("CFA");
    		devise.setRatio(BigDecimal.ONE);
    		devise.persist();
    	}
    }
    
    //finders
    public static TypedQuery<Devise> findTaxeByNameOrShortName(String name,String shortName) {
        if (name == null || name.length() == 0) name = "*";
        if (shortName == null || shortName.length() == 0) shortName = "*";
        name = name.replace('*', '%');
        shortName = shortName.replace('*', '%');
             name = name + "%";
             shortName = shortName + "%";
        EntityManager em = Taxe.entityManager();
        TypedQuery<Devise> q = em.createQuery("SELECT o FROM Devise AS o WHERE LOWER(o.name) LIKE LOWER(:name)  And LOWER(o.shortName) LIKE LOWER(:shortName) ORDER BY o.name ", Devise.class);
        q.setParameter("name", name);
        q.setParameter("shortName", shortName);
        return q;
    }
    
    
}
