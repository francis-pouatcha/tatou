package cm.adorsys.gpao.services;

import java.math.BigDecimal;

import cm.adorsys.gpao.model.CustomerOrderItem;


public class CustomerOrderItemsQuantityDetail {
	private CustomerOrderItem customerOderItem;
	private BigDecimal availableQuantity;
	private BigDecimal unAvailableQuantity;
	
	public CustomerOrderItemsQuantityDetail(CustomerOrderItem customerOderItem,
			BigDecimal availableQuantity, BigDecimal unAvailableQuantity) {
		super();
		this.customerOderItem = customerOderItem;
		this.availableQuantity = availableQuantity;
		this.unAvailableQuantity = unAvailableQuantity;
	}

	public CustomerOrderItem getCustomerOderItem() {
		return customerOderItem;
	}

	public BigDecimal getAvailableQuantity() {
		return availableQuantity;
	}

	public BigDecimal getUnAvailableQuantity() {
		return unAvailableQuantity;
	}
	
	
}
