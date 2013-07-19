package cm.adorsys.gpao.ui.widgets;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

@Dependent
public class Dashboard extends Composite {

    private static DashboardUiBinder uiBinder = GWT.create(DashboardUiBinder.class);

    interface DashboardUiBinder extends UiBinder<Widget, Dashboard> {
    }


    public Dashboard() {
	initWidget(uiBinder.createAndBindUi(this));
    }

}
