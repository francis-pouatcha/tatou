package cm.adorsys.gpao.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import cm.adorsys.gpao.api.model.UdmGroup;
import cm.adorsys.gpao.api.model.UnitOfMesure;

public class UnitOfMesureRepository  extends AbstractHibernateJpaRepository<Long, UnitOfMesure>{

@Inject EntityManager em ;
    
    @Override
    public EntityManager getEntityManager() {
	// TODO Auto-generated method stub
	return em;
    }
    
    
    public  TypedQuery<UnitOfMesure> findUnitOfMesuresByNameEquals(String name) {
	if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
	name = name.replace('*', '%');
	name =name+"%";
	TypedQuery<UnitOfMesure> q = em.createQuery("SELECT o FROM UnitOfMesure AS o WHERE LOWER(o.name) LIKE LOWER(:name)", UnitOfMesure.class);
	q.setParameter("name", name);
	return q;
    }

    public  TypedQuery<UnitOfMesure> findUnitOfMesuresByUnitGroupAndNameLike(UdmGroup unitGroup, String name) {
	if (unitGroup == null) throw new IllegalArgumentException("The unitGroup argument is required");
	if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
	name = name.replace('*', '%');
	if (name.charAt(0) != '%') {
	    name = "%" + name;
	}
	if (name.charAt(name.length() - 1) != '%') {
	    name = name + "%";
	}
	TypedQuery<UnitOfMesure> q = em.createQuery("SELECT o FROM UnitOfMesure AS o WHERE o.unitGroup = :unitGroup AND LOWER(o.name) LIKE LOWER(:name)", UnitOfMesure.class);
	q.setParameter("unitGroup", unitGroup);
	q.setParameter("name", name);
	return q;
    }
}
