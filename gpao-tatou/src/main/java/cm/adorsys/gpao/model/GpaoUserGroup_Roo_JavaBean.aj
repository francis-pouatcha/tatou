// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.GpaoUserGroup;
import cm.adorsys.gpao.model.RoleNames;
import java.util.Set;

privileged aspect GpaoUserGroup_Roo_JavaBean {
    
    public String GpaoUserGroup.getName() {
        return this.name;
    }
    
    public void GpaoUserGroup.setName(String name) {
        this.name = name;
    }
    
    public String GpaoUserGroup.getDescription() {
        return this.description;
    }
    
    public void GpaoUserGroup.setDescription(String description) {
        this.description = description;
    }
    
    public Set<RoleNames> GpaoUserGroup.getRoleNames() {
        return this.roleNames;
    }
    
    public void GpaoUserGroup.setRoleNames(Set<RoleNames> roleNames) {
        this.roleNames = roleNames;
    }
    
}