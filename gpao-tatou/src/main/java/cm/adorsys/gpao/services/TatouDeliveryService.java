package cm.adorsys.gpao.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
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


}
