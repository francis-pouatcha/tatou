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

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class InventoryItems {

    @NotNull
    @ManyToOne
    private Product product;

    @NotNull
    @Min(0L)
    private BigInteger virtualStock;

    @NotNull
    @Min(0L)
    @Value("0")
    private BigInteger realStock;

    @Value("0")
    private BigInteger stockGap;

    @Value("0")
    private BigDecimal gapAmount;

    @NotNull
    @ManyToOne
    private Inventory inventory;

    @Value("0")
    private BigDecimal productPrice;

    @NotNull
    @ManyToOne
    private UnitOfMesures udm;

    public InventoryItems() {
    }

    public InventoryItems(Product product, Inventory inventory) {
        super();
        this.product = product;
        this.inventory = inventory;
        this.productPrice = product.getSalePrice();
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
