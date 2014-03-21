// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model.uimodels;

import cm.adorsys.gpao.model.uimodels.PasswordReset;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect PasswordReset_Roo_Json {
    
    public String PasswordReset.toJson() {
        return new JSONSerializer()
        .exclude("*.class").serialize(this);
    }
    
    public String PasswordReset.toJson(String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(this);
    }
    
    public static PasswordReset PasswordReset.fromJsonToPasswordReset(String json) {
        return new JSONDeserializer<PasswordReset>()
        .use(null, PasswordReset.class).deserialize(json);
    }
    
    public static String PasswordReset.toJsonArray(Collection<PasswordReset> collection) {
        return new JSONSerializer()
        .exclude("*.class").serialize(collection);
    }
    
    public static String PasswordReset.toJsonArray(Collection<PasswordReset> collection, String[] fields) {
        return new JSONSerializer()
        .include(fields).exclude("*.class").serialize(collection);
    }
    
    public static Collection<PasswordReset> PasswordReset.fromJsonArrayToPasswordResets(String json) {
        return new JSONDeserializer<List<PasswordReset>>()
        .use("values", PasswordReset.class).deserialize(json);
    }
    
}
