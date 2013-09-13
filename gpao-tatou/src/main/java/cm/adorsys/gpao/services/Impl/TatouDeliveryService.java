package cm.adorsys.gpao.services.Impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.InventoryItems;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IDeliveryService;

@Service
public class TatouDeliveryService implements IDeliveryService {

	@Override
	public Delivery getDeliveryFromOrder(PurchaseOrder order) {
		Delivery delivery = new Delivery(order);
		return delivery;
	}

	@Override
	public Set<DeliveryItems> getDeliveryItems(PurchaseOrder order,Delivery delivery) {
		Set<OrderItems> orderItems = order.getOrderItems();
		Set<DeliveryItems> deliveryItems = new HashSet<DeliveryItems>();
		for (OrderItems items : orderItems) {
			deliveryItems.add(new DeliveryItems(items,delivery));
		}
		return deliveryItems;
	}

	@Override
	public void calCulateDeliveryAmout(Delivery delivery) {
		Set<DeliveryItems> deliveryItems = delivery.getDeliveryItems();
		delivery.initAmount();
		for (DeliveryItems deliveryItems2 : deliveryItems) {
			delivery.increaseAmountFromDeliveryItem(deliveryItems2);
		}
	}
	@Override
	public Delivery getDeliveryFromInventory(Inventory inventory) {
		Delivery delivery = new Delivery(Company.getOwnComapny());
		delivery.setDocRef(inventory.getReference());
		delivery.setCurrency(inventory.getCurrency()) ;
		delivery.setOrigin(DeliveryOrigin.INVENTORY) ;
		return delivery;
	}

	@Override
	public Set<DeliveryItems> getDeliveryItems(Inventory inventory,
			Delivery delivery) {
		Set<DeliveryItems> deliveryItems = new HashSet<DeliveryItems>();
		Set<InventoryItems> inventoryItems = inventory.getInventoryItems();
		for (InventoryItems inventoryItems2 : inventoryItems) {
			DeliveryItems inventoryItem = getDeliveryItemFromInventoryItem(inventoryItems2, delivery);
			inventoryItem.persist();
			deliveryItems.add(inventoryItem);
		}
		return deliveryItems;
	}

	private DeliveryItems getDeliveryItemFromInventoryItem(InventoryItems inventoryItems , Delivery delivery){
		DeliveryItems deliveryItems = new DeliveryItems();
		deliveryItems.setProduct(inventoryItems.getProduct());
		deliveryItems.setOrderQte(inventoryItems.getRealStock());
		deliveryItems.setQteReceive(inventoryItems.getRealStock());
		System.out.println(deliveryItems.getOrderQte());
		deliveryItems.setDelivery(delivery);
		deliveryItems.setAmountHt(inventoryItems.getProductPrice().multiply(BigDecimal.valueOf(inventoryItems.getRealStock().longValue())));
		deliveryItems.setTaxAmount(BigDecimal.ZERO);
		deliveryItems.setTaxedAmount(deliveryItems.getAmountHt());
		deliveryItems.setUdm(inventoryItems.getUdm());
		return deliveryItems ;
	}

	@Override
	public Delivery closeDelivery(Delivery delivery) {
		if(DocumentStates.OPENED.equals(delivery.getStatus())){
			delivery.setStatus(DocumentStates.CLOSED)	;
			delivery.setReceivedDate(new Date());
			delivery.setReceiveBy(SecurityUtil.getUserName());
			delivery = delivery.merge();
		}
		return delivery ;
	}

}
