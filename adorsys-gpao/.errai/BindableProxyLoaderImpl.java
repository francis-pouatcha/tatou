package org.jboss.errai.databinding.client;

import cm.adorsys.gpao.api.model.Users;
import org.jboss.errai.databinding.client.api.InitialState;

public class BindableProxyLoaderImpl implements BindableProxyLoader {
  public void loadBindableProxies() {
    class cm_adorsys_gpao_api_model_UsersProxy extends Users implements BindableProxy {
      private BindableProxyAgent<Users> agent;
      public cm_adorsys_gpao_api_model_UsersProxy(InitialState initialState) {
        this(new Users(), initialState);
      }

      public cm_adorsys_gpao_api_model_UsersProxy(Users target, InitialState initialState) {
        agent = new BindableProxyAgent<Users>(this, target, initialState);
        agent.propertyTypes.put("id", new PropertyType(Long.class, false));
        agent.propertyTypes.put("userName", new PropertyType(String.class, false));
        agent.propertyTypes.put("password", new PropertyType(String.class, false));
        agent.propertyTypes.put("version", new PropertyType(Integer.class, false));
        agent.copyValues();
      }

      public BindableProxyAgent getProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public Users unwrap() {
        return agent.target;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cm_adorsys_gpao_api_model_UsersProxy) {
          obj = ((cm_adorsys_gpao_api_model_UsersProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public Long getId() {
        return agent.target.getId();
      }

      public void setId(Long id) {
        Long oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetAndFireEvent("id", oldValue, id);
      }

      public String getUserName() {
        return agent.target.getUserName();
      }

      public void setUserName(String userName) {
        String oldValue = agent.target.getUserName();
        agent.target.setUserName(userName);
        agent.updateWidgetAndFireEvent("userName", oldValue, userName);
      }

      public String getPassword() {
        return agent.target.getPassword();
      }

      public void setPassword(String password) {
        String oldValue = agent.target.getPassword();
        agent.target.setPassword(password);
        agent.updateWidgetAndFireEvent("password", oldValue, password);
      }

      public int getVersion() {
        return agent.target.getVersion();
      }

      public void setVersion(int version) {
        int oldValue = agent.target.getVersion();
        agent.target.setVersion(version);
        agent.updateWidgetAndFireEvent("version", oldValue, version);
      }

      public Object get(String property) {
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("userName")) {
          return getUserName();
        }
        if (property.equals("password")) {
          return getPassword();
        }
        if (property.equals("version")) {
          return getVersion();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("id")) {
          agent.target.setId((Long) value);
          return;
        }
        if (property.equals("userName")) {
          agent.target.setUserName((String) value);
          return;
        }
        if (property.equals("password")) {
          agent.target.setPassword((String) value);
          return;
        }
        if (property.equals("version")) {
          agent.target.setVersion((Integer) value);
          return;
        }
        throw new NonExistingPropertyException(property);
      }

      public void cloneFromUser(Users a0) {
        agent.target.cloneFromUser(a0);
        agent.updateWidgetsAndFireEvents();
      }
    }
    BindableProxyFactory.addBindableProxy(Users.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cm_adorsys_gpao_api_model_UsersProxy((Users) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cm_adorsys_gpao_api_model_UsersProxy(state);
      }
    });
  }
}