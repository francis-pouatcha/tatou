package cm.adorsys.gpao.services.impl;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.SupplyItems;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.InventoryItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Supply;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IInventoryService;
import cm.adorsys.gpao.utils.CurrencyUtils;

@Service
public class TatouInventoryService implements IInventoryService {

	@Autowired
	private TatouSupplyService deliveryService ;

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
		Supply supply = deliveryService.getSupplyFromInventory(inventory);
		supply.persist();
		Set<SupplyItems> supplyItems = deliveryService.getSupplyItems(inventory, supply);
		supply.setSupplyItems(supplyItems);
		deliveryService.closeSupply(supply);
	}

}
