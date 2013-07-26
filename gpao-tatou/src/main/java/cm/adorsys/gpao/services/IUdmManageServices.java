package cm.adorsys.gpao.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;

@Service
public interface IUdmManageServices {
	
	public UnitOfMesures saveOrUpdate(UnitOfMesures unitOfMesures) ;
	
	public Set<UnitOfMesures> findByGroupAndNameLike(String nameLike , UdmGroup group);
	
	public Set<UnitOfMesures> findByGroupAndNameLike(String nameLike , UdmGroup group , int first ,int last);
	
	public Set<UnitOfMesures> findByGroupAndNameLike(int first ,int last);
	
	public Set<UnitOfMesures> findByAll();
	
	
	
}
