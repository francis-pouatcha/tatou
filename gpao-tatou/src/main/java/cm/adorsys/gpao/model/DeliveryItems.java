package cm.adorsys.gpao.model;

import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class DeliveryItems {

    private String reference;

    @NotNull
    @ManyToOne
    private Product product;

    @Min(0L)
    private BigInteger orderQte;

    @Min(0L)
    private BigInteger qteReceive;

    @Min(0L)
    private BigDecimal qteUnreceive;

    @NotNull
    @ManyToOne
    private Delivery delivery;

    private BigDecimal amountHt;

    @Min(0L)
    private BigDecimal taxAmount;

    private BigDecimal taxedAmount;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date expirationDate;

    public DeliveryItems() {
    }

    public DeliveryItems(OrderItems items,Delivery delivery) {
        this.reference = reference;
        this.product = items.getProduct();
        this.orderQte = items.getQuantity();
        this.expirationDate = new Date();
        this.amountHt = items.getSubTotal();
        this.taxAmount = items.getTaxeAmount();
        this.taxedAmount = items.getTaxedSubTotal();
        this.delivery = delivery;
    }

    @PostPersist
    public void postPersist() {
        reference = GpaoSequenceGenerator.getSequenceWhitoutDate(getId(), delivery.getDocRef());
    }
}
