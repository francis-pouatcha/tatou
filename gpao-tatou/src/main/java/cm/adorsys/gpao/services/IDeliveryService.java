package cm.adorsys.gpao.services;

import java.util.Set;

import cm.adorsys.gpao.model.Supply;
import cm.adorsys.gpao.model.SupplyItems;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.PurchaseOrder;

/**
 * @author clovisgakam
 *
 */
public interface IDeliveryService {
	public Supply getDeliveryFromOrder(PurchaseOrder order) ;
	public Supply getDeliveryFromInventory(Inventory inventory) ;
	public Set<SupplyItems> getDeliveryItems(PurchaseOrder order,Supply supply) ;
	public Set<SupplyItems> getDeliveryItems(Inventory inventory ,Supply supply) ;
	public Supply closeDelivery(Supply supply);
	public void calCulateDeliveryAmout(Supply supply) ;
	public Supply accepAllDeliveryItems(Supply supply) ;

	
}
