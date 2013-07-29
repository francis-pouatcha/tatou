package cm.adorsys.gpao.model.uiModels;

import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

public class UinitOfMesureUiModels {

    private String unitName;

    private UdmGroup udmGroup ;

    private Set<UnitOfMesures> unitOfMesures = new HashSet<UnitOfMesures>();

    private UnitOfMesures selectedUnit ;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public UdmGroup getUdmGroup() {
		return udmGroup;
	}

	public void setUdmGroup(UdmGroup udmGroup) {
		this.udmGroup = udmGroup;
	}

	public Set<UnitOfMesures> getUnitOfMesures() {
		return unitOfMesures;
	}

	public void setUnitOfMesures(Set<UnitOfMesures> unitOfMesures) {
		this.unitOfMesures = unitOfMesures;
	}

	public UnitOfMesures getSelectedUnit() {
		return selectedUnit;
	}

	public void setSelectedUnit(UnitOfMesures selectedUnit) {
		this.selectedUnit = selectedUnit;
	}
    
    
}
