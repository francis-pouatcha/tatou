package cm.adorsys.gpao.resourcesImpl;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.UserTransaction;
import javax.ws.rs.PathParam;

import cm.adorsys.gpao.api.model.Users;
import cm.adorsys.gpao.api.ressources.UsersResosurces;
import cm.adorsys.gpao.repositories.UsersRepository;

@ApplicationScoped 
public class USersRessourceImpl implements UsersResosurces {

    @Inject UsersRepository usersRepository ;
    @Inject UserTransaction userTransaction;
    
    @Override
    public void create(Users users) {
          usersRepository.persist(users);
          System.out.println("user persisted : "+users);
    }

    @Override
    public List<Users> list() {
	return usersRepository.findAll();
    }

    @Override
	public Boolean login(@PathParam("userName") String userName,@PathParam("password") String password) {
	List<Users> resultList = usersRepository.findUsersByUserNameAndPasswordEquals(userName, password).getResultList();
	System.out.println(Boolean.valueOf(!resultList.isEmpty()));
	return Boolean.valueOf(!resultList.isEmpty());
    }

}
