package cm.adorsys.gpao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class Location extends GpaoBaseEntity{

    @NotNull
    private String name;

    @Min(0L)
    private BigDecimal hallWay;

    @Min(0L)
    private BigDecimal locationRow;

    @Min(0L)
    private BigDecimal locationHeigth;

    @NotNull
    @ManyToOne
    private WareHouses wareHouse;
    
    public static void init(){
    	if(Location.countLocations() <= 0){
    		WareHouses.init();
    		Location location = new Location();
    		location.setName("CHAUSSURES") ;
    		location.setWareHouse(WareHouses.findAllWareHouseses().iterator().next());
    		location.persist();
    	}
    }
    
    
 //finders
    
    public static TypedQuery<Location> findLocationsByNameLikeAndWareHouses(String name, WareHouses  wareHouses) {
        if (name == null || name.length() == 0) name = "*";
        name = name.replace('*', '%');
            name = name + "%";
        if (wareHouses == null) throw new IllegalArgumentException("The wareHouses argument is required");
        EntityManager em = Taxe.entityManager();
        TypedQuery<Location> q = em.createQuery("SELECT o FROM Location AS o WHERE LOWER(o.name) LIKE LOWER(:name)  AND o.wareHouse = :wareHouse ORDER BY o.name ", Location.class);
        q.setParameter("name", name);
        q.setParameter("wareHouse", wareHouses);
        return q;
    }
}
