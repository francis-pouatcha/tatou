package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class OrderItems {

    @NotNull
    private String reference;

    @NotNull
    @ManyToOne
    private Product product;

    @ManyToOne
    private UnitOfMesures udm;

    @Value("1")
    private BigInteger quantity;

    @Value("0")
    private BigDecimal subTotal;

    @Value("0")
    private BigDecimal taxeAmount;

    @NotNull
    @ManyToOne
    private PurchaseOrder purchaseOrder;
}
