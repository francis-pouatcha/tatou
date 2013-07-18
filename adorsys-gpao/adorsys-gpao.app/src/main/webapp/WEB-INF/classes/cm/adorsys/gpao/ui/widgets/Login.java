package cm.adorsys.gpao.ui.widgets;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.ui.nav.client.local.PageShowing;
import org.jboss.errai.ui.nav.client.local.TransitionTo;

import cm.adorsys.gpao.api.model.Users;
import cm.adorsys.gpao.support.Val;
import cm.adorsys.gpao.ui.pages.DashBordPage;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

@Dependent
public class Login extends Composite {

    private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

    interface LoginUiBinder extends UiBinder<Widget, Login> {
    }

    public Login() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Inject TransitionTo<DashBordPage> DashBordPageTransition ;
    @Inject DataBinder<Users> userBinder ;
    @Inject Val val;

    private Users users ;

    @UiField
     TextBox userName;

    @UiField
    TextBox password;

    @UiField
    Button signIn;

    @UiHandler("signIn")
    public void onLogin(ClickEvent clickEvent){
	if(val.validate(users)) DashBordPageTransition.go() ;
    }

    @PostConstruct
    public void onPostconstruct(){
	bindToUI();
    }
    public void bindToUI(){
	users = userBinder.bind(userName, "userName").
	bind(password, "password").getModel();
    }

    public Users getUsers() {
	return users;
    }

    public void setUsers(Users users) {
	this.users = users;
    }

    public TextBox getUserName() {
	return userName;
    }

    public void setUserName(TextBox userName) {
	this.userName = userName;
    }

    public TextBox getPassword() {
	return password;
    }

    public void setPassword(TextBox password) {
	this.password = password;
    }

    public Button getSignIn() {
	return signIn;
    }

    public void setSignIn(Button signIn) {
	this.signIn = signIn;
    }




}
