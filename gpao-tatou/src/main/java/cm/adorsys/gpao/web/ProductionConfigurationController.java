/**
 * 
 */
package cm.adorsys.gpao.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author bwa
 *
 */
@Controller
@RequestMapping(value="/productionconfiguration")
public class ProductionConfigurationController {

    @RequestMapping(produces = "text/html")
    public String showMainPage(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
    	
    	return "productionconfiguration/mainpage";
    }
}