package cm.adorsys.gpao.model;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.addon.json.RooJson;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
@RooJson
public class Caracteristic extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String productSize;

    /**
     */
    @NotNull
    private String color;

    /**
     */
    @NotNull
    @ManyToOne
    private Product product;
}
