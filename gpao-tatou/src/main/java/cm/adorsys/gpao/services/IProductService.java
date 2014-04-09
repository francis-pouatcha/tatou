package cm.adorsys.gpao.services;

import java.util.List;

import cm.adorsys.gpao.model.Caracteristic;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Specificity;

public interface IProductService {
	public List<Product> findProductsByNameLike(String name,int max);
	public Caracteristic getProductCaracteristic(Long productId);
	public List<Specificity> getProductSpecificitys(Long productId);
}
