package org.jboss.errai.ioc.client;

import cm.adorsys.gpao.App;
import cm.adorsys.gpao.api.model.UnitOfMesure;
import cm.adorsys.gpao.api.model.Users;
import cm.adorsys.gpao.api.ressources.UsersResosurces;
import cm.adorsys.gpao.controllers.LoginController;
import cm.adorsys.gpao.controllers.UnitOfMesureController;
import cm.adorsys.gpao.support.BootstrapErrorRenderer;
import cm.adorsys.gpao.support.ErrorRenderer;
import cm.adorsys.gpao.support.Val;
import cm.adorsys.gpao.ui.layouts.PublicTemplate;
import cm.adorsys.gpao.ui.layouts.Template;
import cm.adorsys.gpao.ui.pages.DashBordPage;
import cm.adorsys.gpao.ui.pages.LoginPage;
import cm.adorsys.gpao.ui.pages.ProductFamillyPage;
import cm.adorsys.gpao.ui.pages.UnitOfMesurePage;
import cm.adorsys.gpao.ui.pages.contents.Login;
import cm.adorsys.gpao.ui.pages.contents.UnitOfMeasureContent;
import cm.adorsys.gpao.ui.widgets.AdministrationSideBar;
import cm.adorsys.gpao.ui.widgets.BindableWidgets;
import cm.adorsys.gpao.ui.widgets.Dashboard;
import cm.adorsys.gpao.ui.widgets.DashbordSideBar;
import cm.adorsys.gpao.ui.widgets.Footer;
import cm.adorsys.gpao.ui.widgets.Header;
import cm.adorsys.gpao.ui.widgets.MainToolBar;
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
import org.jboss.errai.bus.client.api.ErrorCallback;
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
  private final Any _1998831462Any_15881768 = new Any() {
    public Class annotationType() {
      return Any.class;
    }
  };
  private final Default _1998831462Default_29274975 = new Default() {
    public Class annotationType() {
      return Default.class;
    }
  };
  private final Annotation[] arrayOf_19635043Annotation_27018141 = new Annotation[] { _1998831462Any_15881768, _1998831462Default_29274975 };
  private final New _1998831462New_1314893737 = new New() {
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
  private final Annotation[] arrayOf_19635043Annotation_5985487 = new Annotation[] { _1998831462Any_15881768, _1998831462New_1314893737 };
  private final BootstrapperInjectionContext injContext = new BootstrapperInjectionContext();
  private final CreationalContext context = injContext.getRootContext();
  private final CreationalCallback<Header> inj2281_Header_creational = new CreationalCallback<Header>() {
    public Header getInstance(final CreationalContext context) {
      final Header inj2280_Header = new Header();
      context.addBean(context.getBeanReference(Header.class, arrayOf_19635043Annotation_27018141), inj2280_Header);
      return inj2280_Header;
    }
  };
  private final Header inj2280_Header = inj2281_Header_creational.getInstance(context);
  private final CreationalCallback<DataBinderProvider> inj2282_DataBinderProvider_creational = new CreationalCallback<DataBinderProvider>() {
    public DataBinderProvider getInstance(final CreationalContext context) {
      final DataBinderProvider inj2267_DataBinderProvider = new DataBinderProvider();
      context.addBean(context.getBeanReference(DataBinderProvider.class, arrayOf_19635043Annotation_27018141), inj2267_DataBinderProvider);
      return inj2267_DataBinderProvider;
    }
  };
  private final DataBinderProvider inj2267_DataBinderProvider = inj2282_DataBinderProvider_creational.getInstance(context);
  private final CreationalCallback<RequestDispatcherProvider> inj2283_RequestDispatcherProvider_creational = new CreationalCallback<RequestDispatcherProvider>() {
    public RequestDispatcherProvider getInstance(final CreationalContext context) {
      final RequestDispatcherProvider inj2261_RequestDispatcherProvider = new RequestDispatcherProvider();
      context.addBean(context.getBeanReference(RequestDispatcherProvider.class, arrayOf_19635043Annotation_27018141), inj2261_RequestDispatcherProvider);
      return inj2261_RequestDispatcherProvider;
    }
  };
  private final RequestDispatcherProvider inj2261_RequestDispatcherProvider = inj2283_RequestDispatcherProvider_creational.getInstance(context);
  private final CreationalCallback<AdministrationSideBar> inj2285_AdministrationSideBar_creational = new CreationalCallback<AdministrationSideBar>() {
    public AdministrationSideBar getInstance(final CreationalContext context) {
      final AdministrationSideBar inj2284_AdministrationSideBar = new AdministrationSideBar();
      context.addBean(context.getBeanReference(AdministrationSideBar.class, arrayOf_19635043Annotation_27018141), inj2284_AdministrationSideBar);
      return inj2284_AdministrationSideBar;
    }
  };
  private final CreationalCallback<InstanceProvider> inj2286_InstanceProvider_creational = new CreationalCallback<InstanceProvider>() {
    public InstanceProvider getInstance(final CreationalContext context) {
      final InstanceProvider inj2269_InstanceProvider = new InstanceProvider();
      context.addBean(context.getBeanReference(InstanceProvider.class, arrayOf_19635043Annotation_27018141), inj2269_InstanceProvider);
      return inj2269_InstanceProvider;
    }
  };
  private final InstanceProvider inj2269_InstanceProvider = inj2286_InstanceProvider_creational.getInstance(context);
  private InitializationCallback<Navigation> init_inj2287_Navigation = new InitializationCallback<Navigation>() {
    public void init(final Navigation obj) {
      _136504311_init(obj);
    }
  };
  private final CreationalCallback<Navigation> inj2288_Navigation_creational = new CreationalCallback<Navigation>() {
    public Navigation getInstance(final CreationalContext context) {
      final Navigation inj2287_Navigation = new Navigation();
      context.addBean(context.getBeanReference(Navigation.class, arrayOf_19635043Annotation_27018141), inj2287_Navigation);
      context.addInitializationCallback(inj2287_Navigation, init_inj2287_Navigation);
      return inj2287_Navigation;
    }
  };
  private final Navigation inj2287_Navigation = inj2288_Navigation_creational.getInstance(context);
  private InitializationCallback<App> init_inj2289_App = new InitializationCallback<App>() {
    public void init(final App obj) {
      obj.initApp();
    }
  };
  private final CreationalCallback<App> inj2290_App_creational = new CreationalCallback<App>() {
    public App getInstance(final CreationalContext context) {
      final App inj2289_App = new App();
      context.addBean(context.getBeanReference(App.class, arrayOf_19635043Annotation_27018141), inj2289_App);
      _1128239541_navigation(inj2289_App, inj2287_Navigation);
      context.addInitializationCallback(inj2289_App, init_inj2289_App);
      return inj2289_App;
    }
  };
  private final App inj2289_App = inj2290_App_creational.getInstance(context);
  private final CreationalCallback<BootstrapErrorRenderer> inj2293_BootstrapErrorRenderer_creational = new CreationalCallback<BootstrapErrorRenderer>() {
    public BootstrapErrorRenderer getInstance(final CreationalContext context) {
      final BootstrapErrorRenderer inj640_BootstrapErrorRenderer = new BootstrapErrorRenderer();
      context.addBean(context.getBeanReference(BootstrapErrorRenderer.class, arrayOf_19635043Annotation_27018141), inj640_BootstrapErrorRenderer);
      return inj640_BootstrapErrorRenderer;
    }
  };
  private final CreationalCallback<Val> inj2292_Val_creational = new CreationalCallback<Val>() {
    public Val getInstance(final CreationalContext context) {
      final Val inj2291_Val = new Val();
      context.addBean(context.getBeanReference(Val.class, arrayOf_19635043Annotation_27018141), inj2291_Val);
      final CreationalCallback<Validator> var2 = new CreationalCallback<Validator>() {
        public Validator getInstance(CreationalContext pContext) {
          Validator var2 = inj2289_App.create();
          context.addBean(context.getBeanReference(Validator.class, arrayOf_19635043Annotation_27018141), var2);
          return var2;
        }
      };
      _821285910_validator(inj2291_Val, context.getSingletonInstanceOrNew(injContext, var2, Validator.class, arrayOf_19635043Annotation_27018141));
      _821285910_errorRenderer(inj2291_Val, inj2293_BootstrapErrorRenderer_creational.getInstance(context));
      return inj2291_Val;
    }
  };
  private final Val inj2291_Val = inj2292_Val_creational.getInstance(context);
  private final CreationalCallback<PageTransitionProvider> inj2294_PageTransitionProvider_creational = new CreationalCallback<PageTransitionProvider>() {
    public PageTransitionProvider getInstance(final CreationalContext context) {
      final PageTransitionProvider inj2257_PageTransitionProvider = new PageTransitionProvider();
      context.addBean(context.getBeanReference(PageTransitionProvider.class, arrayOf_19635043Annotation_27018141), inj2257_PageTransitionProvider);
      _395577528_navigation(inj2257_PageTransitionProvider, inj2287_Navigation);
      return inj2257_PageTransitionProvider;
    }
  };
  private final PageTransitionProvider inj2257_PageTransitionProvider = inj2294_PageTransitionProvider_creational.getInstance(context);
  private final CreationalCallback<CallerProvider> inj2295_CallerProvider_creational = new CreationalCallback<CallerProvider>() {
    public CallerProvider getInstance(final CreationalContext context) {
      final CallerProvider inj2275_CallerProvider = new CallerProvider();
      context.addBean(context.getBeanReference(CallerProvider.class, arrayOf_19635043Annotation_27018141), inj2275_CallerProvider);
      return inj2275_CallerProvider;
    }
  };
  private final CallerProvider inj2275_CallerProvider = inj2295_CallerProvider_creational.getInstance(context);
  private InitializationCallback<Login> init_inj2296_Login = new InitializationCallback<Login>() {
    public void init(final Login obj) {
      obj.onPostconstruct();
    }
  };
  private final CreationalCallback<Login> inj2297_Login_creational = new CreationalCallback<Login>() {
    public Login getInstance(final CreationalContext context) {
      final Login inj2296_Login = new Login();
      context.addBean(context.getBeanReference(Login.class, arrayOf_19635043Annotation_27018141), inj2296_Login);
      _1312330669_usersRessourcescaller(inj2296_Login, inj2275_CallerProvider.provide(new Class[] { UsersResosurces.class }, null));
      _1312330669_dashBordPageTransition(inj2296_Login, inj2257_PageTransitionProvider.provide(new Class[] { DashBordPage.class }, null));
      _1312330669_userBinder(inj2296_Login, inj2267_DataBinderProvider.provide(new Class[] { Users.class }, null));
      _1312330669_val(inj2296_Login, inj2291_Val);
      context.addInitializationCallback(inj2296_Login, init_inj2296_Login);
      return inj2296_Login;
    }
  };
  private InitializationCallback<LoginPage> init_inj830_LoginPage = new InitializationCallback<LoginPage>() {
    public void init(final LoginPage obj) {
      _1623866944_init(obj);
    }
  };
  private final CreationalCallback<LoginPage> inj2298_LoginPage_creational = new CreationalCallback<LoginPage>() {
    public LoginPage getInstance(final CreationalContext context) {
      final LoginPage inj830_LoginPage = new LoginPage();
      context.addBean(context.getBeanReference(LoginPage.class, arrayOf_19635043Annotation_27018141), inj830_LoginPage);
      _1623866944_login(inj830_LoginPage, inj2297_Login_creational.getInstance(context));
      context.addInitializationCallback(inj830_LoginPage, init_inj830_LoginPage);
      return inj830_LoginPage;
    }
  };
  private final CreationalCallback<EventProvider> inj2299_EventProvider_creational = new CreationalCallback<EventProvider>() {
    public EventProvider getInstance(final CreationalContext context) {
      final EventProvider inj2273_EventProvider = new EventProvider();
      context.addBean(context.getBeanReference(EventProvider.class, arrayOf_19635043Annotation_27018141), inj2273_EventProvider);
      return inj2273_EventProvider;
    }
  };
  private final EventProvider inj2273_EventProvider = inj2299_EventProvider_creational.getInstance(context);
  private InitializationCallback<UnitOfMeasureContent> init_inj2300_UnitOfMeasureContent = new InitializationCallback<UnitOfMeasureContent>() {
    public void init(final UnitOfMeasureContent obj) {
      obj.createUi();
    }
  };
  private final CreationalCallback<UnitOfMeasureContent> inj2301_UnitOfMeasureContent_creational = new CreationalCallback<UnitOfMeasureContent>() {
    public UnitOfMeasureContent getInstance(final CreationalContext context) {
      final UnitOfMeasureContent inj2300_UnitOfMeasureContent = new UnitOfMeasureContent();
      context.addBean(context.getBeanReference(UnitOfMeasureContent.class, arrayOf_19635043Annotation_27018141), inj2300_UnitOfMeasureContent);
      final Val var3 = inj2291_Val;
      inj2300_UnitOfMeasureContent.setVal(var3);
      final DataBinder<UnitOfMesure> var4 = inj2267_DataBinderProvider.provide(new Class[] { UnitOfMesure.class }, null);
      inj2300_UnitOfMeasureContent.setUnitOfMesureBinder(var4);
      context.addInitializationCallback(inj2300_UnitOfMeasureContent, init_inj2300_UnitOfMeasureContent);
      return inj2300_UnitOfMeasureContent;
    }
  };
  private final CreationalCallback<Dashboard> inj2303_Dashboard_creational = new CreationalCallback<Dashboard>() {
    public Dashboard getInstance(final CreationalContext context) {
      final Dashboard inj2302_Dashboard = new Dashboard();
      context.addBean(context.getBeanReference(Dashboard.class, arrayOf_19635043Annotation_27018141), inj2302_Dashboard);
      return inj2302_Dashboard;
    }
  };
  private InitializationCallback<ProductFamillyPage> init_inj2304_ProductFamillyPage = new InitializationCallback<ProductFamillyPage>() {
    public void init(final ProductFamillyPage obj) {
      _1255488922_init(obj);
    }
  };
  private final CreationalCallback<ProductFamillyPage> inj2305_ProductFamillyPage_creational = new CreationalCallback<ProductFamillyPage>() {
    public ProductFamillyPage getInstance(final CreationalContext context) {
      final ProductFamillyPage inj2304_ProductFamillyPage = new ProductFamillyPage();
      context.addBean(context.getBeanReference(ProductFamillyPage.class, arrayOf_19635043Annotation_27018141), inj2304_ProductFamillyPage);
      _1255488922_dashboard(inj2304_ProductFamillyPage, inj2303_Dashboard_creational.getInstance(context));
      _1255488922_liftmenu(inj2304_ProductFamillyPage, inj2285_AdministrationSideBar_creational.getInstance(context));
      context.addInitializationCallback(inj2304_ProductFamillyPage, init_inj2304_ProductFamillyPage);
      return inj2304_ProductFamillyPage;
    }
  };
  private final ProductFamillyPage inj2304_ProductFamillyPage = inj2305_ProductFamillyPage_creational.getInstance(context);
  private final CreationalCallback<MessageBusProvider> inj2306_MessageBusProvider_creational = new CreationalCallback<MessageBusProvider>() {
    public MessageBusProvider getInstance(final CreationalContext context) {
      final MessageBusProvider inj2259_MessageBusProvider = new MessageBusProvider();
      context.addBean(context.getBeanReference(MessageBusProvider.class, arrayOf_19635043Annotation_27018141), inj2259_MessageBusProvider);
      return inj2259_MessageBusProvider;
    }
  };
  private final MessageBusProvider inj2259_MessageBusProvider = inj2306_MessageBusProvider_creational.getInstance(context);
  private final CreationalCallback<IOCBeanManagerProvider> inj2307_IOCBeanManagerProvider_creational = new CreationalCallback<IOCBeanManagerProvider>() {
    public IOCBeanManagerProvider getInstance(final CreationalContext context) {
      final IOCBeanManagerProvider inj2265_IOCBeanManagerProvider = new IOCBeanManagerProvider();
      context.addBean(context.getBeanReference(IOCBeanManagerProvider.class, arrayOf_19635043Annotation_27018141), inj2265_IOCBeanManagerProvider);
      return inj2265_IOCBeanManagerProvider;
    }
  };
  private final IOCBeanManagerProvider inj2265_IOCBeanManagerProvider = inj2307_IOCBeanManagerProvider_creational.getInstance(context);
  private final CreationalCallback<DashbordSideBar> inj2309_DashbordSideBar_creational = new CreationalCallback<DashbordSideBar>() {
    public DashbordSideBar getInstance(final CreationalContext context) {
      final DashbordSideBar inj2308_DashbordSideBar = new DashbordSideBar();
      context.addBean(context.getBeanReference(DashbordSideBar.class, arrayOf_19635043Annotation_27018141), inj2308_DashbordSideBar);
      return inj2308_DashbordSideBar;
    }
  };
  private final DashbordSideBar inj2308_DashbordSideBar = inj2309_DashbordSideBar_creational.getInstance(context);
  private final CreationalCallback<ErrorCallback> inj2253_ErrorCallback_creational = new CreationalCallback<ErrorCallback>() {
    public ErrorCallback getInstance(CreationalContext pContext) {
      ErrorCallback var5 = inj2290_App_creational.getInstance(context).defaultErrorCallback();
      context.addBean(context.getBeanReference(ErrorCallback.class, arrayOf_19635043Annotation_5985487), var5);
      return var5;
    }
  };
  private final CreationalCallback<UnitOfMesureController> inj2311_UnitOfMesureController_creational = new CreationalCallback<UnitOfMesureController>() {
    public UnitOfMesureController getInstance(final CreationalContext context) {
      final UnitOfMesureController inj2310_UnitOfMesureController = new UnitOfMesureController();
      context.addBean(context.getBeanReference(UnitOfMesureController.class, arrayOf_19635043Annotation_27018141), inj2310_UnitOfMesureController);
      final ErrorCallback var6 = inj2289_App.defaultErrorCallback();
      inj2310_UnitOfMesureController.setErrorCallback(var6);
      return inj2310_UnitOfMesureController;
    }
  };
  private final UnitOfMesureController inj2310_UnitOfMesureController = inj2311_UnitOfMesureController_creational.getInstance(context);
  private final CreationalCallback<LoginController> inj2313_LoginController_creational = new CreationalCallback<LoginController>() {
    public LoginController getInstance(final CreationalContext context) {
      final LoginController inj2312_LoginController = new LoginController();
      context.addBean(context.getBeanReference(LoginController.class, arrayOf_19635043Annotation_27018141), inj2312_LoginController);
      return inj2312_LoginController;
    }
  };
  private final LoginController inj2312_LoginController = inj2313_LoginController_creational.getInstance(context);
  private InitializationCallback<UnitOfMesurePage> init_inj2314_UnitOfMesurePage = new InitializationCallback<UnitOfMesurePage>() {
    public void init(final UnitOfMesurePage obj) {
      _1667708175_init(obj);
    }
  };
  private final CreationalCallback<UnitOfMesurePage> inj2315_UnitOfMesurePage_creational = new CreationalCallback<UnitOfMesurePage>() {
    public UnitOfMesurePage getInstance(final CreationalContext context) {
      final UnitOfMesurePage inj2314_UnitOfMesurePage = new UnitOfMesurePage();
      context.addBean(context.getBeanReference(UnitOfMesurePage.class, arrayOf_19635043Annotation_27018141), inj2314_UnitOfMesurePage);
      _1667708175_unitOfMeasureContent(inj2314_UnitOfMesurePage, inj2301_UnitOfMeasureContent_creational.getInstance(context));
      _1667708175_liftmenu(inj2314_UnitOfMesurePage, inj2285_AdministrationSideBar_creational.getInstance(context));
      context.addInitializationCallback(inj2314_UnitOfMesurePage, init_inj2314_UnitOfMesurePage);
      return inj2314_UnitOfMesurePage;
    }
  };
  private final UnitOfMesurePage inj2314_UnitOfMesurePage = inj2315_UnitOfMesurePage_creational.getInstance(context);
  private InitializationCallback<DashBordPage> init_inj2316_DashBordPage = new InitializationCallback<DashBordPage>() {
    public void init(final DashBordPage obj) {
      _$1235384712_init(obj);
    }
  };
  private final CreationalCallback<DashBordPage> inj2317_DashBordPage_creational = new CreationalCallback<DashBordPage>() {
    public DashBordPage getInstance(final CreationalContext context) {
      final DashBordPage inj2316_DashBordPage = new DashBordPage();
      context.addBean(context.getBeanReference(DashBordPage.class, arrayOf_19635043Annotation_27018141), inj2316_DashBordPage);
      _$1235384712_dashboard(inj2316_DashBordPage, inj2303_Dashboard_creational.getInstance(context));
      _$1235384712_leftMenu(inj2316_DashBordPage, inj2308_DashbordSideBar);
      context.addInitializationCallback(inj2316_DashBordPage, init_inj2316_DashBordPage);
      return inj2316_DashBordPage;
    }
  };
  private final DashBordPage inj2316_DashBordPage = inj2317_DashBordPage_creational.getInstance(context);
  private final CreationalCallback<SenderProvider> inj2318_SenderProvider_creational = new CreationalCallback<SenderProvider>() {
    public SenderProvider getInstance(final CreationalContext context) {
      final SenderProvider inj2279_SenderProvider = new SenderProvider();
      context.addBean(context.getBeanReference(SenderProvider.class, arrayOf_19635043Annotation_27018141), inj2279_SenderProvider);
      return inj2279_SenderProvider;
    }
  };
  private final SenderProvider inj2279_SenderProvider = inj2318_SenderProvider_creational.getInstance(context);
  private final CreationalCallback<InitBallotProvider> inj2319_InitBallotProvider_creational = new CreationalCallback<InitBallotProvider>() {
    public InitBallotProvider getInstance(final CreationalContext context) {
      final InitBallotProvider inj2271_InitBallotProvider = new InitBallotProvider();
      context.addBean(context.getBeanReference(InitBallotProvider.class, arrayOf_19635043Annotation_27018141), inj2271_InitBallotProvider);
      return inj2271_InitBallotProvider;
    }
  };
  private final InitBallotProvider inj2271_InitBallotProvider = inj2319_InitBallotProvider_creational.getInstance(context);
  private final CreationalCallback<Footer> inj2321_Footer_creational = new CreationalCallback<Footer>() {
    public Footer getInstance(final CreationalContext context) {
      final Footer inj2320_Footer = new Footer();
      context.addBean(context.getBeanReference(Footer.class, arrayOf_19635043Annotation_27018141), inj2320_Footer);
      return inj2320_Footer;
    }
  };
  private final Footer inj2320_Footer = inj2321_Footer_creational.getInstance(context);
  private final CreationalCallback<RootPanelProvider> inj2322_RootPanelProvider_creational = new CreationalCallback<RootPanelProvider>() {
    public RootPanelProvider getInstance(final CreationalContext context) {
      final RootPanelProvider inj2263_RootPanelProvider = new RootPanelProvider();
      context.addBean(context.getBeanReference(RootPanelProvider.class, arrayOf_19635043Annotation_27018141), inj2263_RootPanelProvider);
      return inj2263_RootPanelProvider;
    }
  };
  private final RootPanelProvider inj2263_RootPanelProvider = inj2322_RootPanelProvider_creational.getInstance(context);
  private InitializationCallback<MainToolBar> init_inj2323_MainToolBar = new InitializationCallback<MainToolBar>() {
    public void init(final MainToolBar obj) {
      obj.init();
    }
  };
  private final CreationalCallback<MainToolBar> inj2324_MainToolBar_creational = new CreationalCallback<MainToolBar>() {
    public MainToolBar getInstance(final CreationalContext context) {
      final MainToolBar inj2323_MainToolBar = new MainToolBar();
      context.addBean(context.getBeanReference(MainToolBar.class, arrayOf_19635043Annotation_27018141), inj2323_MainToolBar);
      context.addInitializationCallback(inj2323_MainToolBar, init_inj2323_MainToolBar);
      return inj2323_MainToolBar;
    }
  };
  private final MainToolBar inj2323_MainToolBar = inj2324_MainToolBar_creational.getInstance(context);
  private final CreationalCallback<ListWidgetProvider> inj2325_ListWidgetProvider_creational = new CreationalCallback<ListWidgetProvider>() {
    public ListWidgetProvider getInstance(final CreationalContext context) {
      final ListWidgetProvider inj2255_ListWidgetProvider = new ListWidgetProvider();
      context.addBean(context.getBeanReference(ListWidgetProvider.class, arrayOf_19635043Annotation_27018141), inj2255_ListWidgetProvider);
      return inj2255_ListWidgetProvider;
    }
  };
  private final ListWidgetProvider inj2255_ListWidgetProvider = inj2325_ListWidgetProvider_creational.getInstance(context);
  private final CreationalCallback<DisposerProvider> inj2326_DisposerProvider_creational = new CreationalCallback<DisposerProvider>() {
    public DisposerProvider getInstance(final CreationalContext context) {
      final DisposerProvider inj2277_DisposerProvider = new DisposerProvider();
      context.addBean(context.getBeanReference(DisposerProvider.class, arrayOf_19635043Annotation_27018141), inj2277_DisposerProvider);
      _$1300398733_beanManager(inj2277_DisposerProvider, inj2265_IOCBeanManagerProvider.get());
      return inj2277_DisposerProvider;
    }
  };
  private final DisposerProvider inj2277_DisposerProvider = inj2326_DisposerProvider_creational.getInstance(context);
  private final CreationalCallback<Template> inj2329_Template_creational = new CreationalCallback<Template>() {
    public Template getInstance(final CreationalContext context) {
      final Template inj2328_Template = new Template();
      context.addBean(context.getBeanReference(Template.class, arrayOf_19635043Annotation_27018141), inj2328_Template);
      return inj2328_Template;
    }
  };
  private void declareBeans_0() {
    injContext.addBean(Header.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Composite.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, Header.class, inj2281_Header_creational, inj2280_Header, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(DataBinderProvider.class, DataBinderProvider.class, inj2282_DataBinderProvider_creational, inj2267_DataBinderProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ContextualTypeProvider.class, DataBinderProvider.class, inj2282_DataBinderProvider_creational, inj2267_DataBinderProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(RequestDispatcherProvider.class, RequestDispatcherProvider.class, inj2283_RequestDispatcherProvider_creational, inj2261_RequestDispatcherProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Provider.class, RequestDispatcherProvider.class, inj2283_RequestDispatcherProvider_creational, inj2261_RequestDispatcherProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(AdministrationSideBar.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Composite.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, AdministrationSideBar.class, inj2285_AdministrationSideBar_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(InstanceProvider.class, InstanceProvider.class, inj2286_InstanceProvider_creational, inj2269_InstanceProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ContextualTypeProvider.class, InstanceProvider.class, inj2286_InstanceProvider_creational, inj2269_InstanceProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Navigation.class, Navigation.class, inj2288_Navigation_creational, inj2287_Navigation, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(App.class, App.class, inj2290_App_creational, inj2289_App, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(BootstrapErrorRenderer.class, BootstrapErrorRenderer.class, inj2293_BootstrapErrorRenderer_creational, null, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ErrorRenderer.class, BootstrapErrorRenderer.class, inj2293_BootstrapErrorRenderer_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Val.class, Val.class, inj2292_Val_creational, inj2291_Val, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(PageTransitionProvider.class, PageTransitionProvider.class, inj2294_PageTransitionProvider_creational, inj2257_PageTransitionProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ContextualTypeProvider.class, PageTransitionProvider.class, inj2294_PageTransitionProvider_creational, inj2257_PageTransitionProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(CallerProvider.class, CallerProvider.class, inj2295_CallerProvider_creational, inj2275_CallerProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ContextualTypeProvider.class, CallerProvider.class, inj2295_CallerProvider_creational, inj2275_CallerProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Login.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Composite.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, Login.class, inj2297_Login_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(LoginPage.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(PublicTemplate.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Composite.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, LoginPage.class, inj2298_LoginPage_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventProvider.class, EventProvider.class, inj2299_EventProvider_creational, inj2273_EventProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ContextualTypeProvider.class, EventProvider.class, inj2299_EventProvider_creational, inj2273_EventProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UnitOfMeasureContent.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(BindableWidgets.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Composite.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, UnitOfMeasureContent.class, inj2301_UnitOfMeasureContent_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Dashboard.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Composite.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, Dashboard.class, inj2303_Dashboard_creational, null, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(ProductFamillyPage.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Template.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Composite.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, ProductFamillyPage.class, inj2305_ProductFamillyPage_creational, inj2304_ProductFamillyPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(MessageBusProvider.class, MessageBusProvider.class, inj2306_MessageBusProvider_creational, inj2259_MessageBusProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Provider.class, MessageBusProvider.class, inj2306_MessageBusProvider_creational, inj2259_MessageBusProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IOCBeanManagerProvider.class, IOCBeanManagerProvider.class, inj2307_IOCBeanManagerProvider_creational, inj2265_IOCBeanManagerProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Provider.class, IOCBeanManagerProvider.class, inj2307_IOCBeanManagerProvider_creational, inj2265_IOCBeanManagerProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(DashbordSideBar.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Composite.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, DashbordSideBar.class, inj2309_DashbordSideBar_creational, inj2308_DashbordSideBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(ErrorCallback.class, ErrorCallback.class, inj2253_ErrorCallback_creational, null, arrayOf_19635043Annotation_5985487, null, true);
    injContext.addBean(UnitOfMesureController.class, UnitOfMesureController.class, inj2311_UnitOfMesureController_creational, inj2310_UnitOfMesureController, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(LoginController.class, LoginController.class, inj2313_LoginController_creational, inj2312_LoginController, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(UnitOfMesurePage.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Template.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Composite.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, UnitOfMesurePage.class, inj2315_UnitOfMesurePage_creational, inj2314_UnitOfMesurePage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(DashBordPage.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Template.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Composite.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, DashBordPage.class, inj2317_DashBordPage_creational, inj2316_DashBordPage, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(SenderProvider.class, SenderProvider.class, inj2318_SenderProvider_creational, inj2279_SenderProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ContextualTypeProvider.class, SenderProvider.class, inj2318_SenderProvider_creational, inj2279_SenderProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(InitBallotProvider.class, InitBallotProvider.class, inj2319_InitBallotProvider_creational, inj2271_InitBallotProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ContextualTypeProvider.class, InitBallotProvider.class, inj2319_InitBallotProvider_creational, inj2271_InitBallotProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Footer.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Composite.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, Footer.class, inj2321_Footer_creational, inj2320_Footer, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(RootPanelProvider.class, RootPanelProvider.class, inj2322_RootPanelProvider_creational, inj2263_RootPanelProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Provider.class, RootPanelProvider.class, inj2322_RootPanelProvider_creational, inj2263_RootPanelProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(MainToolBar.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(Composite.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsRenderable.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Widget.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(EventListener.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasAttachHandlers.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasHandlers.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(IsWidget.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(UIObject.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(HasVisibility.class, MainToolBar.class, inj2324_MainToolBar_creational, inj2323_MainToolBar, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(ListWidgetProvider.class, ListWidgetProvider.class, inj2325_ListWidgetProvider_creational, inj2255_ListWidgetProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ContextualTypeProvider.class, ListWidgetProvider.class, inj2325_ListWidgetProvider_creational, inj2255_ListWidgetProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(DisposerProvider.class, DisposerProvider.class, inj2326_DisposerProvider_creational, inj2277_DisposerProvider, arrayOf_19635043Annotation_27018141, null, true);
    injContext.addBean(ContextualTypeProvider.class, DisposerProvider.class, inj2326_DisposerProvider_creational, inj2277_DisposerProvider, arrayOf_19635043Annotation_27018141, null, false);
    injContext.addBean(Template.class, Template.class, inj2329_Template_creational, null, arrayOf_19635043Annotation_27018141, null, true);
  }

  private native static void _1255488922_dashboard(ProductFamillyPage instance, Dashboard value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.ProductFamillyPage::dashboard = value;
  }-*/;

  private native static void _1667708175_unitOfMeasureContent(UnitOfMesurePage instance, UnitOfMeasureContent value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.UnitOfMesurePage::unitOfMeasureContent = value;
  }-*/;

  private native static void _821285910_errorRenderer(Val instance, ErrorRenderer value) /*-{
    instance.@cm.adorsys.gpao.support.Val::errorRenderer = value;
  }-*/;

  private native static void _$1300398733_beanManager(DisposerProvider instance, IOCBeanManager value) /*-{
    instance.@org.jboss.errai.ioc.client.api.builtin.DisposerProvider::beanManager = value;
  }-*/;

  private native static void _1667708175_liftmenu(UnitOfMesurePage instance, AdministrationSideBar value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.UnitOfMesurePage::liftmenu = value;
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

  private native static void _821285910_validator(Val instance, Validator value) /*-{
    instance.@cm.adorsys.gpao.support.Val::validator = value;
  }-*/;

  private native static void _1312330669_val(Login instance, Val value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.contents.Login::val = value;
  }-*/;

  private native static void _$1235384712_dashboard(DashBordPage instance, Dashboard value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.DashBordPage::dashboard = value;
  }-*/;

  private native static void _1128239541_navigation(App instance, Navigation value) /*-{
    instance.@cm.adorsys.gpao.App::navigation = value;
  }-*/;

  private native static void _$1235384712_leftMenu(DashBordPage instance, DashbordSideBar value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.DashBordPage::leftMenu = value;
  }-*/;

  private native static void _1255488922_liftmenu(ProductFamillyPage instance, AdministrationSideBar value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.ProductFamillyPage::liftmenu = value;
  }-*/;

  private native static void _1312330669_dashBordPageTransition(Login instance, TransitionTo value) /*-{
    instance.@cm.adorsys.gpao.ui.pages.contents.Login::dashBordPageTransition = value;
  }-*/;

  public native static void _136504311_init(Navigation instance) /*-{
    instance.@org.jboss.errai.ui.nav.client.local.Navigation::init()();
  }-*/;

  public native static void _1623866944_init(LoginPage instance) /*-{
    instance.@cm.adorsys.gpao.ui.pages.LoginPage::init()();
  }-*/;

  public native static void _1255488922_init(ProductFamillyPage instance) /*-{
    instance.@cm.adorsys.gpao.ui.pages.ProductFamillyPage::init()();
  }-*/;

  public native static void _1667708175_init(UnitOfMesurePage instance) /*-{
    instance.@cm.adorsys.gpao.ui.pages.UnitOfMesurePage::init()();
  }-*/;

  public native static void _$1235384712_init(DashBordPage instance) /*-{
    instance.@cm.adorsys.gpao.ui.pages.DashBordPage::init()();
  }-*/;

  // The main IOC bootstrap method.
  public BootstrapperInjectionContext bootstrapContainer() {
    final CreationalCallback<Validator> var1 = new CreationalCallback<Validator>() {
      public Validator getInstance(CreationalContext pContext) {
        Validator var1 = inj2289_App.create();
        context.addBean(context.getBeanReference(Validator.class, arrayOf_19635043Annotation_27018141), var1);
        return var1;
      }
    };
    declareBeans_0();
    return injContext;
  }
}