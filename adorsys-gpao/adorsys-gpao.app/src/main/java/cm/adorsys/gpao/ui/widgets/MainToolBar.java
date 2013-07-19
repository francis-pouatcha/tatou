package cm.adorsys.gpao.ui.widgets;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.github.gwtbootstrap.client.ui.base.DivWidget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped
public class MainToolBar extends Composite  {

    private static MainToolBarUiBinder uiBinder = GWT
	    .create(MainToolBarUiBinder.class);

    interface MainToolBarUiBinder extends UiBinder<Widget, MainToolBar> {
    }

    public MainToolBar() {
	initWidget(uiBinder.createAndBindUi(this));
	init();
    }

    @UiField 
    HTML  toolbar ;
    
    @PostConstruct
    public void init(){
	toolbar.setHTML("<h3><i class='icon-home'></i>Dashboard</h3>");
    }
    

    public HTML getToolbar() {
        return toolbar;
    }

    public void setToolbar(HTML toolbar) {
        this.toolbar = toolbar;
    }
    
    
    


}
