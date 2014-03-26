// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model.uimodels;

import cm.adorsys.gpao.model.uimodels.DeliveryFinder;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect DeliveryFinder_Roo_Json {
    
    public String DeliveryFinder.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String DeliveryFinder.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static DeliveryFinder DeliveryFinder.fromJsonToDeliveryFinder(String json) {
        return new JSONDeserializer<DeliveryFinder>()
        .use(null, DeliveryFinder.class).deserialize(json);
    }
    
    public static String DeliveryFinder.toJsonArray(Collection<DeliveryFinder> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String DeliveryFinder.toJsonArray(Collection<DeliveryFinder> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<DeliveryFinder> DeliveryFinder.fromJsonArrayToDeliveryFinders(String json) {
        return new JSONDeserializer<List<DeliveryFinder>>()
        .use("values", DeliveryFinder.class).deserialize(json);
    }
    
}