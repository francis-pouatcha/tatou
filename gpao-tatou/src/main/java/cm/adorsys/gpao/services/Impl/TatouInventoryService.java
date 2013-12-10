package cm.adorsys.gpao.services.Impl;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.InventoryItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IDeliveryService;
import cm.adorsys.gpao.services.IInventoryService;
import cm.adorsys.gpao.utils.CurrencyUtils;

@Service
public class TatouInventoryService implements IInventoryService {

	@Autowired
	private TatouDeliveryService deliveryService ;

	@Override
	public Inventory buildInitialInventoryFromProduct(Product product){
		Inventory	inventory = new Inventory();
		inventory.setCurrency(CurrencyUtils.getCompanyCurrency());
		inventory.setCompany(Company.getOwnComapny());
		inventory.persist();
		InventoryItems inventoryItems = new InventoryItems(product, inventory);
		inventoryItems.persist();
		inventory.getInventoryItems().add(inventoryItems);
		inventory.calculateGapAmount();
		inventory.merge();
		return inventory;
	}

	@Override
	public void closeInventory(Inventory inventory){
		inventory.setClosed(new Date());
		inventory.setClosedBy(SecurityUtil.getUserName());
		inventory.setStatus(DocumentStates.FERMER);
		Delivery delivery = deliveryService.getDeliveryFromInventory(inventory);
		delivery.persist();
		Set<DeliveryItems> deliveryItems = deliveryService.getDeliveryItems(inventory, delivery);
		delivery.setDeliveryItems(deliveryItems);
		deliveryService.closeDelivery(delivery);
	}

}
