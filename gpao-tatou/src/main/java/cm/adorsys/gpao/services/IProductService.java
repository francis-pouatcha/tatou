package cm.adorsys.gpao.services;

import java.util.List;

import cm.adorsys.gpao.model.Product;

public interface IProductService {
	public List<Product> findProductsByNameLike(String name,int max);
}
