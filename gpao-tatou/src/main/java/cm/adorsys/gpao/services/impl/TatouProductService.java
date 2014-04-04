/**
 * 
 */
package cm.adorsys.gpao.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cm.adorsys.gpao.model.Product;
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

}
