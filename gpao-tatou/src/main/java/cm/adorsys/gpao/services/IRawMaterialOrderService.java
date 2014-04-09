package cm.adorsys.gpao.services;

import java.util.List;

import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.ProductIntrant;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;

public interface IRawMaterialOrderService {

	public RawMaterialOrder generateRawMaterialOrderFromManufacturingVoucher(ManufacturingVoucher manufacturingVoucher);
	public RawMaterialOrder createRawMaterialOrder(ManufacturingVoucher manufacturingVoucher);
	public RawMaterialOrderItem createRawMaterialOrderItem(RawMaterialOrder rawMaterialOrder,ManufacturingVoucherItem manufacturingVoucherItem,List<ProductIntrant> productIntrants);
	public boolean areThereEnoughRawMaterial(CustomerOrderItem customerOrderItem) ;
}
