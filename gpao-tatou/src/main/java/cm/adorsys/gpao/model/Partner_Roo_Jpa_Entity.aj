// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.Partner;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

privileged aspect Partner_Roo_Jpa_Entity {
    
    declare @type: Partner: @Entity;
    
    declare @type: Partner: @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS);
    
}
