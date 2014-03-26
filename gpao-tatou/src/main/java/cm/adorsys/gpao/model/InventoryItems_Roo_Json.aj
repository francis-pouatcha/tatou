// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.InventoryItems;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect InventoryItems_Roo_Json {
    
    public String InventoryItems.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String InventoryItems.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static InventoryItems InventoryItems.fromJsonToInventoryItems(String json) {
        return new JSONDeserializer<InventoryItems>()
        .use(null, InventoryItems.class).deserialize(json);
    }
    
    public static String InventoryItems.toJsonArray(Collection<InventoryItems> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String InventoryItems.toJsonArray(Collection<InventoryItems> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<InventoryItems> InventoryItems.fromJsonArrayToInventoryItemses(String json) {
        return new JSONDeserializer<List<InventoryItems>>()
        .use("values", InventoryItems.class).deserialize(json);
    }
    
}