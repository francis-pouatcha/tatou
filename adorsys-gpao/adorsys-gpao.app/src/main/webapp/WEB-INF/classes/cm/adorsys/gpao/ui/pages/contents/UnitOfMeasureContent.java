package cm.adorsys.gpao.ui.pages.contents;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.databinding.client.api.DataBinder;

import cm.adorsys.gpao.api.model.UnitOfMesure;
import cm.adorsys.gpao.controllers.UnitOfMesureController;
import cm.adorsys.gpao.support.Val;
import cm.adorsys.gpao.ui.widgets.BindableWidgets;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.CheckBox;
import com.github.gwtbootstrap.client.ui.ListBox;
import com.github.gwtbootstrap.client.ui.SimplePager;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@Dependent
public class UnitOfMeasureContent  extends Composite implements BindableWidgets  {

	private static UnitOfMeasureUiBinder uiBinder = GWT
			.create(UnitOfMeasureUiBinder.class);

	interface UnitOfMeasureUiBinder extends UiBinder<Widget, UnitOfMeasureContent> {
	}

	
	@Inject Val val;
	
	@UiField ListBox udmSearch ;
	
	@UiField TextBox query ;
	
	@UiField Button search ;
	
	@UiField TextBox name ;
	
	@UiField TextBox ratio ;
	
	@UiField ListBox udmGroup ;
	
	@UiField CheckBox actif ;
	
	@UiField CheckBox udmRef ;
	
	@UiField Button categorie ;
	
	@UiField Button save ;
	
	@UiField CellTable<UnitOfMesure> udmTable ;
	
	@UiField SimplePager pager ;
	
	public UnitOfMesure unitOfMesure ; 
	
	@Inject DataBinder<UnitOfMesure> unitOfMesureBinder ;
	
	public UnitOfMesureController unitOfMesureController ;

	public UnitOfMeasureContent() {
		udmTable = new CellTable<UnitOfMesure>();
		udmTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		unitOfMesureController = new UnitOfMesureController(this);
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public void bindBeansToWidgets() {
		unitOfMesure = unitOfMesureBinder.bind(name, "name").
				bind(ratio, "ratio")
				.bind(udmGroup, "unitGroup")
				.bind(actif, "enabled")
				.bind(udmRef, "isRefUdm").getModel();

	}

	@PostConstruct
    public void createUi(){
	unitOfMesureController.buildUdmTable();
	unitOfMesureController.getDataProvider().addDataDisplay(udmTable);
	pager.setPageSize(10);
	pager.setDisplay(udmTable);
	bindBeansToWidgets();

    }

    @UiHandler("save")
    public void onSaveClick(ClickEvent clickEvent){
    	if(val.validate(unitOfMesure)){
    		unitOfMesureController.saveUdm(unitOfMesure);
    	}
    }
	
	//getter and setters
	public Val getVal() {
		return val;
	}

	public void setVal(Val val) {
		this.val = val;
	}

	public ListBox getUdmSearch() {
		return udmSearch;
	}

	public void setUdmSearch(ListBox udmSearch) {
		this.udmSearch = udmSearch;
	}

	public TextBox getQuery() {
		return query;
	}

	public void setQuery(TextBox query) {
		this.query = query;
	}

	public Button getSearch() {
		return search;
	}

	public void setSearch(Button search) {
		this.search = search;
	}

	public TextBox getName() {
		return name;
	}

	public void setName(TextBox name) {
		this.name = name;
	}

	public TextBox getRatio() {
		return ratio;
	}

	public void setRatio(TextBox ratio) {
		this.ratio = ratio;
	}

	public ListBox getUdmGroup() {
		return udmGroup;
	}

	public void setUdmGroup(ListBox udmGroup) {
		this.udmGroup = udmGroup;
	}

	public CheckBox getActif() {
		return actif;
	}

	public void setActif(CheckBox actif) {
		this.actif = actif;
	}

	public CheckBox getUdmRef() {
		return udmRef;
	}

	public void setUdmRef(CheckBox udmRef) {
		this.udmRef = udmRef;
	}

	public Button getCategorie() {
		return categorie;
	}

	public void setCategorie(Button categorie) {
		this.categorie = categorie;
	}

	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

	public UnitOfMesure getUnitOfMesure() {
		return unitOfMesure;
	}

	public void setUnitOfMesure(UnitOfMesure unitOfMesure) {
		this.unitOfMesure = unitOfMesure;
	}

	public DataBinder<UnitOfMesure> getUnitOfMesureBinder() {
		return unitOfMesureBinder;
	}

	public void setUnitOfMesureBinder(DataBinder<UnitOfMesure> unitOfMesureBinder) {
		this.unitOfMesureBinder = unitOfMesureBinder;
	}

	public CellTable<UnitOfMesure> getUdmTable() {
		return udmTable;
	}

	public void setUdmTable(CellTable<UnitOfMesure> udmTable) {
		this.udmTable = udmTable;
	}





	

}
