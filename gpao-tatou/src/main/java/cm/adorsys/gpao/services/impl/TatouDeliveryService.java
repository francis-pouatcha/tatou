/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.util.Assert;

import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItem;
import cm.adorsys.gpao.services.IDeliveryService;

/**
 * @author bwa
 *
 */
public class TatouDeliveryService implements IDeliveryService {

	@Override
	public void computeAmount(Delivery delivery) {
		Assert.notNull(delivery, "The delivery shoud not be null here");
		List<DeliveryItem> deliveryItems = DeliveryItem.findDeliveryItemsByDelivery(delivery).getResultList();
		BigDecimal taxAmount = BigDecimal.ZERO;
		BigDecimal amountHt = BigDecimal.ZERO;
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (DeliveryItem deliveryItem : deliveryItems) {
			amountHt = amountHt.add(deliveryItem.getDeliveredQty().multiply(deliveryItem.getAmountHt()));
		}
		delivery.setAmoutHt(amountHt);
		delivery.setTaxeAmount(amountHt);
		delivery.persist();
	}

}
