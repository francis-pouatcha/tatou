package cm.adorsys.gpao.services;

import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.Product;

public interface IInventoryService {
	public Inventory buildInitialInventoryFormProduct(Product product);
	public void closeInventory(Inventory inventory);

}
