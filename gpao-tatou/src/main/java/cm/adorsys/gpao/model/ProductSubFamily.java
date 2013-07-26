package cm.adorsys.gpao.model;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ProductSubFamily {

    @NotNull
    private String name;

    private String description;

    @Value("true")
    private Boolean isActive;

    @NotNull
    @ManyToOne
    private ProductFamily productFamily;
}
