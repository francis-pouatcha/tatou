package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import cm.adorsys.gpao.utils.CurrencyUtils;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class InventoryItems extends GpaoBaseEntity{

    @ManyToOne
    private Product product;

    @NotNull
    private BigDecimal virtualStock = BigDecimal.ZERO;

    @NotNull
    private BigDecimal realStock = BigDecimal.ZERO;

    private BigDecimal stockGap= BigDecimal.ZERO;

    private BigDecimal gapAmount= BigDecimal.ZERO;

    @ManyToOne
    private Inventory inventory;

    private BigDecimal productPrice= BigDecimal.ZERO;

    @NotNull
    @ManyToOne
    private UnitOfMesures udm;

    public InventoryItems() {
    }

    public InventoryItems(Product product, Inventory inventory) {
        this.product = product;
        this.inventory = inventory;
        this.productPrice = CurrencyUtils.convertAmount(product.getDefaultCurrency(), inventory.getCurrency(), product.getSalePrice());
        this.virtualStock = product.getVirtualStock();
        this.realStock = product.getVirtualStock();
        this.udm = product.getDefaultUdm();
        calculateStockGapAndGapAmount();
    }
    

    public void calculateStockGapAndGapAmount() {
        stockGap = virtualStock.subtract(realStock);
        gapAmount = productPrice.multiply(BigDecimal.valueOf(stockGap.longValue()));
    }
}
