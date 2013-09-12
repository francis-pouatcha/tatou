package cm.adorsys.gpao.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.InventoryItems;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.PurchaseOrder;

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
		Delivery delivery = new Delivery();
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
			deliveryItems.add(getDeliveryItemFromInventoryItem(inventoryItems2, delivery));
		}
		return deliveryItems;
	}
	
	private DeliveryItems getDeliveryItemFromInventoryItem(InventoryItems inventoryItems , Delivery delivery){
		DeliveryItems deliveryItems = new DeliveryItems();
		deliveryItems.setProduct(inventoryItems.getProduct());
		deliveryItems.setOrderQte(inventoryItems.getRealStock());
		deliveryItems.setQteReceive(inventoryItems.getRealStock());
		deliveryItems.setDelivery(delivery);
		deliveryItems.setAmountHt(inventoryItems.getProductPrice().multiply(BigDecimal.valueOf(inventoryItems.getRealStock().longValue())));
		deliveryItems.setTaxAmount(BigDecimal.ZERO);
		deliveryItems.setTaxAmount(deliveryItems.getAmountHt());
		deliveryItems.setUdm(inventoryItems.getUdm());
		return deliveryItems ;
	}


}
