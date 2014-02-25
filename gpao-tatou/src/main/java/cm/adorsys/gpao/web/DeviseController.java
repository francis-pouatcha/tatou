package cm.adorsys.gpao.web;
import cm.adorsys.gpao.model.Devise;
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

@RequestMapping("/devises")
@Controller
@RooWebScaffold(path = "devises", formBackingObject = Devise.class)
public class DeviseController {

    @RequestMapping(value = "/config", produces = "text/html")
    public String configDevise(@RequestParam(value = "id", required = false) Long id, Model uiModel) {
        Devise taxe = null;
        if (id != null) taxe = Devise.findDevise(id);
        populateView(uiModel, taxe, null);
        return "devises/config";
    }

    @RequestMapping(value = "/save/{id}", produces = "text/html")
    public String editDevise(@PathVariable("id") Long id, Model uiModel) {
        populateView(uiModel, Devise.findDevise(id), null);
        return "devises/config";
    }

    @RequestMapping(value = "/save", produces = "text/html", method = RequestMethod.POST)
    public String configDevise(@Valid Devise devise, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateView(uiModel, devise, null);
            uiModel.addAttribute(MessageType.ERROR_MESSAGE, Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
            return "devises/config";
        }
        uiModel.asMap().clear();
        Devise merge = devise.merge();
        populateView(uiModel, null, null);
        uiModel.addAttribute(MessageType.SUCCESS_MESSAGE, Arrays.asList("Devise enregistrer avec success !"));
        return "devises/config";
    }

    @RequestMapping(value = "/findByNameOrShortName", produces = "text/html", method = RequestMethod.POST)
    public String findByNameOrShortName(Devise devise, Model uiModel) {
        List<Devise> devises = Devise.findTaxeByNameOrShortName(devise.getName(), devise.getShortName()).getResultList();
        populateView(uiModel, null, devises);
        return "devises/config";
    }

    public void populateView(Model uiModel, Devise devise, List<Devise> devises) {
        uiModel.addAttribute("devises", devises == null ? Devise.findAllDevises() : devises);
        uiModel.addAttribute("devise", devise == null ? new Devise() : devise);
    }
}
