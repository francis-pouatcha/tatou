package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class OrderItems {

	@NotNull
	private String reference;

	@NotNull
	@ManyToOne
	private Product product;

	@ManyToOne
	private UnitOfMesures udm;

	@Value("1")
	private BigInteger quantity;

	@Value("0")
	private BigDecimal subTotal;
	
	@Value("0")
	private BigDecimal taxedSubTotal;


	@Value("0")
	private BigDecimal taxeAmount;

	@NotNull
	@ManyToOne
	private PurchaseOrder purchaseOrder;

	public OrderItems() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderItems(PurchaseOrder order,OrderItemUimodel itemUimodel) {
		product = Product.findProduct(itemUimodel.getProductId());
		udm = itemUimodel.getUdm();
		quantity = itemUimodel.getQuantity();
		purchaseOrder = order ;
		reference = ""+order.getOrderItems().size()+1;
		subTotal = itemUimodel.getUnitPrice();
	}
	
	public void reset(OrderItemUimodel itemUimodel) {
		product = Product.findProduct(itemUimodel.getProductId());
		udm = itemUimodel.getUdm();
		quantity = itemUimodel.getQuantity();
		subTotal = itemUimodel.getUnitPrice();
	}
	


	public void calculateTaxAndAmout(){
		taxeAmount = BigDecimal.ZERO ;
		taxedSubTotal = BigDecimal.ZERO ;
		Set<Taxe> saleTaxes = product.getSaleTaxes();
		for (Taxe taxe : saleTaxes) {
             taxeAmount = taxe.getTaxeFromAmount(subTotal);
             taxedSubTotal = taxedSubTotal.add(subTotal);
		}
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result
				+ ((purchaseOrder == null) ? 0 : purchaseOrder.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime * result
				+ ((reference == null) ? 0 : reference.hashCode());
		result = prime * result
				+ ((subTotal == null) ? 0 : subTotal.hashCode());
		result = prime * result
				+ ((taxeAmount == null) ? 0 : taxeAmount.hashCode());
		result = prime * result + ((udm == null) ? 0 : udm.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItems other = (OrderItems) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (purchaseOrder == null) {
			if (other.purchaseOrder != null)
				return false;
		} else if (!purchaseOrder.equals(other.purchaseOrder))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (subTotal == null) {
			if (other.subTotal != null)
				return false;
		} else if (!subTotal.equals(other.subTotal))
			return false;
		if (taxeAmount == null) {
			if (other.taxeAmount != null)
				return false;
		} else if (!taxeAmount.equals(other.taxeAmount))
			return false;
		if (udm == null) {
			if (other.udm != null)
				return false;
		} else if (!udm.equals(other.udm))
			return false;
		return true;
	}


}
