/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.services.IProductionService;
import cm.adorsys.gpao.services.impl.function.Fonction;

/**
 * @author bwa
 *
 */
@Service
public class TatouProductionService implements IProductionService {

	public PurchaseOrder generatePurchaseOrder(List<Product> products) {
		return null;
	}
	@Override
	public Delivery generateDelivery(List<Product> products) {
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
	@Override
	public <G> Fonction<ManufacturingVoucherItem, G> processManufacturingVoucher(
			ManufacturingVoucher manufacturingVoucher,
			Fonction<ManufacturingVoucherItem, G> function) {
		Assert.noNullElements(new Object[] {manufacturingVoucher,function}, "Null arguments are not allowed here. Please check our params [manufacturingVoucher, function]");
		Collection<ManufacturingVoucherItem> manufacturingVoucherItems = ManufacturingVoucherItem.findManufacturingVoucherItemsByManufacturingVoucher(manufacturingVoucher).getResultList();
		for (ManufacturingVoucherItem manufacturingVoucherItem : manufacturingVoucherItems) {
			function.doFunction(manufacturingVoucherItem);
		}
		return function;
	}
	@Override
	public ManufacturingVoucherItem addManufacturingVoucherItem(
			ManufacturingVoucher manufacturingVoucher,
			ManufacturingVoucherItem manufacturingVoucherItem) {
		Assert.noNullElements(new Object[] {manufacturingVoucher,manufacturingVoucherItem}, "Null arguments are not allowed here. Please check our params [manufacturingVoucher, manufacturingVoucherItem]");
		manufacturingVoucherItem.setManufacturingVoucher(manufacturingVoucher);
		manufacturingVoucher.persist();
		return manufacturingVoucherItem;
	}
	public boolean deleteManufacturingOrderItems(List<Long> manufacturingVoucherItemIds) {
		if(manufacturingVoucherItemIds == null || manufacturingVoucherItemIds.isEmpty()) {
			return false;
		}
		for (Long manufacturingVoucherItemId : manufacturingVoucherItemIds) {
			if(manufacturingVoucherItemId != null) {
				ManufacturingVoucherItem.findManufacturingVoucherItem(manufacturingVoucherItemId).remove();
			}
		}
		return true;
	}
}