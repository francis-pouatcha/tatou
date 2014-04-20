/**
 * 
 */
package cm.adorsys.gpao.services;

import java.util.List;

import cm.adorsys.gpao.model.RawMaterialDeliveryNote;
import cm.adorsys.gpao.model.RawMaterialDeliveryNoteItem;

/**
 * @author bwa
 *
 */
public interface IRawMaterialDeliveryNoteService {
	public void addRawMaterialDeliveryNoteItem(RawMaterialDeliveryNote rawMaterialDeliveryNote,RawMaterialDeliveryNoteItem rawMaterialDeliveryNoteItem);
	public boolean removeItems(List<Long> rawMaterialDeliveryNoteItemIds);
}
