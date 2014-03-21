package cm.adorsys.gpao.model.uimodels;
import java.util.ArrayList;
import java.util.List;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.model.PurchaseOrder;
import cm.adorsys.gpao.model.WareHouses;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;

@RooJavaBean
@RooJson
public class ProductFinder implements GpaoEntityFinder<Product> {

    private String productName;

    private WareHouses wareHouse;

    private ProductSubFamily familly;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public WareHouses getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(WareHouses wareHouse) {
        this.wareHouse = wareHouse;
    }

    public ProductSubFamily getFamilly() {
        return familly;
    }

    public void setFamilly(ProductSubFamily familly) {
        this.familly = familly;
    }

    @Override
    public List<Product> find(int page, int size) {
        return Product.findProductsByNameLikeAndWareHouseAndProductSubFamilly(productName, wareHouse, familly).setFirstResult(page).setMaxResults(size).getResultList();
    }

    @Override
    public List<Product> find() {
        return Product.findProductsByNameLikeAndWareHouseAndProductSubFamilly(productName, wareHouse, familly).getResultList();
    }

    public List<Long> getProductId() {
        ArrayList<Long> tenderId = new ArrayList<Long>();
        List<Product> find = find();
        for (Product product : find) {
            tenderId.add(product.getId());
        }
        return tenderId;
    }
}
