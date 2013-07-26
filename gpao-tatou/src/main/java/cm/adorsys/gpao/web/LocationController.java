package cm.adorsys.gpao.web;

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

import cm.adorsys.gpao.model.Location;
import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.WareHouses;
import cm.adorsys.gpao.utils.MessageType;

@RequestMapping("/locations")
@Controller
@RooWebScaffold(path = "locations", formBackingObject = Location.class)
public class LocationController {
	
	@RequestMapping(value = "/config", produces = "text/html")
	public String configLocation(@RequestParam(value="id",required=false) Long id, Model uiModel) {
		WareHouses wareHouse = null ;
		if(id!=null) wareHouse = WareHouses.findWareHouses(id);
		populateView(uiModel, null,null,wareHouse);
		return "locations/config";
	}

	@RequestMapping(value = "/save/{id}", produces = "text/html")
	public String editLocation(@PathVariable("id")Long id , Model uiModel) {
		populateView(uiModel, Location.findLocation(id),null,new WareHouses());
		return "locations/config";
	}

	@RequestMapping(value = "/save", produces = "text/html" ,method = RequestMethod.POST)
	public String configLocation(@Valid Location location, BindingResult bindingResult , Model uiModel,HttpServletRequest httpServletRequest) {
		if (bindingResult.hasErrors()) {
			populateView(uiModel, location,null,new WareHouses());
			uiModel.addAttribute(MessageType.ERROR_MESSAGE,Arrays.asList("Une erreur est Survenue durant l'enregistrement !"));
			return "locations/config";
		}
		uiModel.asMap().clear();
		Location merge =location.merge();
		 populateView(uiModel, null,null ,null);
	     uiModel.addAttribute(MessageType.SUCCESS_MESSAGE,Arrays.asList("Emplacement enregistrer avec success !"));
        return "locations/config";
	}
	
	@RequestMapping(value = "/findByNameAndWareHouse", produces = "text/html" ,method = RequestMethod.POST)
	public String findLocation(Location location , Model uiModel) {
		List<Location> locations = Location.findLocationsByNameLikeAndWareHouses(location.getName(), location.getWareHouse()).getResultList();
		populateView(uiModel, null, locations , new WareHouses());
		return "locations/config";
	}

	public void populateView(Model uiModel,Location location ,List<Location> locations,WareHouses wareHouses){
		uiModel.addAttribute("locations",locations==null? Location.findAllLocations():locations) ;
		uiModel.addAttribute("location", location==null? new Location():location) ;
		List<WareHouses> allWareHouses = WareHouses.findAllWareHouseses();
		if(wareHouses!=null) allWareHouses.add(0, wareHouses);
		uiModel.addAttribute("warehouseses", allWareHouses ) ;
	}
}
