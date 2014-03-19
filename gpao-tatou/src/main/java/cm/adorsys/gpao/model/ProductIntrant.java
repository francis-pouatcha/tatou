package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class ProductIntrant extends GpaoBaseEntity {

    /**
     */
    @NotNull
    @ManyToOne
    private Product product;

    /**
     * amout of raw material
     */
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal quantity;

    /**
     * the raw material product
     */
    @NotNull
    @ManyToOne
    private Product rawMaterial;

    /**
     * raw material, measure unit value.
     */
    @NotNull
    @ManyToOne
    private UdmGroup udm;
}
