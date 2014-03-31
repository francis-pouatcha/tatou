package cm.adorsys.gpao.model;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findDeliverysByDocRefEquals", "findDeliverysByReferenceEquals" })
public class Delivery extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String reference;

    /**
     */
    private String createBy;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date createdDate;

    /**
     */
    @Enumerated
    private DeliveryOrigin origin;

    /**
     */
    @Enumerated
    private DocumentStates status;

    /**
     */
    private BigDecimal amoutHt;

    /**
     */
    private BigDecimal taxedAmount;

    /**
     */
    private BigDecimal taxeAmount;

    /**
     */
    @NotNull
    @ManyToOne
    private Devise curreny;

    /**
     */
    @ManyToOne
    private Company company;

    /**
     */
    @NotNull
    private String docRef;

    /**
     */
    @ManyToOne
    private Partner customer;
}
