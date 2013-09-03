// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Contacts;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.GpaoUser;
import cm.adorsys.gpao.model.GpaoUserGroup;
import cm.adorsys.gpao.model.Location;
import cm.adorsys.gpao.model.OrderItems;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductFamily;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.WareHouses;
import cm.adorsys.gpao.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<Company, String> ApplicationConversionServiceFactoryBean.getCompanyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Company, java.lang.String>() {
            public String convert(Company company) {
                return new StringBuilder().append(company.getName()).append(' ').append(company.getContactName()).append(' ').append(company.getContactFunction()).append(' ').append(company.getTaxePayerNumber()).toString();
            }
        };
    }
    
    public Converter<Long, Company> ApplicationConversionServiceFactoryBean.getIdToCompanyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Company>() {
            public cm.adorsys.gpao.model.Company convert(java.lang.Long id) {
                return Company.findCompany(id);
            }
        };
    }
    
    public Converter<String, Company> ApplicationConversionServiceFactoryBean.getStringToCompanyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Company>() {
            public cm.adorsys.gpao.model.Company convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Company.class);
            }
        };
    }
    
    public Converter<Contacts, String> ApplicationConversionServiceFactoryBean.getContactsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Contacts, java.lang.String>() {
            public String convert(Contacts contacts) {
                return new StringBuilder().append(contacts.getName()).append(' ').append(contacts.getPhone()).append(' ').append(contacts.getEmail()).append(' ').append(contacts.getContactFunction()).toString();
            }
        };
    }
    
    public Converter<Long, Contacts> ApplicationConversionServiceFactoryBean.getIdToContactsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Contacts>() {
            public cm.adorsys.gpao.model.Contacts convert(java.lang.Long id) {
                return Contacts.findContacts(id);
            }
        };
    }
    
    public Converter<String, Contacts> ApplicationConversionServiceFactoryBean.getStringToContactsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Contacts>() {
            public cm.adorsys.gpao.model.Contacts convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Contacts.class);
            }
        };
    }
    
    public Converter<Devise, String> ApplicationConversionServiceFactoryBean.getDeviseToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Devise, java.lang.String>() {
            public String convert(Devise devise) {
                return new StringBuilder().append(devise.getName()).append(' ').append(devise.getShortName()).append(' ').append(devise.getRatio()).toString();
            }
        };
    }
    
    public Converter<Long, Devise> ApplicationConversionServiceFactoryBean.getIdToDeviseConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Devise>() {
            public cm.adorsys.gpao.model.Devise convert(java.lang.Long id) {
                return Devise.findDevise(id);
            }
        };
    }
    
    public Converter<String, Devise> ApplicationConversionServiceFactoryBean.getStringToDeviseConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Devise>() {
            public cm.adorsys.gpao.model.Devise convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Devise.class);
            }
        };
    }
    
    public Converter<GpaoUser, String> ApplicationConversionServiceFactoryBean.getGpaoUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.GpaoUser, java.lang.String>() {
            public String convert(GpaoUser gpaoUser) {
                return new StringBuilder().append(gpaoUser.getUserNumber()).append(' ').append(gpaoUser.getUserName()).append(' ').append(gpaoUser.getFirstName()).append(' ').append(gpaoUser.getLastName()).toString();
            }
        };
    }
    
    public Converter<Long, GpaoUser> ApplicationConversionServiceFactoryBean.getIdToGpaoUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.GpaoUser>() {
            public cm.adorsys.gpao.model.GpaoUser convert(java.lang.Long id) {
                return GpaoUser.findGpaoUser(id);
            }
        };
    }
    
    public Converter<String, GpaoUser> ApplicationConversionServiceFactoryBean.getStringToGpaoUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.GpaoUser>() {
            public cm.adorsys.gpao.model.GpaoUser convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GpaoUser.class);
            }
        };
    }
    
    public Converter<GpaoUserGroup, String> ApplicationConversionServiceFactoryBean.getGpaoUserGroupToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.GpaoUserGroup, java.lang.String>() {
            public String convert(GpaoUserGroup gpaoUserGroup) {
                return new StringBuilder().append(gpaoUserGroup.getName()).append(' ').append(gpaoUserGroup.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, GpaoUserGroup> ApplicationConversionServiceFactoryBean.getIdToGpaoUserGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.GpaoUserGroup>() {
            public cm.adorsys.gpao.model.GpaoUserGroup convert(java.lang.Long id) {
                return GpaoUserGroup.findGpaoUserGroup(id);
            }
        };
    }
    
    public Converter<String, GpaoUserGroup> ApplicationConversionServiceFactoryBean.getStringToGpaoUserGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.GpaoUserGroup>() {
            public cm.adorsys.gpao.model.GpaoUserGroup convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GpaoUserGroup.class);
            }
        };
    }
    
    public Converter<Location, String> ApplicationConversionServiceFactoryBean.getLocationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Location, java.lang.String>() {
            public String convert(Location location) {
                return new StringBuilder().append(location.getName()).append(' ').append(location.getHallWay()).append(' ').append(location.getLocationRow()).append(' ').append(location.getLocationHeigth()).toString();
            }
        };
    }
    
    public Converter<Long, Location> ApplicationConversionServiceFactoryBean.getIdToLocationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Location>() {
            public cm.adorsys.gpao.model.Location convert(java.lang.Long id) {
                return Location.findLocation(id);
            }
        };
    }
    
    public Converter<String, Location> ApplicationConversionServiceFactoryBean.getStringToLocationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Location>() {
            public cm.adorsys.gpao.model.Location convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Location.class);
            }
        };
    }
    
    public Converter<OrderItems, String> ApplicationConversionServiceFactoryBean.getOrderItemsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.OrderItems, java.lang.String>() {
            public String convert(OrderItems orderItems) {
                return new StringBuilder().append(orderItems.getReference()).append(' ').append(orderItems.getQuantity()).append(' ').append(orderItems.getSubTotal()).append(' ').append(orderItems.getTaxeAmount()).toString();
            }
        };
    }
    
    public Converter<Long, OrderItems> ApplicationConversionServiceFactoryBean.getIdToOrderItemsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.OrderItems>() {
            public cm.adorsys.gpao.model.OrderItems convert(java.lang.Long id) {
                return OrderItems.findOrderItems(id);
            }
        };
    }
    
    public Converter<String, OrderItems> ApplicationConversionServiceFactoryBean.getStringToOrderItemsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.OrderItems>() {
            public cm.adorsys.gpao.model.OrderItems convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), OrderItems.class);
            }
        };
    }
    
    public Converter<Partner, String> ApplicationConversionServiceFactoryBean.getPartnerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Partner, java.lang.String>() {
            public String convert(Partner partner) {
                return new StringBuilder().append(partner.getName()).append(' ').append(partner.getContactName()).append(' ').append(partner.getContactFunction()).append(' ').append(partner.getPhone()).toString();
            }
        };
    }
    
    public Converter<Long, Partner> ApplicationConversionServiceFactoryBean.getIdToPartnerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Partner>() {
            public cm.adorsys.gpao.model.Partner convert(java.lang.Long id) {
                return Partner.findPartner(id);
            }
        };
    }
    
    public Converter<String, Partner> ApplicationConversionServiceFactoryBean.getStringToPartnerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Partner>() {
            public cm.adorsys.gpao.model.Partner convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Partner.class);
            }
        };
    }
    
    public Converter<PartnerGroup, String> ApplicationConversionServiceFactoryBean.getPartnerGroupToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.PartnerGroup, java.lang.String>() {
            public String convert(PartnerGroup partnerGroup) {
                return new StringBuilder().append(partnerGroup.getName()).append(' ').append(partnerGroup.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, PartnerGroup> ApplicationConversionServiceFactoryBean.getIdToPartnerGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.PartnerGroup>() {
            public cm.adorsys.gpao.model.PartnerGroup convert(java.lang.Long id) {
                return PartnerGroup.findPartnerGroup(id);
            }
        };
    }
    
    public Converter<String, PartnerGroup> ApplicationConversionServiceFactoryBean.getStringToPartnerGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.PartnerGroup>() {
            public cm.adorsys.gpao.model.PartnerGroup convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PartnerGroup.class);
            }
        };
    }
    
    public Converter<Product, String> ApplicationConversionServiceFactoryBean.getProductToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Product, java.lang.String>() {
            public String convert(Product product) {
                return new StringBuilder().append(product.getReference()).append(' ').append(product.getName()).append(' ').append(product.getVirtualStock()).append(' ').append(product.getMinStock()).toString();
            }
        };
    }
    
    public Converter<Long, Product> ApplicationConversionServiceFactoryBean.getIdToProductConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Product>() {
            public cm.adorsys.gpao.model.Product convert(java.lang.Long id) {
                return Product.findProduct(id);
            }
        };
    }
    
    public Converter<String, Product> ApplicationConversionServiceFactoryBean.getStringToProductConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Product>() {
            public cm.adorsys.gpao.model.Product convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Product.class);
            }
        };
    }
    
    public Converter<ProductFamily, String> ApplicationConversionServiceFactoryBean.getProductFamilyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.ProductFamily, java.lang.String>() {
            public String convert(ProductFamily productFamily) {
                return new StringBuilder().append(productFamily.getName()).append(' ').append(productFamily.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, ProductFamily> ApplicationConversionServiceFactoryBean.getIdToProductFamilyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.ProductFamily>() {
            public cm.adorsys.gpao.model.ProductFamily convert(java.lang.Long id) {
                return ProductFamily.findProductFamily(id);
            }
        };
    }
    
    public Converter<String, ProductFamily> ApplicationConversionServiceFactoryBean.getStringToProductFamilyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.ProductFamily>() {
            public cm.adorsys.gpao.model.ProductFamily convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), ProductFamily.class);
            }
        };
    }
    
    public Converter<ProductSubFamily, String> ApplicationConversionServiceFactoryBean.getProductSubFamilyToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.ProductSubFamily, java.lang.String>() {
            public String convert(ProductSubFamily productSubFamily) {
                return new StringBuilder().append(productSubFamily.getName()).append(' ').append(productSubFamily.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, ProductSubFamily> ApplicationConversionServiceFactoryBean.getIdToProductSubFamilyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.ProductSubFamily>() {
            public cm.adorsys.gpao.model.ProductSubFamily convert(java.lang.Long id) {
                return ProductSubFamily.findProductSubFamily(id);
            }
        };
    }
    
    public Converter<String, ProductSubFamily> ApplicationConversionServiceFactoryBean.getStringToProductSubFamilyConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.ProductSubFamily>() {
            public cm.adorsys.gpao.model.ProductSubFamily convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), ProductSubFamily.class);
            }
        };
    }
    
    public Converter<PurchaseOrder, String> ApplicationConversionServiceFactoryBean.getPurchaseOrderToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.PurchaseOrder, java.lang.String>() {
            public String convert(PurchaseOrder purchaseOrder) {
                return new StringBuilder().append(purchaseOrder.getReference()).append(' ').append(purchaseOrder.getOrderDate()).append(' ').append(purchaseOrder.getValidatedBy()).append(' ').append(purchaseOrder.getValidateDate()).toString();
            }
        };
    }
    
    public Converter<Long, PurchaseOrder> ApplicationConversionServiceFactoryBean.getIdToPurchaseOrderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.PurchaseOrder>() {
            public cm.adorsys.gpao.model.PurchaseOrder convert(java.lang.Long id) {
                return PurchaseOrder.findPurchaseOrder(id);
            }
        };
    }
    
    public Converter<String, PurchaseOrder> ApplicationConversionServiceFactoryBean.getStringToPurchaseOrderConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.PurchaseOrder>() {
            public cm.adorsys.gpao.model.PurchaseOrder convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PurchaseOrder.class);
            }
        };
    }
    
    public Converter<Taxe, String> ApplicationConversionServiceFactoryBean.getTaxeToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.Taxe, java.lang.String>() {
            public String convert(Taxe taxe) {
                return new StringBuilder().append(taxe.getName()).append(' ').append(taxe.getShortName()).append(' ').append(taxe.getTaxeValue()).toString();
            }
        };
    }
    
    public Converter<Long, Taxe> ApplicationConversionServiceFactoryBean.getIdToTaxeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.Taxe>() {
            public cm.adorsys.gpao.model.Taxe convert(java.lang.Long id) {
                return Taxe.findTaxe(id);
            }
        };
    }
    
    public Converter<String, Taxe> ApplicationConversionServiceFactoryBean.getStringToTaxeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.Taxe>() {
            public cm.adorsys.gpao.model.Taxe convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Taxe.class);
            }
        };
    }
    
    public Converter<UdmGroup, String> ApplicationConversionServiceFactoryBean.getUdmGroupToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.UdmGroup, java.lang.String>() {
            public String convert(UdmGroup udmGroup) {
                return new StringBuilder().append(udmGroup.getName()).append(' ').append(udmGroup.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, UdmGroup> ApplicationConversionServiceFactoryBean.getIdToUdmGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.UdmGroup>() {
            public cm.adorsys.gpao.model.UdmGroup convert(java.lang.Long id) {
                return UdmGroup.findUdmGroup(id);
            }
        };
    }
    
    public Converter<String, UdmGroup> ApplicationConversionServiceFactoryBean.getStringToUdmGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.UdmGroup>() {
            public cm.adorsys.gpao.model.UdmGroup convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), UdmGroup.class);
            }
        };
    }
    
    public Converter<UnitOfMesures, String> ApplicationConversionServiceFactoryBean.getUnitOfMesuresToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.UnitOfMesures, java.lang.String>() {
            public String convert(UnitOfMesures unitOfMesures) {
                return new StringBuilder().append(unitOfMesures.getName()).append(' ').append(unitOfMesures.getRatio()).toString();
            }
        };
    }
    
    public Converter<Long, UnitOfMesures> ApplicationConversionServiceFactoryBean.getIdToUnitOfMesuresConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.UnitOfMesures>() {
            public cm.adorsys.gpao.model.UnitOfMesures convert(java.lang.Long id) {
                return UnitOfMesures.findUnitOfMesures(id);
            }
        };
    }
    
    public Converter<String, UnitOfMesures> ApplicationConversionServiceFactoryBean.getStringToUnitOfMesuresConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.UnitOfMesures>() {
            public cm.adorsys.gpao.model.UnitOfMesures convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), UnitOfMesures.class);
            }
        };
    }
    
    public Converter<WareHouses, String> ApplicationConversionServiceFactoryBean.getWareHousesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<cm.adorsys.gpao.model.WareHouses, java.lang.String>() {
            public String convert(WareHouses wareHouses) {
                return new StringBuilder().append(wareHouses.getName()).append(' ').append(wareHouses.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, WareHouses> ApplicationConversionServiceFactoryBean.getIdToWareHousesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, cm.adorsys.gpao.model.WareHouses>() {
            public cm.adorsys.gpao.model.WareHouses convert(java.lang.Long id) {
                return WareHouses.findWareHouses(id);
            }
        };
    }
    
    public Converter<String, WareHouses> ApplicationConversionServiceFactoryBean.getStringToWareHousesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, cm.adorsys.gpao.model.WareHouses>() {
            public cm.adorsys.gpao.model.WareHouses convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), WareHouses.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getCompanyToStringConverter());
        registry.addConverter(getIdToCompanyConverter());
        registry.addConverter(getStringToCompanyConverter());
        registry.addConverter(getContactsToStringConverter());
        registry.addConverter(getIdToContactsConverter());
        registry.addConverter(getStringToContactsConverter());
        registry.addConverter(getDeviseToStringConverter());
        registry.addConverter(getIdToDeviseConverter());
        registry.addConverter(getStringToDeviseConverter());
        registry.addConverter(getGpaoUserToStringConverter());
        registry.addConverter(getIdToGpaoUserConverter());
        registry.addConverter(getStringToGpaoUserConverter());
        registry.addConverter(getGpaoUserGroupToStringConverter());
        registry.addConverter(getIdToGpaoUserGroupConverter());
        registry.addConverter(getStringToGpaoUserGroupConverter());
        registry.addConverter(getLocationToStringConverter());
        registry.addConverter(getIdToLocationConverter());
        registry.addConverter(getStringToLocationConverter());
        registry.addConverter(getOrderItemsToStringConverter());
        registry.addConverter(getIdToOrderItemsConverter());
        registry.addConverter(getStringToOrderItemsConverter());
        registry.addConverter(getPartnerToStringConverter());
        registry.addConverter(getIdToPartnerConverter());
        registry.addConverter(getStringToPartnerConverter());
        registry.addConverter(getPartnerGroupToStringConverter());
        registry.addConverter(getIdToPartnerGroupConverter());
        registry.addConverter(getStringToPartnerGroupConverter());
        registry.addConverter(getProductToStringConverter());
        registry.addConverter(getIdToProductConverter());
        registry.addConverter(getStringToProductConverter());
        registry.addConverter(getProductFamilyToStringConverter());
        registry.addConverter(getIdToProductFamilyConverter());
        registry.addConverter(getStringToProductFamilyConverter());
        registry.addConverter(getProductSubFamilyToStringConverter());
        registry.addConverter(getIdToProductSubFamilyConverter());
        registry.addConverter(getStringToProductSubFamilyConverter());
        registry.addConverter(getPurchaseOrderToStringConverter());
        registry.addConverter(getIdToPurchaseOrderConverter());
        registry.addConverter(getStringToPurchaseOrderConverter());
        registry.addConverter(getTaxeToStringConverter());
        registry.addConverter(getIdToTaxeConverter());
        registry.addConverter(getStringToTaxeConverter());
        registry.addConverter(getUdmGroupToStringConverter());
        registry.addConverter(getIdToUdmGroupConverter());
        registry.addConverter(getStringToUdmGroupConverter());
        registry.addConverter(getUnitOfMesuresToStringConverter());
        registry.addConverter(getIdToUnitOfMesuresConverter());
        registry.addConverter(getStringToUnitOfMesuresConverter());
        registry.addConverter(getWareHousesToStringConverter());
        registry.addConverter(getIdToWareHousesConverter());
        registry.addConverter(getStringToWareHousesConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
