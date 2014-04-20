package cm.adorsys.gpao.web;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.CustomerOrder;
import cm.adorsys.gpao.model.CustomerOrderItem;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.DocumentStates;
import cm.adorsys.gpao.model.Partner;
import cm.adorsys.gpao.model.Product;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.uimodels.OrderItemUimodel;
import cm.adorsys.gpao.services.BusinessOperation;
import cm.adorsys.gpao.services.ICustomerOrderService;
import cm.adorsys.gpao.services.IManufacturingVoucherService;
import cm.adorsys.gpao.services.IRawMaterialOrderService;
import cm.adorsys.gpao.services.impl.function.ProcessCustomerOrder;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/customerorders")
@Controller
@RooWebScaffold(path = "customerorders", formBackingObject = CustomerOrder.class)
public class CustomerOrderController extends AbstractOrderController {

    @Autowired
    ICustomerOrderService customerOrderService;

    @Autowired
    IManufacturingVoucherService productionService;

    @Autowired
    IRawMaterialOrderService rawMaterialOrderService;

    @RequestMapping(value = "/addOrEditForm", method = RequestMethod.GET)
    public String addOrEditCustomerOrdersForm(@RequestParam(value = "id", required = false) Long id, HttpServletRequest httpServletRequest, Model uiModel) {
        CustomerOrder customerOrder = id == null ? new CustomerOrder() : CustomerOrder.findCustomerOrder(id);
        populateEditForm(uiModel, customerOrder);
        return "customerorders/customerordersView";
    }

    @RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
    public String addOrEditCustomerOrders(@Valid CustomerOrder customerOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, customerOrder);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "une erreur est Survenue durant l'enregistrement ! \n " + bindingResult.getFieldErrors());
            return "customerorders/customerordersView";
        }
        if (customerOrder.getId() == null) {
            customerOrder.setOrderState(DocumentStates.BROUILLON);
            customerOrder.persist();
        }
        customerOrderService.computeAndSetAmounts(customerOrder, CustomerOrderItem.findCustomerOrderItemsByCustomerOrder(customerOrder).getResultList());
        //TODO if customer order is validated
        //TODO document state has changed
        customerOrder = doAConsistantMerge(customerOrder);
        populateEditForm(uiModel, customerOrder);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Enregistre avec success !");
        return "customerorders/customerordersView";
    }

    private CustomerOrder doAConsistantMerge(CustomerOrder customerOrder) {
        try {
            customerOrder.merge();
        } catch (Exception e) {
            customerOrder.setVersion(CustomerOrder.findCustomerOrder(customerOrder.getId()).getVersion());
            customerOrder = customerOrder.merge();
        }
        return customerOrder;
    }

    @RequestMapping(value = "/findProductByNameLike/{name}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String findProductByNameLike(@PathVariable("name") String name) {
        List<Product> productList = customerOrderService.findProductByNameLike(name, 100);
        return Product.toJsonArray(productList);
    }

    @RequestMapping(value = "/getSelectedProduct/{productId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getSelectedProduct(@PathVariable("productId") Long productId) {
        OrderItemUimodel selectedProduct = customerOrderService.findSelectedProduct(productId);
        return selectedProduct.toJson();
    }

    @RequestMapping(value = "/{customerOrderId}/addOrderItem", method = RequestMethod.GET, params = { "productId" })
    @ResponseBody
    public String addOrderItem(@PathVariable("customerOrderId") Long customerOrderId, OrderItemUimodel itemUimodel, @RequestParam("productId") Long productId, Model uiModel) {
        CustomerOrder customerOrder = CustomerOrder.findCustomerOrder(customerOrderId);
        if (!customerOrderService.isBusinessOperationAllowed(customerOrder, BusinessOperation.VALIDATE)) {
            throw new RuntimeException("This operation is not allowed");
        }
        itemUimodel.setProduct(Product.findProduct(productId));
        customerOrderService.addCustomerOrderItems(customerOrder, itemUimodel);
        customerOrderService.computeAndSetAmounts(customerOrder, customerOrderService.findCustomerOrderItems(customerOrder)).merge();
        return CustomerOrderItem.toJsonArray(customerOrderService.findCustomerOrderItems(customerOrder));
    }

    @Transactional(rollbackFor = Throwable.class)
    @RequestMapping(value = "/{customerOrderId}/validatedOrder", method = RequestMethod.GET)
    public String validatedOrder(@PathVariable("customerOrderId") Long customerOrderId, Model uiModel) {
        CustomerOrder customerOrder = CustomerOrder.findCustomerOrder(customerOrderId);
        if (!customerOrderService.isBusinessOperationAllowed(customerOrder, BusinessOperation.VALIDATE)) {
            populateEditForm(uiModel, customerOrder);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Nous somme desole, il est impossible de valider cette commande! \n ");
            return "purchaseorders/purchaseordersView";
        }
        List<CustomerOrderItem> customerOrderItems = customerOrderService.findCustomerOrderItems(customerOrder);
        if (customerOrderItems.isEmpty()) {
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "validation Impossible la commande est vide !");
        } else {
            customerOrder = customerOrderService.computeAndSetAmounts(customerOrder, customerOrderItems);
            customerOrder = customerOrderService.validateCustomerOrder(customerOrder);
            //            productionService.processCustomerOrder(customerOrder, new ProcessCustomerOrder(rawMaterialOrderService));
            customerOrder = doAConsistantMerge(customerOrder);
            uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "validation effectuee avec success !");
        }
        populateEditForm(uiModel, customerOrder);
        return "customerorders/customerordersView";
    }

    @Transactional(rollbackFor = Throwable.class)
    @RequestMapping(value = "/{customerOrderId}/validatedOrderAndGenerate", method = RequestMethod.GET)
    public String validatedOrderAndGenerateManufacturingOrder(@PathVariable("customerOrderId") Long customerOrderId, Model uiModel) {
        CustomerOrder customerOrder = CustomerOrder.findCustomerOrder(customerOrderId);
        if (!customerOrderService.isBusinessOperationAllowed(customerOrder, BusinessOperation.VALIDATE)) {
            populateEditForm(uiModel, customerOrder);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Nous somme desole, il est impossible de valider cette commande! \n ");
            return "purchaseorders/purchaseordersView";
        }
        List<CustomerOrderItem> customerOrderItems = customerOrderService.findCustomerOrderItems(customerOrder);
        if (customerOrderItems.isEmpty()) {
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "validation Impossible la commande est vide !");
        } else {
            customerOrder = customerOrderService.computeAndSetAmounts(customerOrder, customerOrderItems);
            customerOrder = customerOrderService.validateCustomerOrder(customerOrder);
            productionService.processCustomerOrder(customerOrder, new ProcessCustomerOrder(rawMaterialOrderService)); //here is where we generate manufacturing order.
            customerOrder = doAConsistantMerge(customerOrder);
            uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "validation effectuee avec success !");
        }
        populateEditForm(uiModel, customerOrder);
        return "customerorders/customerordersView";
    }

    @RequestMapping(value = "/{customerOrderId}/caculated", method = RequestMethod.GET)
    public String calculatedTaxAndAmount(@PathVariable("customerOrderId") Long customerOrderId, Model uiModel) {
        CustomerOrder customerOrder = CustomerOrder.findCustomerOrder(customerOrderId);
        customerOrder = customerOrderService.computeAndSetAmounts(customerOrder, customerOrderService.findCustomerOrderItems(customerOrder));
        customerOrder = doAConsistantMerge(customerOrder);
        populateEditForm(uiModel, customerOrder);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "Calcul des taxes effectues avec success !");
        return "customerorders/customerordersView";
    }

    @RequestMapping(value = "/{customerOrderId}/removeOrderItem", method = RequestMethod.GET)
    public String removeOrderItems(@PathVariable("customerOrderId") Long customerOrderId, @RequestParam("itemid") Long[] orderItemIds, Model uiModel) {
        CustomerOrder customerOrder = CustomerOrder.findCustomerOrder(customerOrderId);
        if (!customerOrderService.isBusinessOperationAllowed(customerOrder, BusinessOperation.REMOVE_SUBELEMENT_ITEM)) {
            populateEditForm(uiModel, customerOrder);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Nous somme desole, il est impossible de retirer un element! \n ");
            return "purchaseorders/purchaseordersView";
        }
        customerOrderService.deleteOrderItems(customerOrder, Arrays.asList(orderItemIds));
        customerOrder = customerOrderService.computeAndSetAmounts(customerOrder, customerOrderService.findCustomerOrderItems(customerOrder));
        customerOrder = doAConsistantMerge(customerOrder);
        populateEditForm(uiModel, customerOrder);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "ligne supreimee avec success !");
        return "customerorders/customerordersView";
    }

    @RequestMapping(value = "/{customerOrderId}/cancel", method = RequestMethod.GET)
    public String cancelOrder(@PathVariable("customerOrderId") Long customerOrderId, Model uiModel) {
        CustomerOrder customerOrder = CustomerOrder.findCustomerOrder(customerOrderId);
        if (!customerOrderService.isBusinessOperationAllowed(customerOrder, BusinessOperation.CANCEL)) {
            populateEditForm(uiModel, customerOrder);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Nous somme desole, il est impossible de valider cette commande! \n ");
            return "purchaseorders/purchaseordersView";
        }
        customerOrder = customerOrderService.cancelCustomerOrder(customerOrder);
        customerOrder = customerOrderService.computeAndSetAmounts(customerOrder, customerOrderService.findCustomerOrderItems(customerOrder));
        customerOrder = doAConsistantMerge(customerOrder);
        populateEditForm(uiModel, customerOrder);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "The order has successfully been canceled !");
        return "customerorders/customerordersView";
    }

    @RequestMapping(value = "/{customerOrderId}/close", method = RequestMethod.GET)
    public String closeOrder(@PathVariable("customerOrderId") Long customerOrderId, Model uiModel) {
        CustomerOrder customerOrder = CustomerOrder.findCustomerOrder(customerOrderId);
        if (!customerOrderService.isBusinessOperationAllowed(customerOrder, BusinessOperation.CANCEL)) {
            populateEditForm(uiModel, customerOrder);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Nous somme desole, il est impossible de valider cette commande! \n ");
            return "purchaseorders/purchaseordersView";
        }
        customerOrder = customerOrderService.cancelCustomerOrder(customerOrder);
        customerOrder = customerOrderService.computeAndSetAmounts(customerOrder, customerOrderService.findCustomerOrderItems(customerOrder));
        customerOrder = doAConsistantMerge(customerOrder);
        populateEditForm(uiModel, customerOrder);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, "The order has successfully been canceled !");
        return "customerorders/customerordersView";
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getNextCustomerOrder(@PathVariable("id") Long id, Model uiModel) {
        List<CustomerOrder> nextCustomerOrders = CustomerOrder.findCustomerOrdersByIdUpperThan(id).setMaxResults(1).getResultList();
        if (nextCustomerOrders.isEmpty()) {
            populateEditForm(uiModel, CustomerOrder.findCustomerOrder(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucune commande trouve !");
            return "customerorders/customerordersView";
        }
        CustomerOrder nextCustomerOrder = nextCustomerOrders.iterator().next();
        populateEditForm(uiModel, nextCustomerOrder);
        return "customerorders/customerordersView";
    }

    @RequestMapping(value = "/previous/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getPreviousCustomerOrder(@PathVariable("id") Long id, Model uiModel) {
        List<CustomerOrder> nextCustomerOrders = CustomerOrder.findCustomerOrdersByIdLowerThan(id).setMaxResults(1).getResultList();
        if (nextCustomerOrders.isEmpty()) {
            populateEditForm(uiModel, CustomerOrder.findCustomerOrder(id));
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, "Aucune commande trouve !");
            return "customerorders/customerordersView";
        }
        CustomerOrder nextCustomerOrder = nextCustomerOrders.iterator().next();
        populateEditForm(uiModel, nextCustomerOrder);
        return "customerorders/customerordersView";
    }

    void populateEditForm(Model uiModel, CustomerOrder customerOrder) {
        uiModel.addAttribute("customerOrder", customerOrder);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("documentstateses", Arrays.asList(DocumentStates.values()));
        uiModel.addAttribute("partners", Partner.findAllActiveProviders().getResultList());
        uiModel.addAttribute("devises", Devise.findAllDevises());
        uiModel.addAttribute("companys", Company.findAllCompanys());
        uiModel.addAttribute("taxes", Taxe.findAllTaxes());
        List<CustomerOrderItem> customerOrderItems = customerOrderService.findCustomerOrderItems(customerOrder);
        uiModel.addAttribute("orderItems", customerOrderItems);
    }
}
