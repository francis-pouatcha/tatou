// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.ProductIntrant;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect ProductIntrant_Roo_Json {
    
    public String ProductIntrant.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String ProductIntrant.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static ProductIntrant ProductIntrant.fromJsonToProductIntrant(String json) {
        return new JSONDeserializer<ProductIntrant>()
        .use(null, ProductIntrant.class).deserialize(json);
    }
    
    public static String ProductIntrant.toJsonArray(Collection<ProductIntrant> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String ProductIntrant.toJsonArray(Collection<ProductIntrant> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<ProductIntrant> ProductIntrant.fromJsonArrayToProductIntrants(String json) {
        return new JSONDeserializer<List<ProductIntrant>>()
        .use("values", ProductIntrant.class).deserialize(json);
    }
    
}