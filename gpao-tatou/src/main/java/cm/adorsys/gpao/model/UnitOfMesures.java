package cm.adorsys.gpao.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findUnitOfMesuresesByNameLikeAndUnitGroup" })
public class UnitOfMesures {

    @NotNull
    @Column(unique=true)
    private String name;

    @Min(0L)
    private BigDecimal ratio;

    @NotNull
    @ManyToOne
    private UdmGroup unitGroup;

    @Value("true")
    private Boolean isActive;
    
    @Value("false")
    private Boolean isRefUnit;
}
