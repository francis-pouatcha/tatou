package org.jboss.errai.ui.nav.client.local.spi;

import cm.adorsys.gpao.ui.pages.DashBordPage;
import cm.adorsys.gpao.ui.pages.LoginPage;
import org.jboss.errai.ui.nav.client.local.HistoryToken;

public class GeneratedNavigationGraph extends NavigationGraph {
  public GeneratedNavigationGraph() {
    pagesByName.put("DashBordPage", new PageNode<DashBordPage>() {
      public String name() {
        return "DashBordPage";
      }

      public Class contentType() {
        return DashBordPage.class;
      }

      public DashBordPage content() {
        return bm.lookupBean(DashBordPage.class).getInstance();
      }

      public void pageHiding(DashBordPage widget) {

      }

      public void pageHidden(DashBordPage widget) {

      }

      public void pageShowing(DashBordPage widget, HistoryToken state) {

      }

      public void pageShown(DashBordPage widget, HistoryToken state) {

      }
    });
    final PageNode defaultPage = new PageNode<LoginPage>() {
      public String name() {
        return "LoginPage";
      }

      public Class contentType() {
        return LoginPage.class;
      }

      public LoginPage content() {
        return bm.lookupBean(LoginPage.class).getInstance();
      }

      public void pageHiding(LoginPage widget) {

      }

      public void pageHidden(LoginPage widget) {

      }

      public void pageShowing(LoginPage widget, HistoryToken state) {

      }

      public void pageShown(LoginPage widget, HistoryToken state) {

      }
    };
    pagesByName.put("", defaultPage);
    pagesByName.put("LoginPage", defaultPage);
  }

}