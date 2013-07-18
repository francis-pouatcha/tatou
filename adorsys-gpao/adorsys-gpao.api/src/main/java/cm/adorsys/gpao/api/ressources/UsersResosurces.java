package cm.adorsys.gpao.api.ressources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cm.adorsys.gpao.api.model.Users;

@Path("/users")
public interface UsersResosurces {
    
        @POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void create(Users users) ;
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Users> list() ;
	
	@POST
	@Path("/login/{userName}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean login(@PathParam("userName") String userName,@PathParam("password") String password) ;


}
