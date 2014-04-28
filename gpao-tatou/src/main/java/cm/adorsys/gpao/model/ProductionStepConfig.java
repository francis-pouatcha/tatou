package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class ProductionStepConfig extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    private String description;

    /**
     */
    @NotNull
    @ManyToOne
    private ProductionWorkshop productionWorkshop;

    /**
     */
    private BigDecimal duration;

    /**
     */
    private int rank;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<GpaoUser> employees = new HashSet<GpaoUser>();

    /**
     */
    @ManyToOne
    private ProductionTypeConfig productionTypeConf;
}
