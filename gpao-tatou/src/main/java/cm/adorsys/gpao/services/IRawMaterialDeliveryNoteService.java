/**
 * 
 */
package cm.adorsys.gpao.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(rollbackFor=Throwable.class)
	public boolean validateRawMaterialDeliveryNote(RawMaterialDeliveryNote rawMaterialDeliveryNote);
}
