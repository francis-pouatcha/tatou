package cm.adorsys.gpao.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class WareHouses {

    @NotNull
    @Column(unique=true)
    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "wareHouse")
    private Set<Location> location = new HashSet<Location>();
    
    public WareHouses() {
		name = " " ;
	}
    public static void init(){
    	if(WareHouses.countWareHouseses() <= 0){
    		WareHouses wareHouses = new WareHouses();
    		wareHouses.setName("CHAUSSURES") ;
    		wareHouses.persist();
    	}
}
    public String toString(){
    	return name ;
    }
}
