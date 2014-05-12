/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Production;
import cm.adorsys.gpao.model.ProductionStep;
import cm.adorsys.gpao.model.ProductionTask;
import cm.adorsys.gpao.model.ProductionTypeConfig;
import cm.adorsys.gpao.model.RawMaterialOrder;
import cm.adorsys.gpao.model.RawMaterialOrderItem;
import cm.adorsys.gpao.model.excepions.InsufficientRawMaterialException;
import cm.adorsys.gpao.model.excepions.InvalidEntityValueException;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.services.IProductionService;
import cm.adorsys.gpao.services.IProductionTypeConfigService;
import cm.adorsys.gpao.services.IRawMaterialOrderService;

/**
 * @author bwa
 *
 */
@Service
public class TatouProductionService implements IProductionService {

	@Autowired
	IRawMaterialOrderService rawMaterialOrderService;
	
	@Autowired
	IProductionTypeConfigService productionTypeConfigService;
	
	@Override
	public List<Production> findProductionsByProductionState(DocumentStates documentStates) {
		Assert.notNull(documentStates, "Document state should not be null here");
		return Production.findProductionsByProductionState(documentStates).getResultList();
	}

	@Override
	public List<Production> findOpenProductions() {
		return findProductionsByProductionState(DocumentStates.OUVERT);
	}

	@Override
	public List<Production> findClosedProductions() {
		return findProductionsByProductionState(DocumentStates.FERMER);
	}

	@Override
	public List<Production> findValidatedProductions() {
		return findProductionsByProductionState(DocumentStates.VALIDER);
	}

	@Override
	public List<ProductionStep> findProductionStepsByProduction(Production production) {
		return ProductionStep.findProductionStepsByProduction(production).getResultList();
	}

	@Override
	public List<ProductionTask> findProductionTasksByProduction(Production production) {
		List<ProductionStep> productionStepsByProduction = findProductionStepsByProduction(production);
		List<ProductionTask> productionTasks = new ArrayList<ProductionTask>();
		for (ProductionStep productionStep : productionStepsByProduction) {
			List<ProductionTask> tasks = ProductionTask.findProductionTasksByProductionStep(productionStep).getResultList();
			if(!tasks.isEmpty()) {
				productionTasks.addAll(tasks);
			}
		}
		return productionTasks;
	}

	@Override
	public ProductionStep findActiveProductionStep(Production production) {
		Assert.notNull(production, "The production should not be null");
		List<ProductionStep> steps = ProductionStep.findProductionStepsByProductionAndStepState(production, DocumentStates.OUVERT).getResultList();
		if(steps.isEmpty()) {
			return null;
		}
		return steps.iterator().next();
	}

	@Override
	public ProductionTask findActiveProductionTask(Production production) {
		ProductionStep activeProductionStep = findActiveProductionStep(production);
		if(activeProductionStep == null) {
			return null;
		}
		List<ProductionTask> tasks = ProductionTask.findProductionTasksByProductionStepAndTaskState(activeProductionStep, DocumentStates.OUVERT).getResultList();
		if(tasks.isEmpty()) {
			return null; //no active task was found
		}
		return tasks.iterator().next();
	}

	@Override
	public RawMaterialOrder findRawMaterialOrder(Production production) throws InvalidEntityValueException {
		Assert.notNull(production, "The production should not be null here");
		ManufacturingVoucher manufacturingVoucher = production.getManufacturingVoucher();
		Assert.notNull(manufacturingVoucher.getReference(), "The manufacturing voucher's reference should not be null !");
		List<RawMaterialOrder> rawMaterialOrders = RawMaterialOrder.findRawMaterialOrdersByDocRefEquals(manufacturingVoucher.getReference()).getResultList();
		if(rawMaterialOrders.isEmpty()) {
			throw new InvalidEntityValueException("There is no raw material order please create one !");
		}
		return rawMaterialOrders.iterator().next();
	}

	@Override
	public List<RawMaterialOrderItem> findRawMaterialOrderItems(Production production) throws InvalidEntityValueException {
		RawMaterialOrder rawMaterialOrder = findRawMaterialOrder(production);
		return RawMaterialOrderItem.findRawMaterialOrderItemsByRawMaterialOrder(rawMaterialOrder).getResultList();
	}

	@Override
	public List<ManufacturingVoucherItem> findManufacturingVoucherItems(Production production) {
		Assert.notNull(production, "The production should not be null here !");
		return ManufacturingVoucherItem.findManufacturingVoucherItemsByManufacturingVoucher(production.getManufacturingVoucher()).getResultList();
	}

	@Override
	public Production createProduction(ManufacturingVoucher manufacturingVoucher) throws InsufficientRawMaterialException {
		Assert.notNull(manufacturingVoucher, "Invalid manufacturing voucher. Sorry it is null !");
		boolean checkRawMaterialAvaibility = rawMaterialOrderService.checkRawMaterialAvaibility(manufacturingVoucher);
		if(checkRawMaterialAvaibility == false ) {
			throw new InsufficientRawMaterialException("Pas assez de matieres premieres. verifiez s'il n'ya pas une commande en attente de validation.");
		}
		//collect differents productions types
		
		Production production = new Production();
		production.setEndDate(manufacturingVoucher.getDelayDate());
		production.setManufacturingVoucher(manufacturingVoucher);
		production.setProductionState(DocumentStates.BROUILLON);
//		production.set
		production.setStartDate(new Date());
		production.setUserName(SecurityUtil.getUserName());
		production.persist();
		//generate productionsteps and productions tasks
		//get production type config my manufacturing voucher items, so depending on  the manufacturing voucher item we know the type of production to create.
		Map<ProductionTypeConfig, Collection<ManufacturingVoucherItem>> productTypeConfigsAndProductsMap = productionTypeConfigService.getProductTypeConfigsAndProducts(manufacturingVoucher);
		Set<ProductionTypeConfig> productionTypeConfigsKeysSet = productTypeConfigsAndProductsMap.keySet();
		for (ProductionTypeConfig productionTypeConfig : productionTypeConfigsKeysSet) {
			Collection<ManufacturingVoucherItem> products = productTypeConfigsAndProductsMap.get(productionTypeConfig);
			//generate productions for every product
			Collection<Production> productions = generateProductionForEveryProduct(manufacturingVoucher,products); 
		}
		return production;
	}

	private Set<Production> generateProductionForEveryProduct(ManufacturingVoucher manufacturingVoucher, Collection<ManufacturingVoucherItem> products) {
		Set<Production> productions = new HashSet<Production>(products.size());
		
		for (ManufacturingVoucherItem product : products) {
			Production production = createProductionProduct(manufacturingVoucher,product);
		}
		return null;
	}

	private Production createProductionProduct(ManufacturingVoucher manufacturingVoucher, ManufacturingVoucherItem productItem) {
		Production production = new Production();
		production.setEndDate(manufacturingVoucher.getDelayDate());
		production.setManufacturingVoucher(manufacturingVoucher);
		production.setProductionState(DocumentStates.BROUILLON);
		production.setProductionTypeConfig(productItem.getProduct().getProductionTypeConfig());
		production.setUserName(SecurityUtil.getUserName());
		return production;
	}
}