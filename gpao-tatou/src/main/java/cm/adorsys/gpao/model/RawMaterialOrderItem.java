package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", finders = { "findRawMaterialOrderItemsByRawMaterialOrder" })
public class RawMaterialOrderItem extends GpaoBaseEntity {

    /**
     */
    @ManyToOne
    private RawMaterialOrder rawMaterialOrder;

    /**
     */
    @NotNull
    @ManyToOne
    private Product product;

    /**
     */
    @ManyToOne
    private UnitOfMesures udm;

    /**
     */
    private BigDecimal quantity;
}
