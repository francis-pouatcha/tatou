// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Devise;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect Devise_Roo_Json {
    
    public String Devise.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String Devise.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static Devise Devise.fromJsonToDevise(String json) {
        return new JSONDeserializer<Devise>()
        .use(null, Devise.class).deserialize(json);
    }
    
    public static String Devise.toJsonArray(Collection<Devise> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String Devise.toJsonArray(Collection<Devise> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<Devise> Devise.fromJsonArrayToDevises(String json) {
        return new JSONDeserializer<List<Devise>>()
        .use("values", Devise.class).deserialize(json);
    }
    
}
