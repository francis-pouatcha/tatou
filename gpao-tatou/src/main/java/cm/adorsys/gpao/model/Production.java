package cm.adorsys.gpao.model;
import java.util.Date;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findProductionsByProductionState", "findProductionsByManufacturingVoucher", "findProductionsByManufacturingVoucherAndProductionState" })
public class Production extends GpaoBaseEntity {

    /**
     */
    private String reference;

    /**
     */
    @NotNull
    @ManyToOne
    private ManufacturingVoucher manufacturingVoucher;

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
    @NotNull
    @ManyToOne
    private ProductionTypeConfig productionTypeConfig;

    /**
     */
    @Enumerated
    private DocumentStates productionState;

    /**
     */
    private String userName;

    @PostPersist
    private void postPersist() {
        reference = GpaoSequenceGenerator.getSequence(getId(), GpaoSequenceGenerator.PRODUCTION_SEQUENSE_PREFIX);
    }

    /**
     */
    private String docRef;

    /**
     */
    @ManyToOne
    private ManufacturingVoucherItem manufacturingVoucherItem;
}
