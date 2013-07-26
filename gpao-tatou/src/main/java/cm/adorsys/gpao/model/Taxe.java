package cm.adorsys.gpao.model;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Taxe {

    @NotNull
    private String name;

    @NotNull
    @Size(min = 2)
    private String shortName;

    @NotNull
    @Min(0L)
    private BigDecimal taxeValue;

    @Value("true")
    private Boolean isActive;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TaxeType taxeType;
    
    
    //finders
    
    public static TypedQuery<Taxe> findTaxeByNameLikeTaxeType(String name, TaxeType  taxeType) {
        if (name == null || name.length() == 0) name = "*";
        name = name.replace('*', '%');
            name = name + "%";
        if (taxeType == null) throw new IllegalArgumentException("The taxeType argument is required");
        EntityManager em = Taxe.entityManager();
        TypedQuery<Taxe> q = em.createQuery("SELECT o FROM Taxe AS o WHERE LOWER(o.name) LIKE LOWER(:name)  AND o.taxeType = :taxeType ORDER BY o.name ", Taxe.class);
        q.setParameter("name", name);
        q.setParameter("taxeType", taxeType);
        return q;
    }
}
