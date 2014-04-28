package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class ProductionTaskConfig extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String name;

    /**
     */
    @ManyToOne
    private ProductionStepConfig productionStepConfig;

    /**
     */
    @ManyToOne
    private GpaoUser assignee;

    /**
     * The estimated task's duration in hours
     */
    private int duration;
}
