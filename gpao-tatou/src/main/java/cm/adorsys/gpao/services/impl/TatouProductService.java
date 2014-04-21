/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.Caracteristic;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Specificity;
import cm.adorsys.gpao.model.SpecificityToCaracteristicMap;
import cm.adorsys.gpao.services.IProductService;

/**
 * @author bwa
 *
 */
@Service
public class TatouProductService implements IProductService{

	@Override
	public List<Product> findProductsByNameLike(String name, int max) {
		Assert.notNull(name, "the product name should not be null on find product by name like");
		if(max <0) {
			max = 100;
		}
		return Product.findProductsByNameLike(name).setMaxResults(max).getResultList();
	}

	@Override
	public Caracteristic getProductCaracteristic(Long productId) {
		Assert.notNull(productId, "The product id should not be null here");
		Product product = Product.findProduct(productId);
		List<Caracteristic> caracteristics = Caracteristic.findCaracteristicsByProduct(product).getResultList();
		if(caracteristics.isEmpty()) {
			return null;
		}
		return caracteristics.iterator().next();
	}

	@Override
	public List<Specificity> getProductSpecificitys(Long productId) {
		Assert.notNull(productId, "The product id should not be null here");
		Caracteristic productCaracteristic = getProductCaracteristic(productId);
		if(productCaracteristic == null ) {
			return new ArrayList<Specificity>();
		}
		return SpecificityToCaracteristicMap.findSpecificitysByCaracteristicsEquals(productCaracteristic).getResultList();
	}

	public boolean updateProductVirtualStock(Product product,BigDecimal rawMaterialOrderItemQuantity) {
		product.setVirtualStock(product.getVirtualStock().subtract(rawMaterialOrderItemQuantity));
		product.merge();
		return true;
	}
}
