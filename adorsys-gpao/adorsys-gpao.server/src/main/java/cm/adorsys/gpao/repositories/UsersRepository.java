package cm.adorsys.gpao.repositories;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;

import cm.adorsys.gpao.api.model.Users;

@Singleton
public class UsersRepository extends AbstractHibernateJpaRepository<Long, Users> {

    @Inject  EntityManager em ;
  
    @Override
    public EntityManager getEntityManager() {
	return em;
    }
    
    public  TypedQuery<Users> findUsersByUserNameAndPasswordEquals(String userName ,String password) {
	if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) 
	    throw new IllegalArgumentException("The userName And password argument is required");
	
	TypedQuery<Users> q = null;
	q = em.createQuery("SELECT o FROM Users AS o WHERE o.userName = :userName  AND o.password != :password ", Users.class);
	q.setParameter("password", password);
	q.setParameter("userName", userName);
	return q;
}
    

}
