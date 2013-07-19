package cm.adorsys.gpao.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import cm.adorsys.gpao.api.model.UdmGroup;

public class UdmGroupRepository extends AbstractHibernateJpaRepository<Long, UdmGroup> {
   @Inject EntityManager em ;
    
    @Override
    public EntityManager getEntityManager() {
	return em;
    }

}
