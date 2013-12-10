package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.DeliveryItems;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.Inventory;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.model.ProductType;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.UnitOfMesures;
import cm.adorsys.gpao.model.WareHouses;
import cm.adorsys.gpao.model.uimodels.ProductFinder;
import cm.adorsys.gpao.model.uimodels.PurchaseOrderFinder;
import cm.adorsys.gpao.services.Impl.TatouInventoryService;
import cm.adorsys.gpao.utils.GpaoDocumentDirectories;
import cm.adorsys.gpao.utils.GpaoFileUtils;
import cm.adorsys.gpao.utils.GpaoPdfProducer;
import cm.adorsys.gpao.utils.GpaoRepportPath;
import cm.adorsys.gpao.utils.MessageType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
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
@RooWebFinder
public class ProductController {

	@Autowired
	TatouInventoryService inventoryService;
	@Autowired
	GpaoPdfProducer pdfProducer;


	@RequestMapping(value = "/list/{module}", method = RequestMethod.GET, produces = "text/html")
	public String lister(@PathVariable("module") String module, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
		if (page != null || size != null) {
			int sizeNo = size == null ? 10 : size.intValue();
			final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
			uiModel.addAttribute("products", Product.findProductEntries(firstResult, sizeNo));
			float nrOfPages = (float) Product.countProducts() / sizeNo;
			uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
		} else {
			uiModel.addAttribute("products", Product.findAllProducts());
		}
		uiModel.addAttribute("module", module);
		return "products/" + module + "/list";
	}

	@RequestMapping(value = "/addOrEditForm/{module}", method = RequestMethod.GET, produces = "text/html")
	public String addOrEditProductsForm(@PathVariable("module") String module, @RequestParam(value = "id", required = false) Long id, Model uiModel) {
		Product product = id == null ? new Product() : Product.findProduct(id);
		populateEditForm(uiModel, product);
		uiModel.addAttribute("module", module);
		return "products/" + module + "/productView";
	}

	@RequestMapping(value = "/addOrEdit/{module}", method = RequestMethod.POST, produces = "text/html")
	public String addOrEditProducts(@PathVariable("module") String module, @Valid Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		uiModel.addAttribute("module", module);
		if (bindingResult.hasErrors()) {
			populateEditForm(uiModel, product);
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
			return "products/" + module + "/productView";
		}
		MultipartFile uploadegLogo = product.getProductImage();
		if (uploadegLogo != null) {
			if (!uploadegLogo.isEmpty()) {
				String saveFileName = GpaoFileUtils.saveFile(GpaoDocumentDirectories.PRODUCT_IMG_PATH, uploadegLogo, "img_" + product.getName());
				if (saveFileName != null) product.setProductImagePath(saveFileName);
			}
		}
		boolean initialEntry = product.isInitialEntry();
		Product merge =  product.merge();
		if (initialEntry) {
			Inventory inventoryFormProduct = inventoryService.buildInitialInventoryFromProduct(merge);
			if (inventoryFormProduct != null) inventoryService.closeInventory(inventoryFormProduct);
		}
		merge.calculateStockQuantity();
		populateEditForm(uiModel, merge.merge());
		uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
		return "products/" + module + "/productView";
	}

	@RequestMapping(value = "/next/{module}/{id}", method = RequestMethod.GET, produces = "text/html")
	public String getNextProduct(@PathVariable("module") String module, @PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("module", module);
		List<Product> nextProduct = Product.findProductsByIdUpperThan(id).setMaxResults(1).getResultList();
		if (nextProduct.isEmpty()) {
			populateEditForm(uiModel, Product.findProduct(id));
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun Produit trouve !");
			return "products/" + module + "/productView";
		}
		Product next = nextProduct.iterator().next();
		populateEditForm(uiModel, next);
		return "products/" + module + "/productView";
	}

	@RequestMapping(value = "/previous/{module}/{id}", method = RequestMethod.GET, produces = "text/html")
	public String getPreviousProduct(@PathVariable("module") String module, @PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("module", module);
		List<Product> nextProduct = Product.findProductsByIdLowerThan(id).setMaxResults(1).getResultList();
		if (nextProduct.isEmpty()) {
			populateEditForm(uiModel, Product.findProduct(id));
			uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucun Produit trouve !");
			return "products/" + module + "/productView";
		}
		Product next = nextProduct.iterator().next();
		populateEditForm(uiModel, next);
		return "products/" + module + "/productView";
	}

	@RequestMapping(value="/find/{module}", params = {"form" }, method = RequestMethod.GET)
	public String findProductForm(@PathVariable("module") String module,Model uiModel) {
		populateFindForm(uiModel, new ProductFinder());
		return "products/" + module + "/findProductView";
	}

	@RequestMapping(value="/find/{module}", method = RequestMethod.POST)
	public String findProduct(@PathVariable("module") String module,@Valid ProductFinder productFinder, Model uiModel) {
		uiModel.addAttribute("products", productFinder.find());
		//populateFindForm(uiModel, productFinder);
		uiModel.addAttribute("module", module);
		return "products/" + module + "/list";
	}

	@RequestMapping(value = "/printFind.pdf", method = RequestMethod.POST, produces = { "application/pdf" })
	public void printFindProducts(@Valid ProductFinder productFinder, HttpServletResponse response) {
		Map parameters = new HashMap();
		parameters.put("entityId", productFinder.getProductId());
		try {
			pdfProducer.buildPdfDocument(parameters, response, GpaoRepportPath.PRODUCT_LIST_JRXML_PATH);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	@RequestMapping(value = "/printLot/{prdId}.pdf", method = RequestMethod.GET, produces = { "application/pdf" })
	public void printLotProducts(@PathVariable("prdId") Long prdId, HttpServletResponse response) {
		Map parameters = new HashMap();
		parameters.put("entityId", Product.findProduct(prdId).getId());
		try {
			pdfProducer.buildPdfDocument(parameters, response, GpaoRepportPath.PRODUCT_LOT_JRXML_PATH);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
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
	void populateFindForm(Model uiModel, ProductFinder productFinder) {
		uiModel.addAttribute("productFinder", productFinder);
		List<ProductSubFamily> productSubFamilys = ProductSubFamily.findAllProductSubFamilys();
		productSubFamilys.add(0, new ProductSubFamily());
		uiModel.addAttribute("productsubfamilys", productSubFamilys);
		List<WareHouses> wareHouseses = WareHouses.findAllWareHouseses();
		wareHouseses.add(0, new WareHouses());
		uiModel.addAttribute("warehouseses", wareHouseses);
	}
}
