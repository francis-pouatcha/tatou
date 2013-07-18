package cm.adorsys.gpao.ui.widgets;

import javax.enterprise.context.ApplicationScoped;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped
public class DashbordSideBar extends Composite  {

    private static DashbordSideBarUiBinder uiBinder = GWT
	    .create(DashbordSideBarUiBinder.class);

    interface DashbordSideBarUiBinder extends UiBinder<Widget, DashbordSideBar> {
    }

    public DashbordSideBar() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    

}
