package cm.adorsys.gpao.services;

import java.util.Set;

import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;

public class UdmManageServices implements IUdmManageServices{

	
	@Override
	public UnitOfMesures saveOrUpdate(UnitOfMesures unitOfMesures) {
		if(unitOfMesures == null) throw new IllegalArgumentException("Unit Of Mesure Is required") ;
		if(unitOfMesures.getId()!=null) return unitOfMesures.merge() ;
		unitOfMesures.persist();
		return unitOfMesures ;
	}

	@Override
	public Set<UnitOfMesures> findByGroupAndNameLike(String nameLike,
			UdmGroup group) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<UnitOfMesures> findByGroupAndNameLike(String nameLike,
			UdmGroup group, int first, int last) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<UnitOfMesures> findByGroupAndNameLike(int first, int last) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<UnitOfMesures> findByAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
