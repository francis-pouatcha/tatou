package cm.adorsys.gpao.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ProductFamily {

    @NotNull
    private String name;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "productFamily")
    private Set<ProductSubFamily> productSubFamily = new HashSet<ProductSubFamily>();
}
