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
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class UdmGroup extends GpaoBaseEntity{

    @NotNull
    private String name;

    private String description;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitGroup")
    private Set<UnitOfMesures> unitOfMesures = new HashSet<UnitOfMesures>();
    
    public String toString(){
    	return name;
    }
	
    public static void init(){
		if(UdmGroup.countUdmGroups() <= 0){
			UdmGroup udmGroup = new UdmGroup();
			udmGroup.setName("POID") ;
			udmGroup.persist();
		}
	}
    
    
}
