package cm.adorsys.gpao.model.uimodels;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.roo.addon.json.RooJson;

import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.UnitOfMesures;

@RooJson
public class OrderItemUimodel {

    private Long productId;

    private UnitOfMesures udm;
    
    private String productName ;

    private BigDecimal unitPrice;

    private BigDecimal quantity = BigDecimal.ONE;
    
    public OrderItemUimodel() { }
    
    public OrderItemUimodel(Product product) {
		productId = product.getId();
		udm = product.getDefaultUdm();
		productName = product.getName();
		unitPrice = product.getPurchasePrice();
		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public UnitOfMesures getUdm() {
		return udm;
	}

	public void setUdm(UnitOfMesures udm) {
		this.udm = udm;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

    
}
