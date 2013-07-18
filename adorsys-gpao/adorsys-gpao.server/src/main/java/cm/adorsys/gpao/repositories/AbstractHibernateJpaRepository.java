package cm.adorsys.gpao.repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public abstract class AbstractHibernateJpaRepository<K, E> implements JpaRepository<K, E> {

	protected Class<E> entityClass;
	@Inject UserTransaction userTransaction;
	
	@SuppressWarnings("unchecked")
	public AbstractHibernateJpaRepository() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	public void persist(E entity) {
	       try {
		userTransaction.begin();
		getEntityManager().persist(entity);
		userTransaction.commit();
	    } catch (Exception ex) {
		ex.printStackTrace();
	    }
	}

	public void remove(E entity) {
	    if (getEntityManager().contains(entity)) {
		getEntityManager().remove(entity);
	        }
	}

	public void refresh(E entity) {
		getEntityManager().refresh(entity);
	}

	public E merge(E entity) {
		return getEntityManager().merge(entity);
	}

	public E findById(K id) {
		return getEntityManager().find(entityClass, id);
	}

	public E flush(E entity) {
		getEntityManager().flush();
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		String queryStr = "SELECT h FROM " + entityClass.getName() + " h";
		Query query = getEntityManager().createQuery(queryStr, entityClass);
		List<E> resultList = query.getResultList();
		return resultList;
	}

	public Integer removeAll() {
		String queryStr = "DELETE FROM " + entityClass.getName() + " h";
		Query query = getEntityManager().createQuery(queryStr);
		return query.executeUpdate();
	}
	
	public void clair(){
	    getEntityManager().clear(); 
	}
	
	public List<E> findEntityEntries(int firstResult, int maxResults){
	    String queryStr = "SELECT h FROM " + entityClass.getName() + " h";
		Query query = getEntityManager().createQuery(queryStr, entityClass);
		query.setFirstResult(firstResult).setMaxResults(maxResults);
		List<E> resultList = query.getResultList();
		return resultList;
	}
	
	public long count(){
	    return getEntityManager().createQuery("SELECT COUNT(o) FROM "+  entityClass.getName() +" o", Long.class).getSingleResult();
	}
	
	

}
