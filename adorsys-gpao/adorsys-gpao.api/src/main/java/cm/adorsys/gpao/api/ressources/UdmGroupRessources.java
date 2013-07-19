package cm.adorsys.gpao.api.ressources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cm.adorsys.gpao.api.model.UdmGroup;
import cm.adorsys.gpao.api.model.UnitOfMesure;

@Path("unit-group")
public interface UdmGroupRessources {

	@POST
	@Path("/create") 
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UnitOfMesure save(UdmGroup udmGroup) ;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UnitOfMesure> list() ;

	@GET
	@Path("/findByName/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UnitOfMesure> findByName(@PathParam("name") String name) ;
	


}
