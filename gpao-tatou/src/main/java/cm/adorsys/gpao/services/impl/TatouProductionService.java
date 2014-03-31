/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.services.CustomerOrderItemsQuantityDetail;
import cm.adorsys.gpao.services.IProductionService;

/**
 * @author bwa
 *
 */
@Service
public class TatouProductionService implements IProductionService {

	@Override
	public boolean checkProductsAvaibility(CustomerOrder customerOrder) {
		Assert.notNull(customerOrder, "Customer order should not be null here");
		boolean avaibility = true;
		List<CustomerOrderItem> customerOrderItems = CustomerOrderItem.findCustomerOrderItemsByCustomerOrder(customerOrder).getResultList();
		for (CustomerOrderItem customerOrderItem : customerOrderItems) {
			BigDecimal virtualStock = customerOrderItem.getProduct().getVirtualStock();
			BigDecimal orderedQuantity = customerOrderItem.getQuantity();
			int stockComparaison = virtualStock.compareTo(orderedQuantity);
			if(stockComparaison == -1) {
				avaibility = false;
			}
		}
		return avaibility;
	}

	//TODO write unit test
	public List<CustomerOrderItemsQuantityDetail> computeCustomerOrderItemsQuantityDetails(CustomerOrder customerOrder){
		Assert.notNull(customerOrder, "Customer order should not be null here");
		List<CustomerOrderItemsQuantityDetail> customerOrderItemsQuantityDetails = new ArrayList<CustomerOrderItemsQuantityDetail>();
		List<CustomerOrderItem> customerOrderItems = CustomerOrderItem.findCustomerOrderItemsByCustomerOrder(customerOrder).getResultList();
		BigDecimal availableQty = BigDecimal.ZERO;
		BigDecimal unAvailableQty = BigDecimal.ZERO;
		for (CustomerOrderItem customerOrderItem : customerOrderItems) {
			availableQty = customerOrderItem.getProduct().getVirtualStock();
			if(customerOrderItem.getQuantity().compareTo(customerOrderItem.getProduct().getVirtualStock()) == 1) {
				unAvailableQty = customerOrderItem.getQuantity().subtract(customerOrderItem.getProduct().getVirtualStock());
			}else {
				unAvailableQty = BigDecimal.ZERO;
			}
			CustomerOrderItemsQuantityDetail customerOrderItemsQuantityDetail = new CustomerOrderItemsQuantityDetail(customerOrderItem, availableQty, unAvailableQty);
			customerOrderItemsQuantityDetails.add(customerOrderItemsQuantityDetail);
		}
		return customerOrderItemsQuantityDetails;
	}
	@Override
	public List<Product> computeListOfProductForDelivery(CustomerOrder customerOrder) {
		Assert.notNull(customerOrder, "Customer order should not be null here");
		List<CustomerOrderItemsQuantityDetail> customerOrderItemsQuantityDetails = new ArrayList<CustomerOrderItemsQuantityDetail>();
		List<CustomerOrderItem> customerOrderItems = CustomerOrderItem.findCustomerOrderItemsByCustomerOrder(customerOrder).getResultList();
		BigDecimal availableQty = BigDecimal.ZERO;
		BigDecimal unAvailableQty = BigDecimal.ZERO;
		for (CustomerOrderItem customerOrderItem : customerOrderItems) {
			if(customerOrderItem.getQuantity().compareTo(customerOrderItem.getProduct().getVirtualStock()) != 1) {
				availableQty = customerOrderItem.getProduct().getVirtualStock();
				unAvailableQty = customerOrderItem.getQuantity().subtract(customerOrderItem.getProduct().getVirtualStock());
				CustomerOrderItemsQuantityDetail customerOrderItemsQuantityDetail = new CustomerOrderItemsQuantityDetail(customerOrderItem, availableQty, unAvailableQty);
				customerOrderItemsQuantityDetails.add(customerOrderItemsQuantityDetail);
			}
		}
		return null;
	}
	public PurchaseOrder generatePurchaseOrder(List<Product> products) {
		return null;
	}
	@Override
	public List<Product> computeListOfProductForInternalManufacturingVoucher(CustomerOrder customerOrder) {
		Assert.notNull(customerOrder, "Customer order should not be null here");
		return null;
	}

	@Override
	public Delivery generateDelivery(List<Product> products) {
		return null;
	}

	@Override
	public ManufacturingVoucher generateInternalManufacturingOrder(List<Product> products) {
		return null;
	}

	@Override
	public List<Product> computeListOfProductForPurchaseOrder(CustomerOrder customerOrder) {
		Assert.notNull(customerOrder, "Customer order should not be null here");
		return null;
	}

	@Override
	public <G> Fonction<CustomerOrderItem, G> processCustomerOrder(CustomerOrder customerOrder,Fonction<CustomerOrderItem, G> function) {
		Assert.notNull(customerOrder, "Customer order should not be null here");
		Assert.notNull(customerOrder, "Function should not be null here");
		List<CustomerOrderItem> customerOrderItems = CustomerOrderItem.findCustomerOrderItemsByCustomerOrder(customerOrder).getResultList();
		for (CustomerOrderItem customerOrderItem : customerOrderItems) {
			function.doFunction(customerOrderItem);
		}
		return function;
	}
}