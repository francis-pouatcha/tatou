package cm.adorsys.gpao.model;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import javax.persistence.Enumerated;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class ProductionTaskLigne extends GpaoBaseEntity {

    /**
     */
    @ManyToOne
    private Product product;

    /**
     */
    @ManyToOne
    private ProductionTask productTask;

    /**
     */
    private BigDecimal quantity;

    /**
     */
    @Enumerated
    private ProductionProductLigneType ligneType;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-yyyy hh:mm")
    private Date createDate;

    /**
     */
    @ManyToOne
    private ProductionProductLigneConfig productionProductLigneConfig;
}
