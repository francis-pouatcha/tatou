package cm.adorsys.gpao.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
@RooJson
public class ProductFamily extends GpaoBaseEntity {

    @NotNull
    private String name;

    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productFamily")
    private Set<ProductSubFamily> productSubFamily = new HashSet<ProductSubFamily>();

    public static void init() {
        if (ProductFamily.countProductFamilys() <= 0) {
            ProductFamily productFamily = new ProductFamily();
            productFamily.setName("CHAUSSURES");
            productFamily.persist();
        }
    }

    public String toString() {
        return name;
    }
}
