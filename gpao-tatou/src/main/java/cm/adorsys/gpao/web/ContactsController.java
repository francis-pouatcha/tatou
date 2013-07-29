package cm.adorsys.gpao.web;

import cm.adorsys.gpao.model.Contacts;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/contactses")
@Controller
@RooWebScaffold(path = "contactses", formBackingObject = Contacts.class)
public class ContactsController {
}
