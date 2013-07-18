package cm.adorsys.gpao.ui.layouts;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;

import cm.adorsys.gpao.ui.widgets.Footer;
import cm.adorsys.gpao.ui.widgets.Header;
import cm.adorsys.gpao.ui.widgets.MainToolBar;
import cm.adorsys.gpao.ui.widgets.AdministrationSideBar;

import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.ModalFooter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author clovisgakam
 *default template for authorised user .
 */
@Dependent
public class Template extends Composite {

    private static TemplateUiBinder uiBinder = GWT
	    .create(TemplateUiBinder.class);

    interface TemplateUiBinder extends UiBinder<Widget, Template> {
    }

    public Template() {
	initWidget(uiBinder.createAndBindUi(this));
	helpInfos.setCloseVisible(true);
	helpInfos.setTitle("Help Informations !") ;
	helpInfos.setAnimation(true);
	addHelpInfoClickListener();
    }
  
   private static final String DEFAULT_INFORMATIONS ="<div class='alert alert-info'>No informations avalable for this Page !</div>";
   @UiField 
   protected Header header;
   
   @UiField 
   protected MainToolBar mainToolBar;
   
   @UiField 
   protected SimplePanel sidebar;
   
   @UiField 
   protected Footer footer;
   
   @UiField 
   protected SimplePanel content;
   
   @UiField 
   protected Modal helpInfos;
   
  public void addHelpInformations(IsWidget helpInformations){
      helpInfos.add(helpInformations);
   }
  
 public void addHelpInfoClickListener(){
     header.getHelp().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		helpInfos.show();
		
	    }
	    
	});
 }
  
public Header getHeader() {
    return header;
}

public void setHeader(Header header) {
    this.header = header;
}

public MainToolBar getMainToolBar() {
    return mainToolBar;
}

public void setMainToolBar(MainToolBar mainToolBar) {
    this.mainToolBar = mainToolBar;
}

public SimplePanel getSidebar() {
    return sidebar;
}

public void setSidebar(SimplePanel sidebar) {
    this.sidebar = sidebar;
}

public Footer getFooter() {
    return footer;
}

public void setFooter(Footer footer) {
    this.footer = footer;
}

public SimplePanel getContent() {
    return content;
}

public void setContent(SimplePanel content) {
    this.content = content;
}

public Modal getHelpInfos() {
    return helpInfos;
}

public void setHelpInfos(Modal helpInfos) {
    this.helpInfos = helpInfos;
}

}
