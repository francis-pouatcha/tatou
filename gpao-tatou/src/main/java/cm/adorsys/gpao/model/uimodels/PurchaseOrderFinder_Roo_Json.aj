// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model.uimodels;

import cm.adorsys.gpao.model.uimodels.PurchaseOrderFinder;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect PurchaseOrderFinder_Roo_Json {
    
    public String PurchaseOrderFinder.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String PurchaseOrderFinder.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static PurchaseOrderFinder PurchaseOrderFinder.fromJsonToPurchaseOrderFinder(String json) {
        return new JSONDeserializer<PurchaseOrderFinder>()
        .use(null, PurchaseOrderFinder.class).deserialize(json);
    }
    
    public static String PurchaseOrderFinder.toJsonArray(Collection<PurchaseOrderFinder> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String PurchaseOrderFinder.toJsonArray(Collection<PurchaseOrderFinder> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<PurchaseOrderFinder> PurchaseOrderFinder.fromJsonArrayToPurchaseOrderFinders(String json) {
        return new JSONDeserializer<List<PurchaseOrderFinder>>()
        .use("values", PurchaseOrderFinder.class).deserialize(json);
    }
    
}
