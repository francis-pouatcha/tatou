package cm.adorsys.gpao.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cm.adorsys.gpao.model.Caracteristic;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Specificity;

public interface IProductService {
	public List<Product> findProductsByNameLike(String name,int max);
	public Caracteristic getProductCaracteristic(Long productId);
	public List<Specificity> getProductSpecificitys(Long productId);
	@Transactional
	public boolean updateProductVirtualStock(Product product,BigDecimal rawMaterialOrderItemQuantity);
}
