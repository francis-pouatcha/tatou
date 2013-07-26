// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cm.adorsys.gpao.model;

import cm.adorsys.gpao.model.UdmGroup;
import cm.adorsys.gpao.model.UnitOfMesures;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect UnitOfMesures_Roo_Finder {
    
    public static TypedQuery<UnitOfMesures> UnitOfMesures.findUnitOfMesuresesByNameLikeAndUnitGroup(String name, UdmGroup unitGroup) {
        if (name == null || name.length() == 0) throw new IllegalArgumentException("The name argument is required");
        name = name.replace('*', '%');
        if (name.charAt(0) != '%') {
            name = "%" + name;
        }
        if (name.charAt(name.length() - 1) != '%') {
            name = name + "%";
        }
        if (unitGroup == null) throw new IllegalArgumentException("The unitGroup argument is required");
        EntityManager em = UnitOfMesures.entityManager();
        TypedQuery<UnitOfMesures> q = em.createQuery("SELECT o FROM UnitOfMesures AS o WHERE LOWER(o.name) LIKE LOWER(:name)  AND o.unitGroup = :unitGroup", UnitOfMesures.class);
        q.setParameter("name", name);
        q.setParameter("unitGroup", unitGroup);
        return q;
    }
    
}
