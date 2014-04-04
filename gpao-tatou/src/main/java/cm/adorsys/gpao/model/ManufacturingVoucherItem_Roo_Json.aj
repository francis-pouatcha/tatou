// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.ManufacturingVoucherItem;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect ManufacturingVoucherItem_Roo_Json {
    
    public String ManufacturingVoucherItem.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String ManufacturingVoucherItem.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static ManufacturingVoucherItem ManufacturingVoucherItem.fromJsonToManufacturingVoucherItem(String json) {
        return new JSONDeserializer<ManufacturingVoucherItem>()
        .use(null, ManufacturingVoucherItem.class).deserialize(json);
    }
    
    public static String ManufacturingVoucherItem.toJsonArray(Collection<ManufacturingVoucherItem> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String ManufacturingVoucherItem.toJsonArray(Collection<ManufacturingVoucherItem> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<ManufacturingVoucherItem> ManufacturingVoucherItem.fromJsonArrayToManufacturingVoucherItems(String json) {
        return new JSONDeserializer<List<ManufacturingVoucherItem>>()
        .use("values", ManufacturingVoucherItem.class).deserialize(json);
    }
    
}
