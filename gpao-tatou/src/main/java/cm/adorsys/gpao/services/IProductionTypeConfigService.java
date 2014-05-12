package cm.adorsys.gpao.services;

import java.util.Collection;
import java.util.Map;

import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.ProductionTypeConfig;

public interface IProductionTypeConfigService {

	public Collection<ProductionTypeConfig> getProductionTypeConfigs(ManufacturingVoucher manufacturingVoucher);

	public Map<ProductionTypeConfig, Collection<ManufacturingVoucherItem>> getProductTypeConfigsAndProducts(ManufacturingVoucher manufacturingVoucher);

}
