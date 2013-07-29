package cm.adorsys.gpao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UdmGroup {

    @NotNull
    private String name;

    private String description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitGroup")
    private Set<UnitOfMesures> unitOfMesures = new HashSet<UnitOfMesures>();
    
	
    
    
}