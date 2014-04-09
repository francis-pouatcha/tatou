package cm.adorsys.gpao.model;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findManufacturingVoucherItemsByManufacturingVoucher" })
@RooJson
public class ManufacturingVoucherItem extends GpaoBaseEntity {

    /**
     */
    @NotNull
    @ManyToOne
    private Product product;

    /**
     */
    @DecimalMin("0")
    private BigDecimal quantity;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date delayDate;

    /**
     */
    private String comment;

    /**
     */
    @NotNull
    @ManyToOne
    private ManufacturingVoucher manufacturingVoucher;
    
    public static TypedQuery<Specificity> findProductSpecificityByManufacturingVoucherItem(ManufacturingVoucherItem manufacturingVoucherItem){
    	String queryString = "SELECT o.specificity FROM SpecificityToCaracteristicMap AS o,Caracteristic AS c WHERE o.caracteristic in (SELECT c FROM Caracteristic AS c WHERE c.product = :product)";
    	TypedQuery<Specificity> typedQuery = ManufacturingVoucherItem.entityManager().createNamedQuery(queryString, Specificity.class);
    	typedQuery.setParameter("product", manufacturingVoucherItem.getProduct());
    	return typedQuery;
    }
}
