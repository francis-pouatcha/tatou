package cm.adorsys.gpao.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Supply;
import cm.adorsys.gpao.model.SupplyItems;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.InventoryItems;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.excepions.UnmatchUnitOfMesureGroupException;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IDeliveryService;
import cm.adorsys.gpao.utils.UdmUtils;

@Service
public class TatouSupplyService implements IDeliveryService {

	@Override
	public Supply getDeliveryFromOrder(PurchaseOrder order) {
		Supply supply = new Supply(order);
		return supply;
	}

	@Override
	public Set<SupplyItems> getDeliveryItems(PurchaseOrder order,Supply supply) {
		List<OrderItems> orderItems = order.getOrderItems();
		Set<SupplyItems> supplyItems = new HashSet<SupplyItems>();
		for (OrderItems items : orderItems) {
			SupplyItems deliveryItems2 = new SupplyItems(items,supply);
			deliveryItems2.persist();
			supplyItems.add(deliveryItems2);
		}
		return supplyItems;
	}

	@Override
	public void calCulateDeliveryAmout(Supply supply) {
		Set<SupplyItems> supplyItems = supply.getSupplyItems();
		supply.initAmount();
		for (SupplyItems deliveryItems2 : supplyItems) {
			supply.increaseAmountFromDeliveryItem(deliveryItems2);
		}
		supply.computeTaxeAmountAndTaxedAmount();
	}
	@Override
	public Supply getDeliveryFromInventory(Inventory inventory) {
		Supply supply = new Supply(Company.getOwnComapny());
		supply.setDocRef(inventory.getReference());
		supply.setCurrency(inventory.getCurrency()) ;
		supply.setOrigin(DeliveryOrigin.INVENTAIRE) ;
		return supply;
	}

	@Override
	public Set<SupplyItems> getDeliveryItems(Inventory inventory,
			Supply supply) {
		Set<SupplyItems> supplyItems = new HashSet<SupplyItems>();
		Set<InventoryItems> inventoryItems = inventory.getInventoryItems();
		for (InventoryItems inventoryItems2 : inventoryItems) {
			SupplyItems inventoryItem = getDeliveryItemFromInventoryItem(inventoryItems2, supply);
			inventoryItem.persist();
			supplyItems.add(inventoryItem);
		}
		return supplyItems;
	}

	private SupplyItems getDeliveryItemFromInventoryItem(InventoryItems inventoryItems , Supply supply){
		SupplyItems supplyItems = new SupplyItems();
		supplyItems.setProduct(inventoryItems.getProduct());
		supplyItems.setOrderQte(inventoryItems.getRealStock());
		supplyItems.setQteReceive(inventoryItems.getRealStock());
		supplyItems.setQteInStock(inventoryItems.getRealStock());
		supplyItems.setSupply(supply);
		supplyItems.setAmountHt(inventoryItems.getProductPrice().multiply(BigDecimal.valueOf(inventoryItems.getRealStock().longValue())));
	/*	deliveryItems.setTaxAmount(BigDecimal.ZERO);
		deliveryItems.setTaxedAmount(deliveryItems.getAmountHt());*/
		supplyItems.setUdm(inventoryItems.getUdm());
		return supplyItems ;
	}

	@Override
	public Supply closeDelivery(Supply supply) throws UnmatchUnitOfMesureGroupException {
		if(DocumentStates.OUVERT.equals(supply.getStatus())){
			Set<SupplyItems> supplyItems = supply.getSupplyItems();
			for (SupplyItems deliveryItems2 : supplyItems) {
				Product product = deliveryItems2.getProduct();
				BigDecimal convert = UdmUtils.convert(deliveryItems2.getUdm(), product.getDefaultUdm(), deliveryItems2.getQteReceive());
				product.increaseVirtualQuantity(convert);
				product.merge();
			}
			supply.setStatus(DocumentStates.FERMER)	;
			supply.setReceivedDate(new Date());
			supply.setReceiveBy(SecurityUtil.getUserName());
			supply = supply.merge();
		}
		return supply ;
	}
	@Override
	public Supply accepAllDeliveryItems(Supply supply)  {
		if(DocumentStates.OUVERT.equals(supply.getStatus())){
			Set<SupplyItems> supplyItems = supply.getSupplyItems();
			for (SupplyItems deliveryItems2 : supplyItems) {
				deliveryItems2.acceptAllOrderQte();
				deliveryItems2.merge();
			}
			supply = supply.merge();
		}
		return supply ;
	}
	public  SupplyItems getDeliveryItemFromUnreceiveQte(SupplyItems supplyItems){
		SupplyItems deliveryItems2 =null;
		if(supplyItems.hasUnreceiveQte()){
			deliveryItems2 = new SupplyItems();
			deliveryItems2.setProduct(supplyItems.getProduct());
			deliveryItems2.setOrderQte(supplyItems.getQteUnreceive());
			deliveryItems2.setExpirationDate(supplyItems.getExpirationDate());
			deliveryItems2.setAmountHt(supplyItems.getAmountHt());
			/*deliveryItems2.setTaxAmount(deliveryItems.getTaxAmount());
			deliveryItems2.setTaxedAmount(deliveryItems.getTaxedAmount());*/
			deliveryItems2.setUdm(supplyItems.getUdm());
		}
		return deliveryItems2 ;
	}

	public Supply produiceDeliveryForUnReceiveArticle(Supply supply){
		PurchaseOrder order = PurchaseOrder.findPurchaseOrderByReferenceEquals(supply.getDocRef()).getSingleResult();
		Supply delivery2 = new Supply(order);
		delivery2.persist();
		for (SupplyItems supplyItems : supply.getSupplyItems()) {
			SupplyItems itemFromUnreceiveQte = getDeliveryItemFromUnreceiveQte(supplyItems);
			if(itemFromUnreceiveQte!=null){
				itemFromUnreceiveQte.setSupply(delivery2);
				itemFromUnreceiveQte.persist();
				delivery2.increaseAmountFromDeliveryItem(itemFromUnreceiveQte);
			}
		} 
		return delivery2.merge();

	}

}
