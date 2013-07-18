package cm.adorsys.gpao.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public interface JpaRepository<K, E> {
    
 public void persist(E entity) ;

public void remove(E entity) ;

public void refresh(E entity) ;

public E merge(E entity) ;

public E findById(K id) ;

public E flush(E entity) ;

public List<E> findAll();

public Integer removeAll() ;

public void clair();

public long count();

public List<E> findEntityEntries(int firstResult, int maxResults) ;

public EntityManager getEntityManager();

}
