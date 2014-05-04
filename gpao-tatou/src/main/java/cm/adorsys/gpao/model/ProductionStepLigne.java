package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import javax.persistence.Enumerated;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class ProductionStepLigne extends GpaoBaseEntity {

    /**
     */
    @ManyToOne
    private Product product;

    /**
     */
    private BigDecimal quantity;

    /**
     */
    @ManyToOne
    private ProductionStep productionStep;

    /**
     */
    @Enumerated
    private ProductionProductLigneType ligneType;

    /**
     */
    @ManyToOne
    private ProductionProductLigneConfig productionProductLigneConfig;
}
