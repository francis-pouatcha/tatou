package cm.adorsys.gpao.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.ProductIntrant;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import cm.adorsys.gpao.model.excepions.InsufficientRawMaterialException;

public interface IRawMaterialOrderService {

	@Transactional(rollbackFor=Exception.class)
	public RawMaterialOrder generateRawMaterialOrderFromManufacturingVoucher(ManufacturingVoucher manufacturingVoucher) throws InsufficientRawMaterialException;
	@Transactional(rollbackFor=Exception.class)
	public RawMaterialOrder createRawMaterialOrder(ManufacturingVoucher manufacturingVoucher);
	@Transactional(rollbackFor=Exception.class)
	public RawMaterialOrderItem createRawMaterialOrderItem(RawMaterialOrder rawMaterialOrder,ManufacturingVoucherItem manufacturingVoucherItem,List<ProductIntrant> productIntrants) throws InsufficientRawMaterialException;
	public boolean areThereEnoughRawMaterial(CustomerOrderItem customerOrderItem) ;
	public boolean addRawMaterialOrderItem(RawMaterialOrder rawMaterialOrder,RawMaterialOrderItem rawMaterialOrderItem);
	public boolean removeItems(List<Long> rawMaterialOrderItems);
}
