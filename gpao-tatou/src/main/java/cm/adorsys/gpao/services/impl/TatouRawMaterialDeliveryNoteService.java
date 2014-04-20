/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.RawMaterialDeliveryNote;
import cm.adorsys.gpao.model.RawMaterialDeliveryNoteItem;
import cm.adorsys.gpao.services.IRawMaterialDeliveryNoteService;

/**
 * @author bwa
 *
 */
@Service
public class TatouRawMaterialDeliveryNoteService implements
		IRawMaterialDeliveryNoteService {

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
			if (rawMaterialDeliveryNoteItemIds != null) {
				RawMaterialDeliveryNoteItem.findRawMaterialDeliveryNoteItem(rawMaterialDeliveryNoteItemId).remove();
			}
		}
		return true;
	}

}
