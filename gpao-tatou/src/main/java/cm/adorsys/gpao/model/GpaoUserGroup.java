package cm.adorsys.gpao.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.TypedQuery;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class GpaoUserGroup {

	@NotNull
	private String name;

	private String description;

	@ElementCollection(fetch = FetchType.LAZY)
	private Set<RoleNames> roleNames = new HashSet<RoleNames>();

	public GpaoUserGroup() {
		// TODO Auto-generated constructor stub
	}
	public GpaoUserGroup(String name) {
		super();
		this.name = name;
	}
	public static void init(){
		if(GpaoUserGroup.countGpaoUserGroups() <= 0){
			GpaoUserGroup gpaoUserGroup = new GpaoUserGroup("ADMINISTRATOR");
			gpaoUserGroup.setDescription("group administrateur !");
			gpaoUserGroup.setRoleNames(new HashSet<RoleNames>(Arrays.asList(RoleNames.values())));
			gpaoUserGroup.persist();
		}
	}

	public static TypedQuery<GpaoUserGroup> findGpaoUserGroupsByIdUpperThan(Long id) {
		EntityManager em = GpaoUserGroup.entityManager();
		TypedQuery<GpaoUserGroup> q = em.createQuery("SELECT o FROM GpaoUserGroup AS o WHERE  o.id > :id ORDER BY o.id ", GpaoUserGroup.class);
		q.setParameter("id", id);
		return q;
	}

	public static TypedQuery<GpaoUserGroup> findGpaoUserGroupsByIdLowerThan(Long id) {
		EntityManager em = GpaoUserGroup.entityManager();
		TypedQuery<GpaoUserGroup> q = em.createQuery("SELECT o FROM GpaoUserGroup AS o WHERE  o.id < :id ORDER BY o.id DESC ", GpaoUserGroup.class);
		q.setParameter("id", id);
		return q;
	}
}
