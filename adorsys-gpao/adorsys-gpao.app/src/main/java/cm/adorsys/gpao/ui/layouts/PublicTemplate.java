package cm.adorsys.gpao.ui.layouts;

import javax.enterprise.context.Dependent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class PublicTemplate extends Composite {

    private static PublicTemplateUiBinder uiBinder = GWT
	    .create(PublicTemplateUiBinder.class);

    interface PublicTemplateUiBinder extends UiBinder<Widget, PublicTemplate> {
    }

    public PublicTemplate() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @UiField 
    protected SimplePanel content;
    
    

}
