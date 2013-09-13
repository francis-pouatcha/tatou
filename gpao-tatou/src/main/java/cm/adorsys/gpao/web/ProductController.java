package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.model.ProductType;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.WareHouses;
import cm.adorsys.gpao.services.Impl.TatouInventoryService;
import cm.adorsys.gpao.utils.GpaoDocumentDirectories;
import cm.adorsys.gpao.utils.GpaoFileUtils;
import cm.adorsys.gpao.utils.MessageType;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/products")
@Controller
@RooWebScaffold(path = "products", formBackingObject = Product.class)
public class ProductController {

    @Autowired
    TatouInventoryService inventoryService;

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET, produces = "text/html")
    public String addOrEditProductsForm(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        Product product = id == null ? new Product() : Product.findProduct(id);
        populateEditForm(uiModel, product);
        return "products/productView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST, produces = "text/html")
    public String addOrEditProducts(@Valid Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, product);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "products/productView";
        }
        MultipartFile uploadegLogo = product.getProductImage();
        if (uploadegLogo != null) {
            if (!uploadegLogo.isEmpty()) {
                String saveFileName = GpaoFileUtils.saveFile(GpaoDocumentDirectories.PRODUCT_IMG_PATH, uploadegLogo, "img_" + product.getName());
                if (saveFileName != null) product.setProductImagePath(saveFileName);
            }
        }
        boolean initialEntry = product.isInitialEntry();
        System.out.println("virtual stock : " + product.getVirtualStock());
        Product merge = product.merge();
        System.out.println("virtual stock after merge : " + merge.getVirtualStock());
        if (initialEntry) {
            Inventory inventoryFormProduct = inventoryService.buildInitialInventoryFormProduct(merge);
            if (inventoryFormProduct != null) inventoryService.closeInventory(inventoryFormProduct);
        }
        populateEditForm(uiModel, merge);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "products/productView";
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextProduct(@PathVariable("id") Long id, Model uiModel) {
        List<Product> nextProduct = Product.findProductsByIdUpperThan(id).setMaxResults(1).getResultList();
        if (nextProduct.isEmpty()) {
            populateEditForm(uiModel, Product.findProduct(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun Produit trouve !");
            return "products/productView";
        }
        Product next = nextProduct.iterator().next();
        populateEditForm(uiModel, next);
        return "products/productView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getPreviousProduct(@PathVariable("id") Long id, Model uiModel) {
        List<Product> nextProduct = Product.findProductsByIdLowerThan(id).setMaxResults(1).getResultList();
        if (nextProduct.isEmpty()) {
            populateEditForm(uiModel, Product.findProduct(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun Produit trouve !");
            return "products/productView";
        }
        Product next = nextProduct.iterator().next();
        populateEditForm(uiModel, next);
        return "products/productView";
    }

    void populateEditForm(Model uiModel, Product product) {
        uiModel.addAttribute("product", product);
        uiModel.addAttribute("devises", Devise.findAllDevises());
        uiModel.addAttribute("productsubfamilys", ProductSubFamily.findAllProductSubFamilys());
        uiModel.addAttribute("producttypes", Arrays.asList(ProductType.values()));
        uiModel.addAttribute("taxes", Taxe.findActivedTaxe().getResultList());
        uiModel.addAttribute("unitofmesureses", UnitOfMesures.findAllUnitOfMesureses());
        uiModel.addAttribute("warehouseses", WareHouses.findAllWareHouseses());
    }
}
