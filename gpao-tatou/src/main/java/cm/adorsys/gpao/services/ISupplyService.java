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
public interface ISupplyService {
	public Supply getSupplyFromOrder(PurchaseOrder order) ;
	public Supply getSupplyFromInventory(Inventory inventory) ;
	public Set<SupplyItems> getSupplyItems(PurchaseOrder order,Supply supply) ;
	public Set<SupplyItems> getSupplyItems(Inventory inventory ,Supply supply) ;
	public Supply closeSupply(Supply supply);
	public void calCulateSupplyAmout(Supply supply) ;
	public Supply accepAllSupplyItems(Supply supply) ;

	
}
