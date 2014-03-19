package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class Caracteristic extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String productSize;

    /**
     */
    @NotNull
    private String color;

    /**
     * product specificities
     */
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Specificity> specificities = new HashSet<Specificity>();
}
