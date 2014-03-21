// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model.uimodels;

import cm.adorsys.gpao.model.uimodels.PartnerFinder;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect PartnerFinder_Roo_Json {
    
    public String PartnerFinder.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String PartnerFinder.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static PartnerFinder PartnerFinder.fromJsonToPartnerFinder(String json) {
        return new JSONDeserializer<PartnerFinder>()
        .use(null, PartnerFinder.class).deserialize(json);
    }
    
    public static String PartnerFinder.toJsonArray(Collection<PartnerFinder> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String PartnerFinder.toJsonArray(Collection<PartnerFinder> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<PartnerFinder> PartnerFinder.fromJsonArrayToPartnerFinders(String json) {
        return new JSONDeserializer<List<PartnerFinder>>()
        .use("values", PartnerFinder.class).deserialize(json);
    }
    
}
