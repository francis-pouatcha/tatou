package cm.adorsys.gpao.web;

import java.util.Currency;

import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Contacts;
import cm.adorsys.gpao.model.Delivery;
import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.GpaoUser;
import cm.adorsys.gpao.model.GpaoUserGroup;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductFamily;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.Tenders;
import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.WareHouses;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}
	 public Converter<Inventory, String> getInventoryToStringConverter() {
	        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Inventory, java.lang.String>() {
	            public String convert(Inventory inventory) {
	                return new StringBuilder().append(inventory.toString()).toString();
	            }
	        };
	    }
	 public Converter<Tenders, String> getTendersToStringConverter() {
	        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Tenders, java.lang.String>() {
	            public String convert(Tenders tenders) {
	            	return new StringBuilder().append(tenders.toString()).toString();
	            	}
	        };
	    }
	public Converter<String, Company> getStringToCompanyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Company>() {
            public cm.adorsys.gpao.model.Company convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Company.class);
            }
        };
    }
	public Converter<Company, String> getCompanyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Company, java.lang.String>() {
            public String convert(Company company) {
                return new StringBuilder().append(company.toString()).toString();
            }
        };
    }
    
    public Converter<Contacts, String> getContactsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Contacts, java.lang.String>() {
            public String convert(Contacts contacts) {
                return new StringBuilder().append(contacts.toString()).toString();
            }
        };
    }
    
    public Converter<Long, Contacts> getIdToContactsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Contacts>() {
            public cm.adorsys.gpao.model.Contacts convert(java.lang.Long id) {
                return Contacts.findContacts(id);
            }
        };
    }
    
    public Converter<String, Contacts> getStringToContactsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Contacts>() {
            public cm.adorsys.gpao.model.Contacts convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Contacts.class);
            }
        };
    }
    
    public Converter<Delivery, String> getDeliveryToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Delivery, java.lang.String>() {
            public String convert(Delivery delivery) {
            	return new StringBuilder().append(delivery.toString()).toString();            }
        };
    }
    
    public Converter<Long, Delivery> getIdToDeliveryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Delivery>() {
            public cm.adorsys.gpao.model.Delivery convert(java.lang.Long id) {
                return Delivery.findDelivery(id);
            }
        };
    }
    
    public Converter<String, Delivery> getStringToDeliveryConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Delivery>() {
            public cm.adorsys.gpao.model.Delivery convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Delivery.class);
            }
        };
    }
    
    public Converter<DeliveryItems, String> getDeliveryItemsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.DeliveryItems, java.lang.String>() {
            public String convert(DeliveryItems deliveryItems) {
            	return new StringBuilder().append(deliveryItems.toString()).toString();            }
        };
    }
    
    public Converter<Long, DeliveryItems> getIdToDeliveryItemsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.DeliveryItems>() {
            public cm.adorsys.gpao.model.DeliveryItems convert(java.lang.Long id) {
                return DeliveryItems.findDeliveryItems(id);
            }
        };
    }
    
    public Converter<String, DeliveryItems> getStringToDeliveryItemsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.DeliveryItems>() {
            public cm.adorsys.gpao.model.DeliveryItems convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), DeliveryItems.class);
            }
        };
    }
    
    public Converter<Devise, String> getDeviseToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Devise, java.lang.String>() {
            public String convert(Devise devise) {
            	return new StringBuilder().append(devise.toString()).toString();            }
        };
    }
    
    public Converter<Long, Devise> getIdToDeviseConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Devise>() {
            public cm.adorsys.gpao.model.Devise convert(java.lang.Long id) {
                return Devise.findDevise(id);
            }
        };
    }
    
    public Converter<String, Devise> getStringToDeviseConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Devise>() {
            public cm.adorsys.gpao.model.Devise convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Devise.class);
            }
        };
    }
    
    public Converter<GpaoUser, String> getGpaoUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.GpaoUser, java.lang.String>() {
            public String convert(GpaoUser gpaoUser) {
            	return new StringBuilder().append(gpaoUser.toString()).toString();            }
        };
    }
    
    public Converter<Long, GpaoUser> getIdToGpaoUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.GpaoUser>() {
            public cm.adorsys.gpao.model.GpaoUser convert(java.lang.Long id) {
                return GpaoUser.findGpaoUser(id);
            }
        };
    }
    
    public Converter<String, GpaoUser> getStringToGpaoUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.GpaoUser>() {
            public cm.adorsys.gpao.model.GpaoUser convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GpaoUser.class);
            }
        };
    }
    
    public Converter<GpaoUserGroup, String> getGpaoUserGroupToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.GpaoUserGroup, java.lang.String>() {
            public String convert(GpaoUserGroup gpaoUserGroup) {
            	return new StringBuilder().append(gpaoUserGroup.toString()).toString();            }
        };
    }
    
    public Converter<Long, GpaoUserGroup> getIdToGpaoUserGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.GpaoUserGroup>() {
            public cm.adorsys.gpao.model.GpaoUserGroup convert(java.lang.Long id) {
                return GpaoUserGroup.findGpaoUserGroup(id);
            }
        };
    }
    
    public Converter<String, GpaoUserGroup> getStringToGpaoUserGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.GpaoUserGroup>() {
            public cm.adorsys.gpao.model.GpaoUserGroup convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GpaoUserGroup.class);
            }
        };
    }
    
    public Converter<OrderItems, String> getOrderItemsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.OrderItems, java.lang.String>() {
            public String convert(OrderItems orderItems) {
                return new StringBuilder().append(orderItems.getReference()).append(' ').append(orderItems.getQuantity()).append(' ').append(orderItems.getSubTotal()).append(' ').append(orderItems.getTaxedSubTotal()).toString();
            }
        };
    }
    
    public Converter<Long, OrderItems> getIdToOrderItemsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.OrderItems>() {
            public cm.adorsys.gpao.model.OrderItems convert(java.lang.Long id) {
                return OrderItems.findOrderItems(id);
            }
        };
    }
    
    public Converter<String, OrderItems> getStringToOrderItemsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.OrderItems>() {
            public cm.adorsys.gpao.model.OrderItems convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), OrderItems.class);
            }
        };
    }
    
    public Converter<Partner, String> getPartnerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Partner, java.lang.String>() {
            public String convert(Partner partner) {
            	return new StringBuilder().append(partner.toString()).toString();            }
        };
    }
    
    public Converter<Long, Partner> getIdToPartnerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Partner>() {
            public cm.adorsys.gpao.model.Partner convert(java.lang.Long id) {
                return Partner.findPartner(id);
            }
        };
    }
    
    public Converter<String, Partner> getStringToPartnerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Partner>() {
            public cm.adorsys.gpao.model.Partner convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Partner.class);
            }
        };
    }
    
    public Converter<PartnerGroup, String> getPartnerGroupToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.PartnerGroup, java.lang.String>() {
            public String convert(PartnerGroup partnerGroup) {
            	return new StringBuilder().append(partnerGroup.toString()).toString();            }
        };
    }
    
    public Converter<Long, PartnerGroup> getIdToPartnerGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.PartnerGroup>() {
            public cm.adorsys.gpao.model.PartnerGroup convert(java.lang.Long id) {
                return PartnerGroup.findPartnerGroup(id);
            }
        };
    }
    
    public Converter<String, PartnerGroup> getStringToPartnerGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.PartnerGroup>() {
            public cm.adorsys.gpao.model.PartnerGroup convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PartnerGroup.class);
            }
        };
    }
    
    public Converter<Product, String> getProductToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Product, java.lang.String>() {
            public String convert(Product product) {
            	return new StringBuilder().append(product.toString()).toString();            }
        };
    }
    
    public Converter<Long, Product> getIdToProductConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Product>() {
            public cm.adorsys.gpao.model.Product convert(java.lang.Long id) {
                return Product.findProduct(id);
            }
        };
    }
    
    public Converter<String, Product> getStringToProductConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Product>() {
            public cm.adorsys.gpao.model.Product convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Product.class);
            }
        };
    }
    
    public Converter<ProductFamily, String> getProductFamilyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.ProductFamily, java.lang.String>() {
            public String convert(ProductFamily productFamily) {
            	return new StringBuilder().append(productFamily.toString()).toString();            }
        };
    }
    
    public Converter<Long, ProductFamily> getIdToProductFamilyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.ProductFamily>() {
            public cm.adorsys.gpao.model.ProductFamily convert(java.lang.Long id) {
                return ProductFamily.findProductFamily(id);
            }
        };
    }
    
    public Converter<String, ProductFamily> getStringToProductFamilyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.ProductFamily>() {
            public cm.adorsys.gpao.model.ProductFamily convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), ProductFamily.class);
            }
        };
    }
    
    public Converter<ProductSubFamily, String> getProductSubFamilyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.ProductSubFamily, java.lang.String>() {
            public String convert(ProductSubFamily productSubFamily) {
            	return new StringBuilder().append(productSubFamily.toString()).toString();            }
        };
    }
    
    public Converter<Long, ProductSubFamily> getIdToProductSubFamilyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.ProductSubFamily>() {
            public cm.adorsys.gpao.model.ProductSubFamily convert(java.lang.Long id) {
                return ProductSubFamily.findProductSubFamily(id);
            }
        };
    }
    
    public Converter<String, ProductSubFamily> getStringToProductSubFamilyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.ProductSubFamily>() {
            public cm.adorsys.gpao.model.ProductSubFamily convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), ProductSubFamily.class);
            }
        };
    }
    
    public Converter<PurchaseOrder, String> getPurchaseOrderToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.PurchaseOrder, java.lang.String>() {
            public String convert(PurchaseOrder purchaseOrder) {
            	return new StringBuilder().append(purchaseOrder.toString()).toString();            }
        };
    }
    
    public Converter<Long, PurchaseOrder> getIdToPurchaseOrderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.PurchaseOrder>() {
            public cm.adorsys.gpao.model.PurchaseOrder convert(java.lang.Long id) {
                return PurchaseOrder.findPurchaseOrder(id);
            }
        };
    }
    
    public Converter<String, PurchaseOrder> getStringToPurchaseOrderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.PurchaseOrder>() {
            public cm.adorsys.gpao.model.PurchaseOrder convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PurchaseOrder.class);
            }
        };
    }
    
    public Converter<Taxe, String> getTaxeToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Taxe, java.lang.String>() {
            public String convert(Taxe taxe) {
            	return new StringBuilder().append(taxe.toString()).toString();            }
        };
    }
    
    public Converter<Long, Taxe> getIdToTaxeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Taxe>() {
            public cm.adorsys.gpao.model.Taxe convert(java.lang.Long id) {
                return Taxe.findTaxe(id);
            }
        };
    }
    
    public Converter<String, Taxe> getStringToTaxeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Taxe>() {
            public cm.adorsys.gpao.model.Taxe convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Taxe.class);
            }
        };
    }
    
    public Converter<UdmGroup, String> getUdmGroupToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.UdmGroup, java.lang.String>() {
            public String convert(UdmGroup udmGroup) {
            	return new StringBuilder().append(udmGroup.toString()).toString();            }
        };
    }
    
    public Converter<Long, UdmGroup> getIdToUdmGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.UdmGroup>() {
            public cm.adorsys.gpao.model.UdmGroup convert(java.lang.Long id) {
                return UdmGroup.findUdmGroup(id);
            }
        };
    }
    
    public Converter<String, UdmGroup> getStringToUdmGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.UdmGroup>() {
            public cm.adorsys.gpao.model.UdmGroup convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), UdmGroup.class);
            }
        };
    }
    
    public Converter<UnitOfMesures, String> getUnitOfMesuresToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.UnitOfMesures, java.lang.String>() {
            public String convert(UnitOfMesures unitOfMesures) {
            	return new StringBuilder().append(unitOfMesures.toString()).toString();            }
        };
    }
    
    public Converter<Long, UnitOfMesures> getIdToUnitOfMesuresConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.UnitOfMesures>() {
            public cm.adorsys.gpao.model.UnitOfMesures convert(java.lang.Long id) {
                return UnitOfMesures.findUnitOfMesures(id);
            }
        };
    }
    
    public Converter<String, UnitOfMesures> getStringToUnitOfMesuresConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.UnitOfMesures>() {
            public cm.adorsys.gpao.model.UnitOfMesures convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), UnitOfMesures.class);
            }
        };
    }
    
    public Converter<WareHouses, String> getWareHousesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.WareHouses, java.lang.String>() {
            public String convert(WareHouses wareHouses) {
            	return new StringBuilder().append(wareHouses.toString()).toString();            }
        };
    }
    
    public Converter<Long, WareHouses> getIdToWareHousesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.WareHouses>() {
            public cm.adorsys.gpao.model.WareHouses convert(java.lang.Long id) {
                return WareHouses.findWareHouses(id);
            }
        };
    }
    
    public Converter<String, WareHouses> getStringToWareHousesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.WareHouses>() {
            public cm.adorsys.gpao.model.WareHouses convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), WareHouses.class);
            }
        };
    }
    

}

