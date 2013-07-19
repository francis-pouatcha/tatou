package cm.adorsys.gpao.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;

import cm.adorsys.gpao.api.model.UnitOfMesure;
import cm.adorsys.gpao.api.ressources.UnitOfMesureRessources;
import cm.adorsys.gpao.ui.pages.contents.UnitOfMeasureContent;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

@ApplicationScoped
public class UnitOfMesureController {
	public ListDataProvider<UnitOfMesure> dataProvider= new ListDataProvider<UnitOfMesure>(); ;
	public UnitOfMeasureContent unitOfMeasureContent ;
	public List<UnitOfMesure> unitOfMesures = new ArrayList<UnitOfMesure>();
	@Inject ErrorCallback errorCallback ;

	public UnitOfMesureController() { }

	public UnitOfMesureController(UnitOfMeasureContent unitOfMeasureContent) {
		this.unitOfMeasureContent = unitOfMeasureContent;
		dataProvider.getList().addAll(unitOfMesures);	
	}

	public RemoteCallback<List<UnitOfMesure>> udmListCall = new RemoteCallback<List<UnitOfMesure>>() {
		@Override
		public void callback(List<UnitOfMesure> response) {
			unitOfMesures.clear();
			unitOfMesures.addAll(response);

		} 
	};

	public RemoteCallback<UnitOfMesure> udmSaveCall = new RemoteCallback<UnitOfMesure>() {
		@Override
		public void callback(UnitOfMesure response) {
			addUdm(response);

		} 
	};

	public void refreshUdmTable(){
		RestClient.create(UnitOfMesureRessources.class,udmListCall , errorCallback).list();
		dataProvider.setList(unitOfMesures);
		dataProvider.refresh();
	}

	public void saveUdm(UnitOfMesure unitOfMesure){
		RestClient.create(UnitOfMesureRessources.class,udmSaveCall , errorCallback).save(unitOfMesure);
	}

	public void addUdm(UnitOfMesure unitOfMesure){
		unitOfMesures.add(0, unitOfMesure);
		dataProvider.refresh();
	}

	public void buildUdmTable(){

		TextColumn<UnitOfMesure> name = new TextColumn<UnitOfMesure>() {
			@Override
			public String getValue(UnitOfMesure object) {
				return object.getName();
			}
		};
		unitOfMeasureContent.getUdmTable().addColumn(name,"Libelle");

		TextColumn<UnitOfMesure> categorie = new TextColumn<UnitOfMesure>() {
			@Override
			public String getValue(UnitOfMesure object) {
				return object.getUnitGroup()+"";
			}
		};
		unitOfMeasureContent.getUdmTable().addColumn(categorie,"Categorie");

		TextColumn<UnitOfMesure> ratio = new TextColumn<UnitOfMesure>() {
			@Override
			public String getValue(UnitOfMesure object) {
				return object.getRatio()+"";
			}
		};
		unitOfMeasureContent.getUdmTable().addColumn(ratio,"Ratio");

		TextColumn<UnitOfMesure> enable = new TextColumn<UnitOfMesure>() {
			@Override
			public String getValue(UnitOfMesure object) {
				return object.getEnabled()?"Oui":"Non";
			}
		};
		unitOfMeasureContent.getUdmTable().addColumn(enable,"Actif ?");

		TextColumn<UnitOfMesure> udmRef = new TextColumn<UnitOfMesure>() {
			@Override
			public String getValue(UnitOfMesure object) {
				return object.getIsRefUdm()?"Oui":"Non";
			}
		};
		unitOfMeasureContent.getUdmTable().addColumn(udmRef,"Udm Ref ?");


		final SingleSelectionModel<UnitOfMesure> selectionModel = new SingleSelectionModel<UnitOfMesure>();
		unitOfMeasureContent.getUdmTable().setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(
				new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						UnitOfMesure unitOfMesure = selectionModel.getSelectedObject();
						if (unitOfMesure != null) {
							unitOfMeasureContent.setUnitOfMesure(unitOfMesure);
						}
					}
				});

	}

	//Getters And Setters

	public ListDataProvider<UnitOfMesure> getDataProvider() {
		return dataProvider;
	}

	public void setDataProvider(ListDataProvider<UnitOfMesure> dataProvider) {
		this.dataProvider = dataProvider;
	}

	public UnitOfMeasureContent getUnitOfMeasureContent() {
		return unitOfMeasureContent;
	}

	public void setUnitOfMeasureContent(UnitOfMeasureContent unitOfMeasureContent) {
		this.unitOfMeasureContent = unitOfMeasureContent;
	}

	public List<UnitOfMesure> getUnitOfMesures() {
		return unitOfMesures;
	}

	public void setUnitOfMesures(List<UnitOfMesure> unitOfMesures) {
		this.unitOfMesures = unitOfMesures;
	}

	public ErrorCallback getErrorCallback() {
		return errorCallback;
	}

	public void setErrorCallback(ErrorCallback errorCallback) {
		this.errorCallback = errorCallback;
	}

	public RemoteCallback<List<UnitOfMesure>> getUdmListCall() {
		return udmListCall;
	}

	public void setUdmListCall(RemoteCallback<List<UnitOfMesure>> udmListCall) {
		this.udmListCall = udmListCall;
	}


}
