/**
 * 
 */
package cm.adorsys.gpao.services;

import java.util.List;

import cm.adorsys.gpao.model.RawMaterialDeliveryNote;
import cm.adorsys.gpao.model.RawMaterialDeliveryNoteItem;
import cm.adorsys.gpao.model.RawMaterialOrder;

/**
 * @author bwa
 *
 */
public interface IRawMaterialDeliveryNoteService {
	public void addRawMaterialDeliveryNoteItem(RawMaterialDeliveryNote rawMaterialDeliveryNote,RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem);
	public boolean removeItems(List<Long> rawMaterialDeliveryNoteItemIds);
	public RawMaterialDeliveryNote generateRawMaterialDeliveryNoteFromRawMaterialOrder(RawMaterialOrder rawMaterialOrder);
}
