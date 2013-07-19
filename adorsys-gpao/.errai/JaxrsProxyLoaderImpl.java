package org.jboss.errai.enterprise.client.jaxrs;

import cm.adorsys.gpao.api.model.UdmGroup;
import cm.adorsys.gpao.api.model.UnitOfMesure;
import cm.adorsys.gpao.api.model.Users;
import cm.adorsys.gpao.api.ressources.UdmGroupRessources;
import cm.adorsys.gpao.api.ressources.UnitOfMesureRessources;
import cm.adorsys.gpao.api.ressources.UsersResosurces;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.URL;
import java.util.List;
import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.bus.client.framework.ProxyProvider;
import org.jboss.errai.bus.client.framework.RemoteServiceProxyFactory;

public class JaxrsProxyLoaderImpl implements JaxrsProxyLoader {
  public void loadProxies() {
    class cm_adorsys_gpao_api_ressources_UsersResosurcesImpl extends AbstractJaxrsProxy implements UsersResosurces {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      public RemoteCallback getRemoteCallback() {
        return remoteCallback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public ErrorCallback getErrorCallback() {
        return errorCallback;
      }

      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public void create(final Users a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("users/create");
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        sendRequest(requestBuilder, MarshallingWrapper.toJSON(a0), new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return null;
          }
        });
      }

      public List list() {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("users/list");
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        sendRequest(requestBuilder, null, new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return MarshallingWrapper.fromJSON(response, List.class, Users.class);
          }
        });
        return null;
      }

      public Boolean login(final String a0, final String a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("users/login/{userName}/{password}".replace("{userName}", URL.encodePathSegment(a0 == null ? "" : a0)).replace("{password}", URL.encodePathSegment(a1 == null ? "" : a1)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        sendRequest(requestBuilder, null, new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return MarshallingWrapper.fromJSON(response, Boolean.class, null);
          }
        });
        return false;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(UsersResosurces.class, new ProxyProvider() {
      public Object getProxy() {
        return new cm_adorsys_gpao_api_ressources_UsersResosurcesImpl();
      }
    });
    class cm_adorsys_gpao_api_ressources_UdmGroupRessourcesImpl extends AbstractJaxrsProxy implements UdmGroupRessources {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      public RemoteCallback getRemoteCallback() {
        return remoteCallback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public ErrorCallback getErrorCallback() {
        return errorCallback;
      }

      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public UnitOfMesure save(final UdmGroup a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("unit-group/create");
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        sendRequest(requestBuilder, MarshallingWrapper.toJSON(a0), new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return MarshallingWrapper.fromJSON(response, UnitOfMesure.class, null);
          }
        });
        return null;
      }

      public List list() {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("unit-group/list");
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        sendRequest(requestBuilder, null, new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return MarshallingWrapper.fromJSON(response, List.class, UnitOfMesure.class);
          }
        });
        return null;
      }

      public List findByName(final String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("unit-group/findByName/{name}".replace("{name}", URL.encodePathSegment(a0 == null ? "" : a0)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        sendRequest(requestBuilder, null, new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return MarshallingWrapper.fromJSON(response, List.class, UnitOfMesure.class);
          }
        });
        return null;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(UdmGroupRessources.class, new ProxyProvider() {
      public Object getProxy() {
        return new cm_adorsys_gpao_api_ressources_UdmGroupRessourcesImpl();
      }
    });
    class cm_adorsys_gpao_api_ressources_UnitOfMesureRessourcesImpl extends AbstractJaxrsProxy implements UnitOfMesureRessources {
      private RemoteCallback remoteCallback;
      private ErrorCallback errorCallback;
      public RemoteCallback getRemoteCallback() {
        return remoteCallback;
      }

      public void setRemoteCallback(RemoteCallback callback) {
        remoteCallback = callback;
      }

      public ErrorCallback getErrorCallback() {
        return errorCallback;
      }

      public void setErrorCallback(ErrorCallback callback) {
        errorCallback = callback;
      }

      public UnitOfMesure save(final UnitOfMesure a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("unit-of-mesure/create");
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.POST, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        requestBuilder.setHeader("Content-Type", "application/json");
        sendRequest(requestBuilder, MarshallingWrapper.toJSON(a0), new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return MarshallingWrapper.fromJSON(response, UnitOfMesure.class, null);
          }
        });
        return null;
      }

      public List list() {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("unit-of-mesure/list");
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        sendRequest(requestBuilder, null, new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return MarshallingWrapper.fromJSON(response, List.class, UnitOfMesure.class);
          }
        });
        return null;
      }

      public List findByName(final String a0) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("unit-of-mesure/findByName/{name}".replace("{name}", URL.encodePathSegment(a0 == null ? "" : a0)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        sendRequest(requestBuilder, null, new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return MarshallingWrapper.fromJSON(response, List.class, UnitOfMesure.class);
          }
        });
        return null;
      }

      public List findByNameAndUdmGroup(final String a0, final UdmGroup a1) {
        StringBuilder url = new StringBuilder(getBaseUrl());
        url.append("unit-of-mesure/findByName/{name}".replace("{name}", URL.encodePathSegment(a0 == null ? "" : a0)));
        RequestBuilder requestBuilder = new RequestBuilder(RequestBuilder.GET, url.toString());
        requestBuilder.setHeader("Accept", "application/json");
        sendRequest(requestBuilder, MarshallingWrapper.toJSON(a1), new ResponseDemarshallingCallback() {
          public Object demarshallResponse(String response) {
            return MarshallingWrapper.fromJSON(response, List.class, UnitOfMesure.class);
          }
        });
        return null;
      }
    }
    RemoteServiceProxyFactory.addRemoteProxy(UnitOfMesureRessources.class, new ProxyProvider() {
      public Object getProxy() {
        return new cm_adorsys_gpao_api_ressources_UnitOfMesureRessourcesImpl();
      }
    });
  }
}