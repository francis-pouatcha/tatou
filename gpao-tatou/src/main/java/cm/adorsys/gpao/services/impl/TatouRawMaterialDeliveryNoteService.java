/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.RawMaterialDeliveryNote;
import cm.adorsys.gpao.model.RawMaterialDeliveryNoteItem;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IProductService;
import cm.adorsys.gpao.services.IRawMaterialDeliveryNoteService;
import cm.adorsys.gpao.services.IRawMaterialOrderService;

/**
 * @author bwa
 *
 */
@Service
public class TatouRawMaterialDeliveryNoteService implements IRawMaterialDeliveryNoteService {
	@Autowired
	IProductService productService;
	@Autowired
	IRawMaterialOrderService rawMaterialOrderService;
	@Override
	public void addRawMaterialDeliveryNoteItem(
			RawMaterialDeliveryNote rawMaterialDeliveryNote,
			RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem) {
		Assert.isTrue(rawMaterialDeliveryNote != null && rawMaterialDeliveryNote.getId() != null, "Invalid raw material, neither entity, nor id should not be null");
		Assert.notNull(rawMaterialDeliveryNoteItem, "The raw material item should not be null here");
		List<RawMaterialDeliveryNoteItem> rawMaterialDeliveryNoteItems = RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemsByRawMaterialDelveryNote(rawMaterialDeliveryNote).getResultList();
		boolean founded = false;
		for (RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem2 : rawMaterialDeliveryNoteItems) {
			if(ligneExist(rawMaterialDeliveryNoteItem, rawMaterialDeliveryNoteItem2)) {
				rawMaterialDeliveryNoteItem2.setOrderedQty(rawMaterialDeliveryNoteItem.getOrderedQty());
				rawMaterialDeliveryNoteItem2.setDeliveredQty(rawMaterialDeliveryNoteItem.getDeliveredQty());
				rawMaterialDeliveryNoteItem2.setUndeliveredQty(rawMaterialDeliveryNoteItem.getUndeliveredQty());
				rawMaterialDeliveryNoteItem2.merge();//update the ligne that exist.
				founded = true;
			}
		}
		if(!founded) {
			rawMaterialDeliveryNoteItem.setRawMaterialDelveryNote(rawMaterialDeliveryNote);
			rawMaterialDeliveryNoteItem.persist();
		}
	}

	private boolean ligneExist(
			RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem,
			RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem2) {
		return rawMaterialDeliveryNoteItem2.getRawMaterial().getId().equals(rawMaterialDeliveryNoteItem.getRawMaterial().getId());
	}

	@Override
	public boolean removeItems(List<Long> rawMaterialDeliveryNoteItemIds) {
		Assert.notNull(rawMaterialDeliveryNoteItemIds, "empty array list");
		for (Long rawMaterialDeliveryNoteItemId : rawMaterialDeliveryNoteItemIds) {
			if (rawMaterialDeliveryNoteItemId != null) {
				RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItem(rawMaterialDeliveryNoteItemId).remove();
			}
		}
		return true;
	}

	@Override
	public RawMaterialDeliveryNote generateRawMaterialDeliveryNoteFromRawMaterialOrder(
			RawMaterialOrder rawMaterialOrder) {
		Assert.isTrue(rawMaterialOrder != null && rawMaterialOrder.getId() != null,"The raw material should not be null, and should be a persited entity");
		List<RawMaterialDeliveryNote> foundedRawMaterialDeliveryNotes = RawMaterialDeliveryNote.findRawMaterialDeliveryNotesByDocRefEquals(rawMaterialOrder.getReference()).getResultList();
		if(!foundedRawMaterialDeliveryNotes.isEmpty()) {
			return foundedRawMaterialDeliveryNotes.iterator().next();
		}
		RawMaterialDeliveryNote rawMaterialDeliveryNote = new RawMaterialDeliveryNote();
		rawMaterialDeliveryNote.setDocRef(rawMaterialOrder.getReference());
		rawMaterialDeliveryNote.setCreatedBy(SecurityUtil.getUserName());
		rawMaterialDeliveryNote.setOrderDate(new Date());
		rawMaterialDeliveryNote.setOrderState(DocumentStates.BROUILLON);
		rawMaterialDeliveryNote.setOrigin(DeliveryOrigin.GENERATED);
		rawMaterialDeliveryNote.persist();
		List<RawMaterialOrderItem> rawMaterialOrderItems = RawMaterialOrderItem.findRawMaterialOrderItemsByRawMaterialOrder(rawMaterialOrder).getResultList();
		for (RawMaterialOrderItem rawMaterialOrderItem : rawMaterialOrderItems) {
			RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem = new RawMaterialDeliveryNoteItem();
			rawMaterialDeliveryNoteItem.setRawMaterialDelveryNote(rawMaterialDeliveryNote);
			rawMaterialDeliveryNoteItem.setOrderedQty(rawMaterialOrderItem.getQuantity());
			rawMaterialDeliveryNoteItem.setRawMaterial(rawMaterialOrderItem.getProduct());
			rawMaterialDeliveryNoteItem.persist();
		}
		return rawMaterialDeliveryNote;
	}

	@Override
	public boolean validateRawMaterialDeliveryNote(RawMaterialDeliveryNote rawMaterialDeliveryNote) {
		Assert.isTrue(rawMaterialDeliveryNote != null && rawMaterialDeliveryNote.getId() != null, "invalid raw material delivery note");
		if(DocumentStates.VALIDER.equals(rawMaterialDeliveryNote.getOrderState())) {
			return true;
		}
		List<RawMaterialOrder> rawMaterialOrders = RawMaterialOrder.findRawMaterialOrdersByReferenceEquals(rawMaterialDeliveryNote.getDocRef()).getResultList();
		if(rawMaterialOrders.isEmpty()) {
			return false;
		}
		RawMaterialOrder rawMaterialOrder = rawMaterialOrders.iterator().next();
		List<RawMaterialOrderItem> rawMaterialOrderItems = RawMaterialOrderItem.findRawMaterialOrderItemsByRawMaterialOrder(rawMaterialOrder).getResultList();
		List<RawMaterialDeliveryNoteItem> rawMaterialDeliveryNoteItems = RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItemsByRawMaterialDelveryNote(rawMaterialDeliveryNote).getResultList();
		Map<Product,RawMaterialOrderItem> ordoredProductMap = new Hashtable<Product, RawMaterialOrderItem>();
		
		for (RawMaterialOrderItem rawMaterialOrderItem : rawMaterialOrderItems) {
			ordoredProductMap.put(rawMaterialOrderItem.getProduct(), rawMaterialOrderItem);
		}
		Iterator<RawMaterialDeliveryNoteItem> deliveryNoteItemsIterator = rawMaterialDeliveryNoteItems.iterator();
		while (deliveryNoteItemsIterator.hasNext()) {
			RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem = (RawMaterialDeliveryNoteItem) deliveryNoteItemsIterator.next();
//			rawMaterialDeliveryNoteItem.setOrderedQty(ordoredProductMap.get(rawMaterialDeliveryNoteItem.getRawMaterial()).getQuantity());
			if(rawMaterialDeliveryNoteItem.getOrderedQty().compareTo(rawMaterialDeliveryNoteItem.getRawMaterial().getVirtualStock()) == 1) {
				throw new RuntimeException("Unsufficient raw material quantity");
			}
			rawMaterialDeliveryNoteItem.setDeliveredQty(ordoredProductMap.get(rawMaterialDeliveryNoteItem.getRawMaterial()).getQuantity());
			rawMaterialDeliveryNoteItem.setUndeliveredQty(rawMaterialDeliveryNoteItem.getOrderedQty().subtract(rawMaterialDeliveryNoteItem.getDeliveredQty()));
			rawMaterialDeliveryNoteItem.merge();
			updateProductVirtualStock(rawMaterialDeliveryNoteItem);
		}
		rawMaterialDeliveryNote.setOrderState(DocumentStates.VALIDER);
		rawMaterialDeliveryNote.setValidated(true);
		rawMaterialDeliveryNote.merge();
		rawMaterialOrder.setDelivered(true);
		rawMaterialOrderService.changeState(rawMaterialOrder, DocumentStates.FERMER);
		return true;
	}

	private void updateProductVirtualStock(	RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem) {
		Product rawMaterial = rawMaterialDeliveryNoteItem.getRawMaterial();
		BigDecimal virtualStock = rawMaterial.getVirtualStock();
		BigDecimal deliveredQty = rawMaterialDeliveryNoteItem.getDeliveredQty();
		BigDecimal subtract = virtualStock.subtract(deliveredQty);
		productService.updateProductVirtualStock(rawMaterial, subtract);
	}

}