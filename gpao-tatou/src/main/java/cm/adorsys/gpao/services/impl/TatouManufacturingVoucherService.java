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
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductIntrant;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.Specificity;
import cm.adorsys.gpao.services.IManufacturingVoucherService;
import cm.adorsys.gpao.services.impl.function.Fonction;

/**
 * @author bwa
 *
 */
@Service
public class TatouManufacturingVoucherService implements IManufacturingVoucherService {

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
		manufacturingVoucherItem.persist();
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
	@Override
	public boolean validateManufacturingVoucher(
			ManufacturingVoucher manufacturingVoucher) {
		if(manufacturingVoucher == null) {
			return false;
		}
		List<ManufacturingVoucherItem> manufacturingVoucherItems = ManufacturingVoucherItem.findManufacturingVoucherItemsByManufacturingVoucher(manufacturingVoucher).getResultList();
		if(manufacturingVoucherItems.isEmpty()) {
			return false;
		}
		manufacturingVoucher.setDocumentState(DocumentStates.VALIDER);
		manufacturingVoucher.merge();
		return true;
	}
	public List<Specificity> findProductSpecificitysByManufacturingVoucherItem(ManufacturingVoucherItem manufacturingVoucherItem) {
		return ManufacturingVoucherItem.findProductSpecificityByManufacturingVoucherItem(manufacturingVoucherItem).getResultList();
    }
	@Override
	public List<ProductIntrant> getIntrant(ManufacturingVoucherItem manufacturingVoucherItem) {
		Assert.notNull(manufacturingVoucherItem, "The manufacturing voucher item should not null here");
		return ProductIntrant.findProductIntrantsByProduct(manufacturingVoucherItem.getProduct()).getResultList();
	}
}