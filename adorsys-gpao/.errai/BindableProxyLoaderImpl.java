package org.jboss.errai.databinding.client;

import cm.adorsys.gpao.api.model.UdmGroup;
import cm.adorsys.gpao.api.model.UnitOfMesure;
import cm.adorsys.gpao.api.model.Users;
import java.math.BigDecimal;
import org.jboss.errai.databinding.client.api.InitialState;

public class BindableProxyLoaderImpl implements BindableProxyLoader {
  public void loadBindableProxies() {
    class cm_adorsys_gpao_api_model_UnitOfMesureProxy extends UnitOfMesure implements BindableProxy {
      private BindableProxyAgent<UnitOfMesure> agent;
      public cm_adorsys_gpao_api_model_UnitOfMesureProxy(InitialState initialState) {
        this(new UnitOfMesure(), initialState);
      }

      public cm_adorsys_gpao_api_model_UnitOfMesureProxy(UnitOfMesure target, InitialState initialState) {
        agent = new BindableProxyAgent<UnitOfMesure>(this, target, initialState);
        agent.propertyTypes.put("id", new PropertyType(Long.class, false));
        agent.propertyTypes.put("enabled", new PropertyType(Boolean.class, false));
        agent.propertyTypes.put("isRefUdm", new PropertyType(Boolean.class, false));
        agent.propertyTypes.put("ratio", new PropertyType(BigDecimal.class, false));
        agent.propertyTypes.put("unitGroup", new PropertyType(UdmGroup.class, true));
        agent.propertyTypes.put("name", new PropertyType(String.class, false));
        agent.propertyTypes.put("version", new PropertyType(Integer.class, false));
        agent.copyValues();
      }

      public BindableProxyAgent getProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public UnitOfMesure unwrap() {
        return agent.target;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cm_adorsys_gpao_api_model_UnitOfMesureProxy) {
          obj = ((cm_adorsys_gpao_api_model_UnitOfMesureProxy) obj).unwrap();
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

      public Boolean getEnabled() {
        return agent.target.getEnabled();
      }

      public void setEnabled(Boolean enabled) {
        Boolean oldValue = agent.target.getEnabled();
        agent.target.setEnabled(enabled);
        agent.updateWidgetAndFireEvent("enabled", oldValue, enabled);
      }

      public Boolean getIsRefUdm() {
        return agent.target.getIsRefUdm();
      }

      public void setIsRefUdm(Boolean isRefUdm) {
        Boolean oldValue = agent.target.getIsRefUdm();
        agent.target.setIsRefUdm(isRefUdm);
        agent.updateWidgetAndFireEvent("isRefUdm", oldValue, isRefUdm);
      }

      public BigDecimal getRatio() {
        return agent.target.getRatio();
      }

      public void setRatio(BigDecimal ratio) {
        BigDecimal oldValue = agent.target.getRatio();
        agent.target.setRatio(ratio);
        agent.updateWidgetAndFireEvent("ratio", oldValue, ratio);
      }

      public UdmGroup getUnitGroup() {
        return agent.target.getUnitGroup();
      }

      public void setUnitGroup(UdmGroup unitGroup) {
        if (agent.binders.containsKey("unitGroup")) {
          unitGroup = (UdmGroup) agent.binders.get("unitGroup").setModel(unitGroup);
        }
        UdmGroup oldValue = agent.target.getUnitGroup();
        agent.target.setUnitGroup(unitGroup);
        agent.updateWidgetAndFireEvent("unitGroup", oldValue, unitGroup);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetAndFireEvent("name", oldValue, name);
      }

      public Integer getVersion() {
        return agent.target.getVersion();
      }

      public void setVersion(Integer version) {
        Integer oldValue = agent.target.getVersion();
        agent.target.setVersion(version);
        agent.updateWidgetAndFireEvent("version", oldValue, version);
      }

      public Object get(String property) {
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("enabled")) {
          return getEnabled();
        }
        if (property.equals("isRefUdm")) {
          return getIsRefUdm();
        }
        if (property.equals("ratio")) {
          return getRatio();
        }
        if (property.equals("unitGroup")) {
          return getUnitGroup();
        }
        if (property.equals("name")) {
          return getName();
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
        if (property.equals("enabled")) {
          agent.target.setEnabled((Boolean) value);
          return;
        }
        if (property.equals("isRefUdm")) {
          agent.target.setIsRefUdm((Boolean) value);
          return;
        }
        if (property.equals("ratio")) {
          agent.target.setRatio((BigDecimal) value);
          return;
        }
        if (property.equals("unitGroup")) {
          agent.target.setUnitGroup((UdmGroup) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("version")) {
          agent.target.setVersion((Integer) value);
          return;
        }
        throw new NonExistingPropertyException(property);
      }

      public void prePersist() {
        agent.target.prePersist();
        agent.updateWidgetsAndFireEvents();
      }

      public void preUpdate() {
        agent.target.preUpdate();
        agent.updateWidgetsAndFireEvents();
      }
    }
    BindableProxyFactory.addBindableProxy(UnitOfMesure.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cm_adorsys_gpao_api_model_UnitOfMesureProxy((UnitOfMesure) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cm_adorsys_gpao_api_model_UnitOfMesureProxy(state);
      }
    });
    class cm_adorsys_gpao_api_model_UdmGroupProxy extends UdmGroup implements BindableProxy {
      private BindableProxyAgent<UdmGroup> agent;
      public cm_adorsys_gpao_api_model_UdmGroupProxy(InitialState initialState) {
        this(new UdmGroup(), initialState);
      }

      public cm_adorsys_gpao_api_model_UdmGroupProxy(UdmGroup target, InitialState initialState) {
        agent = new BindableProxyAgent<UdmGroup>(this, target, initialState);
        agent.propertyTypes.put("id", new PropertyType(Long.class, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false));
        agent.propertyTypes.put("version", new PropertyType(Integer.class, false));
        agent.copyValues();
      }

      public BindableProxyAgent getProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public UdmGroup unwrap() {
        return agent.target;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cm_adorsys_gpao_api_model_UdmGroupProxy) {
          obj = ((cm_adorsys_gpao_api_model_UdmGroupProxy) obj).unwrap();
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

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetAndFireEvent("name", oldValue, name);
      }

      public Integer getVersion() {
        return agent.target.getVersion();
      }

      public void setVersion(Integer version) {
        Integer oldValue = agent.target.getVersion();
        agent.target.setVersion(version);
        agent.updateWidgetAndFireEvent("version", oldValue, version);
      }

      public Object get(String property) {
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("name")) {
          return getName();
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
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("version")) {
          agent.target.setVersion((Integer) value);
          return;
        }
        throw new NonExistingPropertyException(property);
      }
    }
    BindableProxyFactory.addBindableProxy(UdmGroup.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cm_adorsys_gpao_api_model_UdmGroupProxy((UdmGroup) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cm_adorsys_gpao_api_model_UdmGroupProxy(state);
      }
    });
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