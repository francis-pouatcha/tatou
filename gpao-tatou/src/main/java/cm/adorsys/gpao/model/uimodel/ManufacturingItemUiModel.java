package cm.adorsys.gpao.model.uimodel;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;

import javax.persistence.ManyToOne;

import cm.adorsys.gpao.model.Caracteristic;
import cm.adorsys.gpao.model.Specificity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean
public class ManufacturingItemUiModel {

    /**
     */
    @ManyToOne
    private ManufacturingVoucherItem manufacturingVoucherItem;

    /**
     */
    @ManyToOne
    private Caracteristic caracteristic;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Specificity> specifycities = new HashSet<Specificity>();
}
