package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.TaxeType;
import cm.adorsys.gpao.utils.MessageType;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/taxes")
@Controller
@RooWebScaffold(path = "taxes", formBackingObject = Taxe.class)
public class TaxeController {

    @RequestMapping(value = "/config", produces = "text/html")
    public String configTaxe(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        Taxe taxe = null;
        if (id != null) taxe = Taxe.findTaxe(id);
        populateView(uiModel, taxe, null);
        return "taxes/config";
    }

    @RequestMapping(value = "/save/{id}", produces = "text/html")
    public String editTaxe(@PathVariable("id") Long id, Model uiModel) {
        populateView(uiModel, Taxe.findTaxe(id), null);
        return "taxes/config";
    }

    @RequestMapping(value = "/save", produces = "text/html", method = RequestMethod.POST)
    public String configTaxe(@Valid Taxe taxe, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateView(uiModel, taxe, null);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
            return "taxes/config";
        }
        uiModel.asMap().clear();
        Taxe merge = taxe.merge();
        populateView(uiModel, null, null);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, Arrays.asList("Taxe enregistrer avec success !"));
        return "taxes/config";
    }

    @RequestMapping(value = "/findByNameAndType", produces = "text/html", method = RequestMethod.POST)
    public String findByNameAndType(Taxe taxe, Model uiModel) {
        List<Taxe> taxes = Taxe.findTaxeByNameLikeTaxeType(taxe.getName(), taxe.getTaxeType()).getResultList();
        populateView(uiModel, null, taxes);
        return "taxes/config";
    }

    public void populateView(Model uiModel, Taxe taxe, List<Taxe> taxes) {
        uiModel.addAttribute("taxes", taxes == null ? Taxe.findAllTaxes() : taxes);
        uiModel.addAttribute("taxe", taxe == null ? new Taxe() : taxe);
        uiModel.addAttribute("taxetypes", Arrays.asList(TaxeType.values()));
    }
}
