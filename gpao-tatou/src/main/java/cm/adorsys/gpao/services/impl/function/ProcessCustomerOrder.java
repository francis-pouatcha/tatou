package cm.adorsys.gpao.services.impl.function;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItem;
import cm.adorsys.gpao.model.DeliveryOrigin;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.ManufacturingVoucher;
import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.security.SecurityUtil;
import cm.adorsys.gpao.utils.GpaoSequenceGenerator;
import cm.adorsys.gpao.utils.UdmUtils;

public class ProcessCustomerOrder implements Fonction<CustomerOrderItem,Void>{
	private Delivery delivery;
	private ManufacturingVoucher manufacturingVoucher ;
	private PurchaseOrder purchaseOrder;
	
	public ProcessCustomerOrder() {
		super();
	}
	@Override
	public Void doFunction(CustomerOrderItem ... customerOrderItems ) {
		CustomerOrderItem customerOrderItem = null;
		if(customerOrderItems.length >= 1) {
			customerOrderItem = customerOrderItems[0];
		}
		
		Assert.notNull(customerOrderItem, "The customer order item must not be null here");
		
		Product product = customerOrderItem.getProduct();
		BigDecimal convertedCustomerOrderItemQuantity = BigDecimal.ZERO;
		
		if(!customerOrderItem.getUdm().equals(product.getDefaultUdm())) {
			convertedCustomerOrderItemQuantity = UdmUtils.convert(customerOrderItem.getUdm(), product.getDefaultUdm(), customerOrderItem.getQuantity());
		}else {
			convertedCustomerOrderItemQuantity = customerOrderItem.getQuantity();
		}
		
		if(product.getVirtualStock().compareTo(BigDecimal.ZERO) == -1) {
			initManufacturingVoucher(customerOrderItem.getCustomerOrder());
			createNewManufacturingVoucherItem(customerOrderItem, convertedCustomerOrderItemQuantity);
			return null;
		}
		
		BigDecimal virtualStock = BigDecimal.ZERO;
		
		if(product.getIsRoundedProduct()) {//i.e shoes doesn't accept half, or decimal values. theirs values need to be rounded.
			virtualStock = product.getVirtualStock().round(new MathContext(0, RoundingMode.DOWN));
		}else {
			virtualStock = product.getVirtualStock();
		}
		
		if(convertedCustomerOrderItemQuantity.compareTo(virtualStock) <= 0) {//if ordered qty less than or equal to virtual stock.
			initDelivery(customerOrderItem.getCustomerOrder());
			createNewDeliveryItem(customerOrderItem,convertedCustomerOrderItemQuantity,BigDecimal.ZERO);
			return null;
		}else if(convertedCustomerOrderItemQuantity.compareTo(virtualStock) == 1) {
			BigDecimal availableQty = product.getVirtualStock();
			BigDecimal unAvailableQty = convertedCustomerOrderItemQuantity.subtract(virtualStock);
			initDelivery(customerOrderItem.getCustomerOrder());
			initManufacturingVoucher(customerOrderItem.getCustomerOrder());
			createNewDeliveryItem(customerOrderItem, availableQty, unAvailableQty);
			if(product.getCanBeProduce().equals(Boolean.FALSE)) {
				createNewPurchaseOrderItem(customerOrderItem, product, unAvailableQty);
			}else {
				createNewManufacturingVoucherItem(customerOrderItem, unAvailableQty);
			}
		}
		
		return null;
	}
	private ManufacturingVoucherItem createNewManufacturingVoucherItem(CustomerOrderItem customerOrderItem,BigDecimal convertedCustomerOrderItemQuantity) {
		ManufacturingVoucherItem manufacturingVoucherItem = new ManufacturingVoucherItem();
		manufacturingVoucherItem.setManufacturingVoucher(manufacturingVoucher);
		manufacturingVoucherItem.setProduct(customerOrderItem.getProduct());
		manufacturingVoucherItem.setDelayDate(DateUtils.addDays(new Date(), 30));
		manufacturingVoucherItem.setQuantity(convertedCustomerOrderItemQuantity);
		manufacturingVoucherItem.setComment(" ");
		manufacturingVoucherItem.persist();
		return manufacturingVoucherItem;
	}
	private DeliveryItem createNewDeliveryItem(CustomerOrderItem customerOrderItem,
			BigDecimal convertedCustomerOrderItemQuantity,BigDecimal unDeliveredQty) {
		Assert.notNull(customerOrderItem, "The customer order item should not be null here");
		Assert.notNull(convertedCustomerOrderItemQuantity, "The ordered quantity should not be null here");
		Assert.notNull(convertedCustomerOrderItemQuantity, "The Un delivered Qty should not be null here");
		DeliveryItem deliveryItem = new DeliveryItem();
		deliveryItem.setDelivery(this.delivery);
		deliveryItem.setAmountHt(customerOrderItem.getAmountHt());
		deliveryItem.setDeliveredQty(convertedCustomerOrderItemQuantity);
		//TODO		deliveryItem.setExpirationDate(expirationDate);
		deliveryItem.setOrderedQty(convertedCustomerOrderItemQuantity);
		deliveryItem.setProduct(customerOrderItem.getProduct());
		deliveryItem.setUdm(customerOrderItem.getProduct().getDefaultUdm());
		deliveryItem.setUndeliveredQty(unDeliveredQty);
		deliveryItem.persist();
		customerOrderItem.getProduct().setVirtualStock(customerOrderItem.getProduct().getVirtualStock().subtract(convertedCustomerOrderItemQuantity));
		customerOrderItem.getProduct().merge();
		return deliveryItem;
	}

	private void createNewPurchaseOrderItem(CustomerOrderItem customerOrderItem,
			Product product, BigDecimal unAvailableQty) {
		initPurchaseOrder(customerOrderItem.getCustomerOrder());
		OrderItems orderItems = new OrderItems();
		orderItems.setReference(""+OrderItems.findOrderItemssByPurchaseOrder(purchaseOrder).getResultList().size()+1);
		orderItems.setProduct(product);
		orderItems.setPurchaseOrder(purchaseOrder);
		orderItems.setQuantity(unAvailableQty);
		orderItems.setSubTotal(customerOrderItem.getAmountHt().multiply(unAvailableQty));
		orderItems.setUdm(product.getDefaultUdm());
		orderItems.persist();
	}
	protected void initPurchaseOrder(CustomerOrder customerOrder) {
		if(this.purchaseOrder == null) {
			this.purchaseOrder = new PurchaseOrder();
			Company company = Company.findAllCompanys().iterator().next();
			purchaseOrder.setCompany(company);
			purchaseOrder.setCurrency(company.getDevise());
			purchaseOrder.setCreated(new Date());
			purchaseOrder.setCreatedBy(SecurityUtil.getUserName());
			purchaseOrder.setInvoiced(Boolean.FALSE);
			purchaseOrder.setOrderDate(new Date());
//			purchaseOrder.setSaleTaxes(customerOrder.getTaxes());
			purchaseOrder.setOrderState(DocumentStates.BROUILLON);
			purchaseOrder.setSupplier(Partner.findAllPartners().iterator().next());
			purchaseOrder.setReference(GpaoSequenceGenerator.getSequence(Long.valueOf(7), GpaoSequenceGenerator.PORCHASE_SEQUENCE_PREFIX));
			purchaseOrder.persist();
		}
	}
	protected void initDelivery(CustomerOrder customerOrder) {
		if(this.delivery == null) {
			Company company = Company.findCompany(new Long(0));
			this.delivery = new Delivery();
			delivery.setOrigin(DeliveryOrigin.PRODUCTION);
			delivery.setCompany(company);
			delivery.setCreatedDate(new Date());
			delivery.setCurreny(customerOrder.getCurrency());
			delivery.setCustomer(customerOrder.getCustomer());
			delivery.setDocRef(customerOrder.getReference());
			delivery.setStatus(DocumentStates.BROUILLON);
			delivery.setCreateBy(SecurityUtil.getUserName());
			delivery.setReference(GpaoSequenceGenerator.getSequence(Long.valueOf(7), GpaoSequenceGenerator.DELIVERY_SEQUENCE_PREFIX));
			delivery.persist();
		}
	}
	protected void initManufacturingVoucher(CustomerOrder customerOrder) {
		if(this.manufacturingVoucher == null ) {
			this.manufacturingVoucher = new ManufacturingVoucher();
			manufacturingVoucher.setCreateDate(new Date());
			manufacturingVoucher.setCreatedBy(SecurityUtil.getUserName());
			manufacturingVoucher.setCustomer(customerOrder.getCustomer());
			manufacturingVoucher.setCustomerOrder(customerOrder);
			manufacturingVoucher.setDelayDate(DateUtils.addDays(new Date(), 30));
			manufacturingVoucher.setDocumentState(DocumentStates.BROUILLON);
			manufacturingVoucher.setReference(GpaoSequenceGenerator.getSequence(Long.valueOf(7), GpaoSequenceGenerator.MANUFACTURINGVOUCHER_SEQUENSE_PREFIX));
			manufacturingVoucher.persist();
		}
	}
}