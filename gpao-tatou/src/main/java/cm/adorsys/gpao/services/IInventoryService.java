package cm.adorsys.gpao.services;

import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.Product;

/**
 * @author clovisgakam
 *
 */
public interface IInventoryService {
	public Inventory buildInitialInventoryFromProduct(Product product);
	public void closeInventory(Inventory inventory);

}
