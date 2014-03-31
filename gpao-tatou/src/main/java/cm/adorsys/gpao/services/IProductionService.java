/**
 * 
 */
package cm.adorsys.gpao.services;

import java.util.List;

import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.services.impl.Fonction;

/**
 * @author bwa
 *
 */
//TODO write a unit test, to check if sum of product to deliver + sum of product to manufacture is equal to the sum of product in the Customer Order
public interface IProductionService {
	/**
	 * Check if product form this order are available. If available, a delivery will be created, else a manufacturing order will be
	 * created for production.
	 * @param customerOrder
	 * @return
	 */
	public boolean checkProductsAvaibility(CustomerOrder customerOrder);
	/**
	 * @param customerOrder
	 * @return
	 */
	public List<CustomerOrderItemsQuantityDetail> computeCustomerOrderItemsQuantityDetails(CustomerOrder customerOrder);
	/**
	 * @param customerOrder
	 * @return
	 */
	public List<Product> computeListOfProductForPurchaseOrder(CustomerOrder  customerOrder);
	/**
	 * @param customerOrder
	 * @return
	 */
	public List<Product> computeListOfProductForDelivery(CustomerOrder  customerOrder);
	/**
	 * Generate a Purchase order, in case when the ordered products are unavailable
	 * @param customerOrder
	 * @return {@link PurchaseOrder  purchase order.}
	 */
	public PurchaseOrder generatePurchaseOrder(List<Product> customerOrder);
	/**
	 * @param customerOrder
	 * @return
	 */
	public List<Product> computeListOfProductForInternalManufacturingVoucher(CustomerOrder  customerOrder);
	/**
	 * Generate a Delivery, with a list of available product.
	 * @param customerOrder
	 */
	public Delivery generateDelivery(List<Product> customerOrder);
	public ManufacturingVoucher generateInternalManufacturingOrder(List<Product> customerOrder);
	
	public <G> Fonction<CustomerOrderItem, G> processCustomerOrder(CustomerOrder customerOrder,Fonction<CustomerOrderItem, G>  function);
	/* TODO change the return type from void t RawMaterialVoucher
	void generateInternalRawMaterialOrder(ManufacturingVoucher manufacturingVoucher);
	//TODO give the internal manufacturing order as parameter here.
	void generateInternalDeliveryBordereau(ManufacturingVoucher manufacturingVoucher); */
}