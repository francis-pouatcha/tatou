package cm.adorsys.gpao.web;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cm.adorsys.gpao.model.Caracteristic;
import cm.adorsys.gpao.model.Specificity;
import cm.adorsys.gpao.model.SpecificityToCaracteristicMap;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/specificitys")
@Controller
@RooWebScaffold(path = "specificitys", formBackingObject = Specificity.class)
public class SpecificityController {

    /**
     * <p>
     * 	According to the specification, editing a product specificity is not allowed.
     *  So we have changed the behavior of the update form to redirect to the show view. <br />
     *  The update buttons are not visible, in the UI. But someone can maliciously use
     *  the update link, directly in the browser to try to access to form.
     * </p>
     * @param id
     * @param uiModel
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
        uiModel.addAttribute(MessageType.INFOS_MESSAGE, Arrays.asList("La modification des specificites n'est pas allouee dans cette version!"));
        return "specificitys/manage";
    }

    @RequestMapping(value = "/{id}/disable", produces = "text/html")
    public String disableSpecificity(@PathVariable("id") Long id, Model uiModel, HttpServletRequest httpServletRequest) {
        Specificity specificity = Specificity.findSpecificity(id);
        specificity.setActive(Boolean.FALSE);
        specificity.merge();
        paginatedListQuery(uiModel, specificity, null, 1, 10, null, null);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, Arrays.asList("Specficite annulee avec success!"));
        return "specificitys/manage";
    }

    @RequestMapping(value = "/manage", produces = "text/html")
    public String mangeSpecificity(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        Specificity specificity = null;
        if (id != null) specificity = Specificity.findSpecificity(id);
        paginatedListQuery(uiModel, specificity, null, page, size, sortFieldName, sortOrder);
        return "specificitys/manage";
    }

    @RequestMapping(value = "/save/{id}", produces = "text/html")
    public String editTaxe(@PathVariable("id") Long id, Model uiModel) {
        paginatedListQuery(uiModel, Specificity.findSpecificity(id), null, 1, 10, null, null);
        return "specificitys/manage";
    }

    @RequestMapping(value = "/save", produces = "text/html", method = RequestMethod.GET)
    public String configSpecifity(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel, HttpServletRequest httpServletRequest) {
        paginatedListQuery(uiModel, null, null, page, size, sortFieldName, sortOrder);
        return "specificitys/manage";
    }

    @RequestMapping(value = "/save", produces = "text/html", method = RequestMethod.POST)
    public String configSpecifity(@Valid Specificity specificity, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            paginatedListQuery(uiModel, specificity, null, 1, 10, null, null);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
            return "specificitys/manage";
        }
        uiModel.asMap().clear();
        if (specificity.getId() != null) {
            return cannotUpdateForTheMoment(specificity, uiModel);
        }
        specificity.merge();
        paginatedListQuery(uiModel, null, null, 1, 10, null, null);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, Arrays.asList("Specificite du produit enregistree avec success !"));
        return "specificitys/manage";
    }

    private String cannotUpdateForTheMoment(Specificity specificity, Model uiModel) {
        paginatedListQuery(uiModel, Specificity.findSpecificity(specificity.getId()), null, 1, 10, null, null);
        uiModel.addAttribute(MessageType.INFOS_MESSAGE, Arrays.asList("La modification des specificites n'est pas allouee dans cette version!"));
        return "specificitys/manage";
    }

    @RequestMapping(value = "/findByDesignationAndActive", produces = "text/html", method = RequestMethod.POST)
    public String findByNameAndType(@Valid Specificity specificity, BindingResult bindingResult, Model uiModel) {
        if (bindingResult.hasErrors()) {
            paginatedListQuery(uiModel, specificity, null, 1, 10, null, null);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, Arrays.asList("S'il vous plait veuiller remplir la designation !"));
            return "specificitys/manage";
        }
        String designation = specificity.getDesignation() == null ? "" : specificity.getDesignation();
        List<Specificity> specificities = new ArrayList<Specificity>();
        if (StringUtils.isBlank(designation) && specificity.getActive() == null) {
            specificities = Specificity.findAllSpecificitys();
        } else if (specificity.getActive() == null) {
            specificities = Specificity.findSpecificitysByDesignationLike(designation).getResultList();
        } else {
            specificities = Specificity.findSpecificitysByDesignationLikeAndActiveNot(designation, !specificity.getActive()).getResultList();
        }
        paginatedListQuery(uiModel, null, specificities, null, null, null, null);
        return "specificitys/manage";
    }

    public void populateView(Model uiModel, Specificity specificity, List<Specificity> specificities) {
        uiModel.addAttribute("specificitys", specificities == null ? Specificity.findAllSpecificitys() : specificities);
        uiModel.addAttribute("specificity", specificity == null ? new Specificity() : specificity);
    }

    private void paginatedListQuery(Model uiModel, Specificity specificity, List<Specificity> specificities, Integer page, Integer size, String sortFieldName, String sortOrder) {
        if (specificity == null) {
            uiModel.addAttribute("specificity", new Specificity());
        } else {
            uiModel.addAttribute("specificity", specificity);
            List<Caracteristic> caracteristics = SpecificityToCaracteristicMap.findCaracteristicsBySpecificityEquals(specificity).getResultList();
            uiModel.addAttribute("caracteristics", caracteristics);
        }
        if (specificities != null) {
            uiModel.addAttribute("specificitys", specificities);
            return; // stop the execution if specificites are not null
        }
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("specificitys", Specificity.findSpecificityEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Specificity.countSpecificitys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("specificitys", Specificity.findAllSpecificitys(sortFieldName, sortOrder));
        }
    }
}
