/**
 * 
 */
package cm.adorsys.gpao.services;

import java.util.List;

import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductIntrant;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.Specificity;
import cm.adorsys.gpao.services.impl.function.Fonction;

/**
 * @author bwa
 *
 */
//TODO write a unit test, to check if sum of product to deliver + sum of product to manufacture is equal to the sum of product in the Customer Order
public interface IManufacturingVoucherService {
	
	/**
	 * Generate a Purchase order, in case when the ordered products are unavailable
	 * @param customerOrder
	 * @return {@link PurchaseOrder  purchase order.}
	 */
	public PurchaseOrder generatePurchaseOrder(List<Product> customerOrder);
	/**
	 * Generate a Delivery, with a list of available product.
	 * @param customerOrder
	 */
	public Delivery generateDelivery(List<Product> customerOrder);
	public <G> Fonction<CustomerOrderItem, G> processCustomerOrder(CustomerOrder customerOrder,Fonction<CustomerOrderItem, G>  function);
	public <G> Fonction<ManufacturingVoucherItem, G> processManufacturingVoucher(ManufacturingVoucher manufacturingVoucher,Fonction<ManufacturingVoucherItem, G>  function);
	public ManufacturingVoucherItem addManufacturingVoucherItem(ManufacturingVoucher manufacturingVoucher,ManufacturingVoucherItem manufacturingVoucherItem);
	/* TODO change the return type from void t RawMaterialVoucher
	void generateInternalRawMaterialOrder(ManufacturingVoucher manufacturingVoucher);
	//TODO give the internal manufacturing order as parameter here.
	void generateInternalDeliveryBordereau(ManufacturingVoucher manufacturingVoucher); */
	public boolean deleteManufacturingOrderItems(List<Long> manufacturingVoucherItemIds);
	public boolean validateManufacturingVoucher(ManufacturingVoucher manufacturingVoucher);
	public List<Specificity> findProductSpecificitysByManufacturingVoucherItem(ManufacturingVoucherItem manufacturingVoucherItem);
	public List<ProductIntrant> getIntrant(ManufacturingVoucherItem manufacturingVoucherItem);
}