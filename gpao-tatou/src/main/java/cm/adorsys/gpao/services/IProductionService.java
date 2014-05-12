/**
 * 
 */
package cm.adorsys.gpao.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.Production;
import cm.adorsys.gpao.model.ProductionStep;
import cm.adorsys.gpao.model.ProductionTask;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import cm.adorsys.gpao.model.excepions.InsufficientRawMaterialException;
import cm.adorsys.gpao.model.excepions.InvalidEntityValueException;

/**
 * @author bwa
 *
 */
public interface IProductionService {
	public List<Production> findProductionsByProductionState(DocumentStates documentStates);
	public List<Production> findOpenProductions();
	public List<Production> findClosedProductions();
	public List<Production> findValidatedProductions();
	public List<ProductionStep> findProductionStepsByProduction(Production production);
	public List<ProductionTask> findProductionTasksByProduction(Production production);
	public ProductionStep findActiveProductionStep(Production production);
	public ProductionTask findActiveProductionTask(Production production);
	public RawMaterialOrder findRawMaterialOrder(Production production) throws InvalidEntityValueException;
	public List<RawMaterialOrderItem> findRawMaterialOrderItems(Production production) throws InvalidEntityValueException;;
	public List<ManufacturingVoucherItem> findManufacturingVoucherItems(Production production);
	@Transactional(rollbackFor=Throwable.class)
	public Production createProduction(ManufacturingVoucher manufacturingVoucher) throws InsufficientRawMaterialException;
}
