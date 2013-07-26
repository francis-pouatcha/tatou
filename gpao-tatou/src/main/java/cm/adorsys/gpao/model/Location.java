package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Location {

    @NotNull
    private String name;

    @Min(0L)
    private BigDecimal hallWay;

    @Min(0L)
    private BigDecimal locationRow;

    @Min(0L)
    private BigDecimal locationHeigth;

    @NotNull
    @ManyToOne
    private WareHouses wareHouse;
}
