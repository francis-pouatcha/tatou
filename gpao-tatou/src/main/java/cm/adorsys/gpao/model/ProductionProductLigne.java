package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class ProductionProductLigne extends GpaoBaseEntity {

    /**
     */
    @NotNull
    @ManyToOne
    private Product product;

    /**
     */
    private BigDecimal quantity;

    /**
     */
    @Enumerated
    private ProductionProductLigneType productLigneType;

    /**
     */
    @ManyToOne
    private ProductionProductLigneConfig productionProductLigneConfig;

    /**
     */
    @ManyToOne
    private ProductionStep productionStep;

    /**
     */
    @ManyToOne
    private ProductionTask productionTask;
}
