package cm.adorsys.gpao.model;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.utils.CurrencyUtils;
import flexjson.JSONSerializer;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findCustomerOrderItemsByCustomerOrder", "findCustomerOrderItemsByProduct", "findCustomerOrderItemsByReferenceEquals", "findCustomerOrderItemsByCustomerOrderAndProduct" })
@RooJson
public class CustomerOrderItem extends GpaoBaseEntity {

    /**
     */
    @NotNull
    private String reference;

    /**
     */
    @NotNull
    @ManyToOne
    private Product product;

    /**
     */
    @ManyToOne
    private UnitOfMesures udm;

    /**
     */
    @Min(0L)
    private BigDecimal amountHt = BigDecimal.ZERO;

    /**
     */
    private BigDecimal quantity = BigDecimal.ONE;

    /**
     * the unit price of this order item, It erase, if applied, the product's price
     */
    private BigDecimal unitPrice;

    /**
     */
    @ManyToOne
    private CustomerOrder customerOrder;

    public CustomerOrderItem() {
    }

    public CustomerOrderItem(CustomerOrder order, OrderItemUimodel itemUimodel) {
        product = Product.findProduct(itemUimodel.getProduct().getId());
        udm = itemUimodel.getUdm();
        quantity = itemUimodel.getQuantity();
        customerOrder = order;
        reference = "" + findCustomerOrderItemsByCustomerOrder(customerOrder).getResultList().size() + 1;
        amountHt = itemUimodel.getUnitPrice().multiply(BigDecimal.valueOf(quantity.longValue()));
        amountHt = CurrencyUtils.convertAmount(product.getDefaultCurrency(), order.getCurrency(), amountHt);
    }

    public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }

    public static String toJsonArray(Collection<CustomerOrderItem> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }

    public void reset(OrderItemUimodel itemUimodel) {
        product = Product.findProduct(itemUimodel.getProduct().getId());
        udm = itemUimodel.getUdm();
        quantity = itemUimodel.getQuantity();
        amountHt = itemUimodel.getUnitPrice().multiply(BigDecimal.valueOf(quantity.longValue()));
    }
}
