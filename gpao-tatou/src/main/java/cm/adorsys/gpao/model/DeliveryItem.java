package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findDeliveryItemsByDelivery" })
public class DeliveryItem extends GpaoBaseEntity {

    /**
     */
	//    @NotNull
//    private String reference;

    /**
     */
    @NotNull
    @ManyToOne
    private Product product;

    /**
     */
    @NotNull
    private BigDecimal orderedQty;

    /**
     */
    @NotNull
    private BigDecimal deliveredQty;

    /**
     */
    private BigDecimal undeliveredQty;

    /**
     */
    @NotNull
    @ManyToOne
    private Delivery delivery;

    /**
     */
    @NotNull
    private BigDecimal amountHt;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date expirationDate;

    /**
     */
    @NotNull
    @ManyToOne
    private UnitOfMesures udm;
}
