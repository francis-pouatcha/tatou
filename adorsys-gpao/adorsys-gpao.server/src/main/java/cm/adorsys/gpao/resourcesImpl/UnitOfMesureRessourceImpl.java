package cm.adorsys.gpao.resourcesImpl;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import cm.adorsys.gpao.api.model.UdmGroup;
import cm.adorsys.gpao.api.model.UnitOfMesure;
import cm.adorsys.gpao.api.ressources.UnitOfMesureRessources;
import cm.adorsys.gpao.repositories.UdmGroupRepository;
import cm.adorsys.gpao.repositories.UnitOfMesureRepository;
import cm.adorsys.gpao.repositories.UsersRepository;

@ApplicationScoped
public class UnitOfMesureRessourceImpl implements UnitOfMesureRessources {
	@Inject UnitOfMesureRepository unitOfMesureRepository ;
	@Inject UdmGroupRepository udmGroupRepository ;
	@Inject UserTransaction userTransaction;

	@Override
	public UnitOfMesure save(UnitOfMesure unitOfMesure) {
		unitOfMesureRepository.persist(unitOfMesure);
		return unitOfMesure ;
	}

	@Override
	public List<UnitOfMesure> list() {
		if(unitOfMesureRepository.count() <= 0){
			UdmGroup udmGroup = new UdmGroup("group"+0);
			udmGroupRepository.persist(udmGroup);
			for (int j = 0; j <100; j++) {
				UnitOfMesure unitOfMesure = new UnitOfMesure(j);
				unitOfMesure.setUnitGroup(udmGroup);
				unitOfMesureRepository.persist(unitOfMesure);
			}
		}
		return unitOfMesureRepository.findAll();
	}

	@Override
	public List<UnitOfMesure> findByName(@PathParam("name") String name) {
		return unitOfMesureRepository.findUnitOfMesuresByNameEquals(name).getResultList();
	}

	@Override
	public List<UnitOfMesure> findByNameAndUdmGroup(
			@PathParam("name") String name, UdmGroup udmGroup) {
		return unitOfMesureRepository.findUnitOfMesuresByUnitGroupAndNameLike(udmGroup, name).getResultList();
	}

}
