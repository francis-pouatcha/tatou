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

    private BigInteger Quantity = BigInteger.ONE;
    
    private List<UnitOfMesures> udmList = new ArrayList<UnitOfMesures>() ;
    
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

	public BigInteger getQuantity() {
		return Quantity;
	}

	public void setQuantity(BigInteger quantity) {
		Quantity = quantity;
	}

	public List<UnitOfMesures> getUdmList() {
		return udmList;
	}

	public void setUdmList(List<UnitOfMesures> udmList) {
		this.udmList = udmList;
	}
    
    
}
