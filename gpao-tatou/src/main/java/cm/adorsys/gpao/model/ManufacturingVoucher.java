package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findManufacturingVouchersByDocumentState", "findManufacturingVouchersByReferenceEquals" })
public class ManufacturingVoucher extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String reference;

    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date createDate;

    /**
     */
    @NotNull
    private String createdBy;

    /**
     */
    @Enumerated
    private DocumentStates documentState;

    /**
     */
    @ManyToOne
    private CustomerOrder customerOrder;

    /**
     */
    @ManyToOne
    private Partner customer;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyy HH:mm")
    private Date delayDate;
}
