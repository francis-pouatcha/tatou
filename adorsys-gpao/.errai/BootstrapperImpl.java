package org.jboss.errai.ioc.client;

import cm.adorsys.gpao.App;
import cm.adorsys.gpao.api.model.Users;
import cm.adorsys.gpao.api.ressources.UsersResosurces;
import cm.adorsys.gpao.controllers.LoginController;
import cm.adorsys.gpao.support.BootstrapErrorRenderer;
import cm.adorsys.gpao.support.ErrorRenderer;
import cm.adorsys.gpao.support.Val;
import cm.adorsys.gpao.ui.layouts.PublicTemplate;
import cm.adorsys.gpao.ui.layouts.Template;
import cm.adorsys.gpao.ui.pages.DashBordPage;
import cm.adorsys.gpao.ui.pages.LoginPage;
import cm.adorsys.gpao.ui.pages.contents.Login;
import cm.adorsys.gpao.ui.widgets.Dashboard;
import cm.adorsys.gpao.ui.widgets.Footer;
import cm.adorsys.gpao.ui.widgets.Header;
import cm.adorsys.gpao.ui.widgets.MainToolBar;
import cm.adorsys.gpao.ui.widgets.SideBar;
import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasVisibility;
import com.google.gwt.user.client.ui.IsRenderable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;
import java.lang.annotation.Annotation;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.New;
import javax.inject.Provider;
import javax.validation.Validator;
import org.jboss.errai.databinding.client.DataBinderProvider;
import org.jboss.errai.databinding.client.DataBindingModuleBootstrapper;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.enterprise.client.cdi.CDIEventTypeLookup;
import org.jboss.errai.enterprise.client.cdi.EventProvider;
import org.jboss.errai.enterprise.client.cdi.InstanceProvider;
import org.jboss.errai.enterprise.client.cdi.api.CDI;
import org.jboss.errai.enterprise.client.jaxrs.JaxrsModuleBootstrapper;
import org.jboss.errai.ioc.client.api.Caller;
import org.jboss.errai.ioc.client.api.ContextualTypeProvider;
import org.jboss.errai.ioc.client.api.builtin.CallerProvider;
import org.jboss.errai.ioc.client.api.builtin.DisposerProvider;
import org.jboss.errai.ioc.client.api.builtin.IOCBeanManagerProvider;
import org.jboss.errai.ioc.client.api.builtin.InitBallotProvider;
import org.jboss.errai.ioc.client.api.builtin.MessageBusProvider;
import org.jboss.errai.ioc.client.api.builtin.RequestDispatcherProvider;
import org.jboss.errai.ioc.client.api.builtin.RootPanelProvider;
import org.jboss.errai.ioc.client.api.builtin.SenderProvider;
import org.jboss.errai.ioc.client.container.CreationalCallback;
import org.jboss.errai.ioc.client.container.CreationalContext;
import org.jboss.errai.ioc.client.container.IOCBeanManager;
import org.jboss.errai.ioc.client.container.InitializationCallback;
import org.jboss.errai.jpa.client.local.ErraiEntityManagerProvider;
import org.jboss.errai.ui.client.widget.ListWidgetProvider;
import org.jboss.errai.ui.nav.client.local.Navigation;
import org.jboss.errai.ui.nav.client.local.PageTransitionProvider;
import org.jboss.errai.ui.nav.client.local.TransitionTo;

public class BootstrapperImpl implements Bootstrapper {
  {
    new CDI().initLookupTable(CDIEventTypeLookup.get());
    new DataBindingModuleBootstrapper().run();
    new JaxrsModuleBootstrapper().run();
  }
  private final Default _1998831462Default_25754845 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Any _1998831462Any_9515457 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Annotation[] arrayOf_19635043Annotation_10422192 = new Annotation[] { _1998831462Default_25754845, _1998831462Any_9515457 };
  private final New _1998831462New_1319753162 = new New() {
    public Class annotationType() {
      return New.class;
    }
    public String toString() {
      return "@javax.enterprise.inject.New(value=interface javax.enterprise.inject.New)";
    }
    public Class value() {
      return New.class;
    }
  };
  private final Annotation[] arrayOf_19635043Annotation_21912330 = new Annotation[] { _1998831462New_1319753162, _1998831462Any_9515457 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<Header> inj2313_Header_creational = new CreationalCallback<Header>() {
    public Header getInstance(final CreationalContext context) {
      final Header inj2312_Header = new Header();
      context.addBean(context.getBeanReference(Header.class, arrayOf_19635043Annotation_10422192), inj2312_Header);
      return inj2312_Header;
    }
  };
  private final Header inj2312_Header = inj2313_Header_creational.getInstance(context);
  private final CreationalCallback<DataBinderProvider> inj2314_DataBinderProvider_creational = new CreationalCallback<DataBinderProvider>() {
    public DataBinderProvider getInstance(final CreationalContext context) {
      final DataBinderProvider inj2299_DataBinderProvider = new DataBinderProvider();
      context.addBean(context.getBeanReference(DataBinderProvider.class, arrayOf_19635043Annotation_10422192), inj2299_DataBinderProvider);
      return inj2299_DataBinderProvider;
    }
  };
  private final DataBinderProvider inj2299_DataBinderProvider = inj2314_DataBinderProvider_creational.getInstance(context);
  private final CreationalCallback<RequestDispatcherProvider> inj2315_RequestDispatcherProvider_creational = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      final RequestDispatcherProvider inj2291_RequestDispatcherProvider = new RequestDispatcherProvider();
      context.addBean(context.getBeanReference(RequestDispatcherProvider.class, arrayOf_19635043Annotation_10422192), inj2291_RequestDispatcherProvider);
      return inj2291_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj2291_RequestDispatcherProvider = inj2315_RequestDispatcherProvider_creational.getInstance(context);
  private final CreationalCallback<InstanceProvider> inj2316_InstanceProvider_creational = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      final InstanceProvider inj2301_InstanceProvider = new InstanceProvider();
      context.addBean(context.getBeanReference(InstanceProvider.class, arrayOf_19635043Annotation_10422192), inj2301_InstanceProvider);
      return inj2301_InstanceProvider;
    }
  };
  private final InstanceProvider inj2301_InstanceProvider = inj2316_InstanceProvider_creational.getInstance(context);
  private InitializationCallback<Navigation> init_inj2317_Navigation = new InitializationCallback<Navigation>() {
    public void init(final Navigation obj) {
      _136504311_init(obj);
    }
  };
  private final CreationalCallback<Navigation> inj2318_Navigation_creational = new CreationalCallback<Navigation>() {
    public Navigation getInstance(final CreationalContext context) {
      final Navigation inj2317_Navigation = new Navigation();
      context.addBean(context.getBeanReference(Navigation.class, arrayOf_19635043Annotation_10422192), inj2317_Navigation);
      context.addInitializationCallback(inj2317_Navigation, init_inj2317_Navigation);
      return inj2317_Navigation;
    }
  };
  private final Navigation inj2317_Navigation = inj2318_Navigation_creational.getInstance(context);
  private InitializationCallback<App> init_inj2319_App = new InitializationCallback<App>() {
    public void init(final App obj) {
      obj.initApp();
    }
  };
  private final CreationalCallback<App> inj2320_App_creational = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      final App inj2319_App = new App();
      context.addBean(context.getBeanReference(App.class, arrayOf_19635043Annotation_10422192), inj2319_App);
      _1128239541_navigation(inj2319_App, inj2317_Navigation);
      context.addInitializationCallback(inj2319_App, init_inj2319_App);
      return inj2319_App;
    }
  };
  private final App inj2319_App = inj2320_App_creational.getInstance(context);
  private final CreationalCallback<BootstrapErrorRenderer> inj2323_BootstrapErrorRenderer_creational = new CreationalCallback<BootstrapErrorRenderer>() {
    public BootstrapErrorRenderer getInstance(final CreationalContext context) {
      final BootstrapErrorRenderer inj671_BootstrapErrorRenderer = new BootstrapErrorRenderer();
      context.addBean(context.getBeanReference(BootstrapErrorRenderer.class, arrayOf_19635043Annotation_10422192), inj671_BootstrapErrorRenderer);
      return inj671_BootstrapErrorRenderer;
    }
  };
  private final CreationalCallback<Val> inj2322_Val_creational = new CreationalCallback<Val>() {
    public Val getInstance(final CreationalContext context) {
      final Val inj2321_Val = new Val();
      context.addBean(context.getBeanReference(Val.class, arrayOf_19635043Annotation_10422192), inj2321_Val);
      final CreationalCallback<Validator> var2 = new CreationalCallback<Validator>() {
        public Validator getInstance(CreationalContext pContext) {
          Validator var2 = inj2319_App.create();
          context.addBean(context.getBeanReference(Validator.class, arrayOf_19635043Annotation_10422192), var2);
          return var2;
        }
      };
      _821285910_validator(inj2321_Val, context.getSingletonInstanceOrNew(injContext, var2, Validator.class, arrayOf_19635043Annotation_10422192));
      _821285910_errorRenderer(inj2321_Val, inj2323_BootstrapErrorRenderer_creational.getInstance(context));
      return inj2321_Val;
    }
  };
  private final Val inj2321_Val = inj2322_Val_creational.getInstance(context);
  private final CreationalCallback<PageTransitionProvider> inj2324_PageTransitionProvider_creational = new CreationalCallback<PageTransitionProvider>() {
    public PageTransitionProvider getInstance(final CreationalContext context) {
      final PageTransitionProvider inj2287_PageTransitionProvider = new PageTransitionProvider();
      context.addBean(context.getBeanReference(PageTransitionProvider.class, arrayOf_19635043Annotation_10422192), inj2287_PageTransitionProvider);
      _395577528_navigation(inj2287_PageTransitionProvider, inj2317_Navigation);
      return inj2287_PageTransitionProvider;
    }
  };
  private final PageTransitionProvider inj2287_PageTransitionProvider = inj2324_PageTransitionProvider_creational.getInstance(context);
  private final CreationalCallback<CallerProvider> inj2325_CallerProvider_creational = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      final CallerProvider inj2307_CallerProvider = new CallerProvider();
      context.addBean(context.getBeanReference(CallerProvider.class, arrayOf_19635043Annotation_10422192), inj2307_CallerProvider);
      return inj2307_CallerProvider;
    }
  };
  private final CallerProvider inj2307_CallerProvider = inj2325_CallerProvider_creational.getInstance(context);
  private InitializationCallback<Login> init_inj2326_Login = new InitializationCallback<Login>() {
    public void init(final Login obj) {
      obj.onPostconstruct();
    }
  };
  private final CreationalCallback<Login> inj2327_Login_creational = new CreationalCallback<Login>() {
    public Login getInstance(final CreationalContext context) {
      final Login inj2326_Login = new Login();
      context.addBean(context.getBeanReference(Login.class, arrayOf_19635043Annotation_10422192), inj2326_Login);
      _1312330669_usersRessourcescaller(inj2326_Login, inj2307_CallerProvider.provide(new Class[] { UsersResosurces.class }, null));
      _1312330669_dashBordPageTransition(inj2326_Login, inj2287_PageTransitionProvider.provide(new Class[] { DashBordPage.class }, null));
      _1312330669_userBinder(inj2326_Login, inj2299_DataBinderProvider.provide(new Class[] { Users.class }, null));
      _1312330669_val(inj2326_Login, inj2321_Val);
      context.addInitializationCallback(inj2326_Login, init_inj2326_Login);
      return inj2326_Login;
    }
  };
  private InitializationCallback<LoginPage> init_inj859_LoginPage = new InitializationCallback<LoginPage>() {
    public void init(final LoginPage obj) {
      _1623866944_init(obj);
    }
  };
  private final CreationalCallback<LoginPage> inj2328_LoginPage_creational = new CreationalCallback<LoginPage>() {
    public LoginPage getInstance(final CreationalContext context) {
      final LoginPage inj859_LoginPage = new LoginPage();
      context.addBean(context.getBeanReference(LoginPage.class, arrayOf_19635043Annotation_21912330), inj859_LoginPage);
      _1623866944_login(inj859_LoginPage, inj2327_Login_creational.getInstance(context));
      context.addInitializationCallback(inj859_LoginPage, init_inj859_LoginPage);
      return inj859_LoginPage;
    }
  };
  private final CreationalCallback<EventProvider> inj2329_EventProvider_creational = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      final EventProvider inj2305_EventProvider = new EventProvider();
      context.addBean(context.getBeanReference(EventProvider.class, arrayOf_19635043Annotation_10422192), inj2305_EventProvider);
      return inj2305_EventProvider;
    }
  };
  private final EventProvider inj2305_EventProvider = inj2329_EventProvider_creational.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj2330_MessageBusProvider_creational = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      final MessageBusProvider inj2289_MessageBusProvider = new MessageBusProvider();
      context.addBean(context.getBeanReference(MessageBusProvider.class, arrayOf_19635043Annotation_10422192), inj2289_MessageBusProvider);
      return inj2289_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj2289_MessageBusProvider = inj2330_MessageBusProvider_creational.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj2331_IOCBeanManagerProvider_creational = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj2295_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, arrayOf_19635043Annotation_10422192), inj2295_IOCBeanManagerProvider);
      return inj2295_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj2295_IOCBeanManagerProvider = inj2331_IOCBeanManagerProvider_creational.getInstance(context);
  private final CreationalCallback<LoginController> inj2333_LoginController_creational = new CreationalCallback<LoginController>() {
    public LoginController getInstance(final CreationalContext context) {
      final LoginController inj2332_LoginController = new LoginController();
      context.addBean(context.getBeanReference(LoginController.class, arrayOf_19635043Annotation_10422192), inj2332_LoginController);
      return inj2332_LoginController;
    }
  };
  private final LoginController inj2332_LoginController = inj2333_LoginController_creational.getInstance(context);
  private final CreationalCallback<Dashboard> inj2335_Dashboard_creational = new CreationalCallback<Dashboard>() {
    public Dashboard getInstance(final CreationalContext context) {
      final Dashboard inj2334_Dashboard = new Dashboard();
      context.addBean(context.getBeanReference(Dashboard.class, arrayOf_19635043Annotation_10422192), inj2334_Dashboard);
      return inj2334_Dashboard;
    }
  };
  private final Dashboard inj2334_Dashboard = inj2335_Dashboard_creational.getInstance(context);
  private InitializationCallback<DashBordPage> init_inj2336_DashBordPage = new InitializationCallback<DashBordPage>() {
    public void init(final DashBordPage obj) {
      _$1235384712_init(obj);
    }
  };
  private final CreationalCallback<DashBordPage> inj2337_DashBordPage_creational = new CreationalCallback<DashBordPage>() {
    public DashBordPage getInstance(final CreationalContext context) {
      final DashBordPage inj2336_DashBordPage = new DashBordPage();
      context.addBean(context.getBeanReference(DashBordPage.class, arrayOf_19635043Annotation_10422192), inj2336_DashBordPage);
      _$1235384712_dashboard(inj2336_DashBordPage, inj2334_Dashboard);
      context.addInitializationCallback(inj2336_DashBordPage, init_inj2336_DashBordPage);
      return inj2336_DashBordPage;
    }
  };
  private final DashBordPage inj2336_DashBordPage = inj2337_DashBordPage_creational.getInstance(context);
  private final CreationalCallback<SenderProvider> inj2338_SenderProvider_creational = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      final SenderProvider inj2311_SenderProvider = new SenderProvider();
      context.addBean(context.getBeanReference(SenderProvider.class, arrayOf_19635043Annotation_10422192), inj2311_SenderProvider);
      return inj2311_SenderProvider;
    }
  };
  private final SenderProvider inj2311_SenderProvider = inj2338_SenderProvider_creational.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj2339_InitBallotProvider_creational = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      final InitBallotProvider inj2303_InitBallotProvider = new InitBallotProvider();
      context.addBean(context.getBeanReference(InitBallotProvider.class, arrayOf_19635043Annotation_10422192), inj2303_InitBallotProvider);
      return inj2303_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj2303_InitBallotProvider = inj2339_InitBallotProvider_creational.getInstance(context);
  private final CreationalCallback<SideBar> inj2341_SideBar_creational = new CreationalCallback<SideBar>() {
    public SideBar getInstance(final CreationalContext context) {
      final SideBar inj2340_SideBar = new SideBar();
      context.addBean(context.getBeanReference(SideBar.class, arrayOf_19635043Annotation_10422192), inj2340_SideBar);
      return inj2340_SideBar;
    }
  };
  private final SideBar inj2340_SideBar = inj2341_SideBar_creational.getInstance(context);
  private final CreationalCallback<Footer> inj2343_Footer_creational = new CreationalCallback<Footer>() {
    public Footer getInstance(final CreationalContext context) {
      final Footer inj2342_Footer = new Footer();
      context.addBean(context.getBeanReference(Footer.class, arrayOf_19635043Annotation_10422192), inj2342_Footer);
      return inj2342_Footer;
    }
  };
  private final Footer inj2342_Footer = inj2343_Footer_creational.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj2344_RootPanelProvider_creational = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      final RootPanelProvider inj2293_RootPanelProvider = new RootPanelProvider();
      context.addBean(context.getBeanReference(RootPanelProvider.class, arrayOf_19635043Annotation_10422192), inj2293_RootPanelProvider);
      return inj2293_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj2293_RootPanelProvider = inj2344_RootPanelProvider_creational.getInstance(context);
  private final CreationalCallback<MainToolBar> inj2346_MainToolBar_creational = new CreationalCallback<MainToolBar>() {
    public MainToolBar getInstance(final CreationalContext context) {
      final MainToolBar inj2345_MainToolBar = new MainToolBar();
      context.addBean(context.getBeanReference(MainToolBar.class, arrayOf_19635043Annotation_10422192), inj2345_MainToolBar);
      return inj2345_MainToolBar;
    }
  };
  private final MainToolBar inj2345_MainToolBar = inj2346_MainToolBar_creational.getInstance(context);
  private final CreationalCallback<ListWidgetProvider> inj2347_ListWidgetProvider_creational = new CreationalCallback<ListWidgetProvider>() {
    public ListWidgetProvider getInstance(final CreationalContext context) {
      final ListWidgetProvider inj2285_ListWidgetProvider = new ListWidgetProvider();
      context.addBean(context.getBeanReference(ListWidgetProvider.class, arrayOf_19635043Annotation_10422192), inj2285_ListWidgetProvider);
      return inj2285_ListWidgetProvider;
    }
  };
  private final ListWidgetProvider inj2285_ListWidgetProvider = inj2347_ListWidgetProvider_creational.getInstance(context);
  private final CreationalCallback<ErraiEntityManagerProvider> inj2348_ErraiEntityManagerProvider_creational = new CreationalCallback<ErraiEntityManagerProvider>() {
    public ErraiEntityManagerProvider getInstance(final CreationalContext context) {
      final ErraiEntityManagerProvider inj2297_ErraiEntityManagerProvider = new ErraiEntityManagerProvider();
      context.addBean(context.getBeanReference(ErraiEntityManagerProvider.class, arrayOf_19635043Annotation_10422192), inj2297_ErraiEntityManagerProvider);
      return inj2297_ErraiEntityManagerProvider;
    }
  };
  private final ErraiEntityManagerProvider inj2297_ErraiEntityManagerProvider = inj2348_ErraiEntityManagerProvider_creational.getInstance(context);
  private final CreationalCallback<DisposerProvider> inj2349_DisposerProvider_creational = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      final DisposerProvider inj2309_DisposerProvider = new DisposerProvider();
      context.addBean(context.getBeanReference(DisposerProvider.class, arrayOf_19635043Annotation_10422192), inj2309_DisposerProvider);
      _$1300398733_beanManager(inj2309_DisposerProvider, inj2295_IOCBeanManagerProvider.get());
      return inj2309_DisposerProvider;
    }
  };
  private final DisposerProvider inj2309_DisposerProvider = inj2349_DisposerProvider_creational.getInstance(context);
  private void declareBeans_0() {
    injContext.addBean(Header.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Composite.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsRenderable.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Widget.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(EventListener.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasAttachHandlers.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasHandlers.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsWidget.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(UIObject.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasVisibility.class, Header.class, inj2313_Header_creational, inj2312_Header, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(DataBinderProvider.class, DataBinderProvider.class, inj2314_DataBinderProvider_creational, inj2299_DataBinderProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ContextualTypeProvider.class, DataBinderProvider.class, inj2314_DataBinderProvider_creational, inj2299_DataBinderProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj2315_RequestDispatcherProvider_creational, inj2291_RequestDispatcherProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj2315_RequestDispatcherProvider_creational, inj2291_RequestDispatcherProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj2316_InstanceProvider_creational, inj2301_InstanceProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj2316_InstanceProvider_creational, inj2301_InstanceProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Navigation.class, Navigation.class, inj2318_Navigation_creational, inj2317_Navigation, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(App.class, App.class, inj2320_App_creational, inj2319_App, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(BootstrapErrorRenderer.class, BootstrapErrorRenderer.class, inj2323_BootstrapErrorRenderer_creational, null, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ErrorRenderer.class, BootstrapErrorRenderer.class, inj2323_BootstrapErrorRenderer_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Val.class, Val.class, inj2322_Val_creational, inj2321_Val, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(PageTransitionProvider.class, PageTransitionProvider.class, inj2324_PageTransitionProvider_creational, inj2287_PageTransitionProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ContextualTypeProvider.class, PageTransitionProvider.class, inj2324_PageTransitionProvider_creational, inj2287_PageTransitionProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj2325_CallerProvider_creational, inj2307_CallerProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj2325_CallerProvider_creational, inj2307_CallerProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Login.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Composite.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsRenderable.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Widget.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(EventListener.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasAttachHandlers.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasHandlers.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsWidget.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(UIObject.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasVisibility.class, Login.class, inj2327_Login_creational, null, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(LoginPage.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, true);
    injContext.addBean(PublicTemplate.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(Composite.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(IsRenderable.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(Widget.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(EventListener.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(HasAttachHandlers.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(HasHandlers.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(IsWidget.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(UIObject.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(HasVisibility.class, LoginPage.class, inj2328_LoginPage_creational, null, arrayOf_19635043Annotation_21912330, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj2329_EventProvider_creational, inj2305_EventProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj2329_EventProvider_creational, inj2305_EventProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj2330_MessageBusProvider_creational, inj2289_MessageBusProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj2330_MessageBusProvider_creational, inj2289_MessageBusProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj2331_IOCBeanManagerProvider_creational, inj2295_IOCBeanManagerProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj2331_IOCBeanManagerProvider_creational, inj2295_IOCBeanManagerProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(LoginController.class, LoginController.class, inj2333_LoginController_creational, inj2332_LoginController, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Dashboard.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Composite.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsRenderable.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Widget.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(EventListener.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasAttachHandlers.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasHandlers.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsWidget.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(UIObject.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasVisibility.class, Dashboard.class, inj2335_Dashboard_creational, inj2334_Dashboard, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(DashBordPage.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Template.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Composite.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsRenderable.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Widget.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(EventListener.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasAttachHandlers.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasHandlers.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsWidget.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(UIObject.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasVisibility.class, DashBordPage.class, inj2337_DashBordPage_creational, inj2336_DashBordPage, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj2338_SenderProvider_creational, inj2311_SenderProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj2338_SenderProvider_creational, inj2311_SenderProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj2339_InitBallotProvider_creational, inj2303_InitBallotProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj2339_InitBallotProvider_creational, inj2303_InitBallotProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(SideBar.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Composite.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsRenderable.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Widget.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(EventListener.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasAttachHandlers.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasHandlers.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsWidget.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(UIObject.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasVisibility.class, SideBar.class, inj2341_SideBar_creational, inj2340_SideBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Footer.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Composite.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsRenderable.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Widget.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(EventListener.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasAttachHandlers.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasHandlers.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsWidget.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(UIObject.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasVisibility.class, Footer.class, inj2343_Footer_creational, inj2342_Footer, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj2344_RootPanelProvider_creational, inj2293_RootPanelProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj2344_RootPanelProvider_creational, inj2293_RootPanelProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(MainToolBar.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Composite.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsRenderable.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(Widget.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(EventListener.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasAttachHandlers.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasHandlers.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(IsWidget.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(UIObject.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(HasVisibility.class, MainToolBar.class, inj2346_MainToolBar_creational, inj2345_MainToolBar, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(ListWidgetProvider.class, ListWidgetProvider.class, inj2347_ListWidgetProvider_creational, inj2285_ListWidgetProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ContextualTypeProvider.class, ListWidgetProvider.class, inj2347_ListWidgetProvider_creational, inj2285_ListWidgetProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(ErraiEntityManagerProvider.class, ErraiEntityManagerProvider.class, inj2348_ErraiEntityManagerProvider_creational, inj2297_ErraiEntityManagerProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(Provider.class, ErraiEntityManagerProvider.class, inj2348_ErraiEntityManagerProvider_creational, inj2297_ErraiEntityManagerProvider, arrayOf_19635043Annotation_10422192, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj2349_DisposerProvider_creational, inj2309_DisposerProvider, arrayOf_19635043Annotation_10422192, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj2349_DisposerProvider_creational, inj2309_DisposerProvider, arrayOf_19635043Annotation_10422192, null, false);
  }

  private native static void _821285910_errorRenderer(Val instance, ErrorRenderer value) /*-{
    instance.@cm.adorsys.gpao.support.Val::errorRenderer = value;
  }-*/;

  private native static void _$1300398733_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void _1128239541_navigation(App instance, Navigation value) /*-{
    instance.@cm.adorsys.gpao.App::navigation = value;
  }-*/;

  private native static void _395577528_navigation(PageTransitionProvider instance, Navigation value) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.PageTransitionProvider::navigation = value;
  }-*/;

  private native static void _1623866944_login(LoginPage instance, Login value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.LoginPage::login = value;
  }-*/;

  private native static void _1312330669_userBinder(Login instance, DataBinder value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.contents.Login::userBinder = value;
  }-*/;

  private native static void _1312330669_usersRessourcescaller(Login instance, Caller value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.contents.Login::usersRessourcescaller = value;
  }-*/;

  private native static void _1312330669_val(Login instance, Val value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.contents.Login::val = value;
  }-*/;

  private native static void _821285910_validator(Val instance, Validator value) /*-{
    instance.@cm.adorsys.gpao.support.Val::validator = value;
  }-*/;

  private native static void _1312330669_dashBordPageTransition(Login instance, TransitionTo value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.contents.Login::dashBordPageTransition = value;
  }-*/;

  private native static void _$1235384712_dashboard(DashBordPage instance, Dashboard value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.DashBordPage::dashboard = value;
  }-*/;

  public native static void _136504311_init(Navigation instance) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.Navigation::init()();
  }-*/;

  public native static void _1623866944_init(LoginPage instance) /*-{
    instance.@cm.adorsys.gpao.ui.pages.LoginPage::init()();
  }-*/;

  public native static void _$1235384712_init(DashBordPage instance) /*-{
    instance.@cm.adorsys.gpao.ui.pages.DashBordPage::init()();
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    final CreationalCallback<Validator> var1 = new CreationalCallback<Validator>() {
      public Validator getInstance(CreationalContext pContext) {
        Validator var1 = inj2319_App.create();
        context.addBean(context.getBeanReference(Validator.class, arrayOf_19635043Annotation_10422192), var1);
        return var1;
      }
    };
    declareBeans_0();
    return injContext;
  }
}