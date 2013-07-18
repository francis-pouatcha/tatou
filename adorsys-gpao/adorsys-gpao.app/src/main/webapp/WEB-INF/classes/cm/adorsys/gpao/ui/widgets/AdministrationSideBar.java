package cm.adorsys.gpao.ui.widgets;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped
public class AdministrationSideBar extends Composite {
	

	private static AdministrationSideBarUiBinder uiBinder = GWT.create(AdministrationSideBarUiBinder.class);
	interface AdministrationSideBarUiBinder extends UiBinder<Widget, AdministrationSideBar> {
	}

	public AdministrationSideBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
