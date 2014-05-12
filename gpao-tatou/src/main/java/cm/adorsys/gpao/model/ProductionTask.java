package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findProductionTasksByProductionStep", "findProductionTasksByProductionStepAndTaskState" })
public class ProductionTask extends GpaoBaseEntity {

    /**
     */
    @ManyToOne
    private ProductionTaskConfig productionTaskConfig;

    /**
     */
    @ManyToOne
    private ProductionStep productionStep;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date startDate;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date endDate;

    /**
     */
    @Enumerated
    private DocumentStates taskState;

    /**
     */
    private String name;

    /**
     */
    private String description;
}
