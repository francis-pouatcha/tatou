/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductionTypeConfig;
import cm.adorsys.gpao.services.IProductionTypeConfigService;
/**
 * 
 * @author bwa
 *
 */
@Service
public class TatouProductionTypeConfigService implements IProductionTypeConfigService {

	public Collection<ProductionTypeConfig> getProductionTypeConfigs(ManufacturingVoucher manufacturingVoucher){
		Assert.notNull(manufacturingVoucher, "Manufacturing voucher should not be null here !");
		List<ManufacturingVoucherItem> manufacturingVoucherItems = ManufacturingVoucherItem.findManufacturingVoucherItemsByManufacturingVoucher(manufacturingVoucher).getResultList();
		Collection<ProductionTypeConfig> productionTypeConfigs = new HashSet<ProductionTypeConfig>();
		for (ManufacturingVoucherItem manufacturingVoucherItem : manufacturingVoucherItems) {
			productionTypeConfigs.add(manufacturingVoucherItem.getProduct().getProductionTypeConfig());
		}
		return productionTypeConfigs;
	}
	public Map<ProductionTypeConfig, Collection<ManufacturingVoucherItem>> getProductTypeConfigsAndProducts(ManufacturingVoucher manufacturingVoucher){
		Assert.notNull(manufacturingVoucher, "Manufacturing voucher should not be null here !");
		List<ManufacturingVoucherItem> manufacturingVoucherItems = ManufacturingVoucherItem.findManufacturingVoucherItemsByManufacturingVoucher(manufacturingVoucher).getResultList();
		Map<ProductionTypeConfig,Collection<ManufacturingVoucherItem>> productionTypeConfigsMap = new HashMap<ProductionTypeConfig, Collection<ManufacturingVoucherItem>>();
		for (ManufacturingVoucherItem manufacturingVoucherItem : manufacturingVoucherItems) {
			appendElementInMap(manufacturingVoucherItem.getProduct().getProductionTypeConfig(), manufacturingVoucherItem, productionTypeConfigsMap);
		}
		return productionTypeConfigsMap;
	}
	private Map<ProductionTypeConfig, Collection<ManufacturingVoucherItem>> appendElementInMap(ProductionTypeConfig productionTypeConfig,ManufacturingVoucherItem manufacturingVoucherItem, Map<ProductionTypeConfig, Collection<ManufacturingVoucherItem>> map) {
		if(map.get(productionTypeConfig) == null ) {
			map.put(productionTypeConfig, new HashSet<ManufacturingVoucherItem>());
		}
		map.get(productionTypeConfig).add(manufacturingVoucherItem);
		return map;
	}
}
