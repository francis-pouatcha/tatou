/**
 * 
 */
package cm.adorsys.gpao.services.impl.function;

import java.util.ArrayList;
import java.util.List;

import cm.adorsys.gpao.model.ManufacturingVoucherItem;

/**
 * @author bwa
 *
 */
public class FindManufacturingVoucherItems implements
		Fonction<cm.adorsys.gpao.model.ManufacturingVoucherItem, Void> {
	List<ManufacturingVoucherItem> manufacturingVoucherItems = new ArrayList<ManufacturingVoucherItem>();
	@Override
	public  Void doFunction(ManufacturingVoucherItem ... u) {
		if(u == null) {
			return null;
		}
		manufacturingVoucherItems.add(u[0]);
		return null;
	}
	public List<ManufacturingVoucherItem> getManufacturingVoucherItems() {
		return manufacturingVoucherItems;
	}
}
