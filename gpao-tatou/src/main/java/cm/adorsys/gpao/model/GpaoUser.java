package cm.adorsys.gpao.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import cm.adorsys.gpao.security.SecurityUtil;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS")
public class GpaoUser extends GpaoBaseEntity {
	private String userNumber;

	@Enumerated
	private Gender gender;

	@NotNull
	@Column(unique = true)
	private String userName;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	private String fullName;

	private String password;

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<GpaoUserGroup> gpaoUserGroups = new HashSet<GpaoUserGroup>();

	private String phoneNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date accountExpiration = DateUtils.addYears(new Date(), 50);

	@Value("false")
	private boolean disableLogin;

	@Value("false")
	private boolean accountLocked;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
	private Date credentialExpiration =  DateUtils.addYears(new Date(), 50);

	@Transient
	public Set<RoleNames> roleNames = new HashSet<RoleNames>();

	@Transient
	private MultipartFile userImage;

	private String userImagePath;

	public GpaoUser() {
		// TODO Auto-generated constructor stub
	}

	public GpaoUser(String userName, String firstName, String lastName) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public static void init(){
		if(GpaoUser.countGpaoUsers()<= 0){
			GpaoUser gpaoUser = new GpaoUser("tatou","gpao-admin","tatou");
			gpaoUser.setCompany(Company.findCompany(new Long(1)));
			gpaoUser.getGpaoUserGroups().add(GpaoUserGroup.findGpaoUserGroup(new Long(1)));
			gpaoUser.changePassword("test123");
			gpaoUser.merge();
		}
	}

	@PrePersist
	public void prePersist(){
		changePassword("test123");
	}

	@PostLoad
	public void postLoad(){
		roleNames = SecurityUtil.getRoleFromGpoaUserGroups(getGpaoUserGroups());
	}
	public void makeFullName() {
		fullName = getDisplayName();
	}

	public boolean hasAnyRole(Collection<RoleNames> roleNames) {
		Set<RoleNames> orig = getRoleNames();
		for (RoleNames roleName : roleNames) {
			if (orig.contains(roleName)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasRole(RoleNames roleName) {
		Set<RoleNames> orig = getRoleNames();
		if (orig.contains(roleName)) {
			return true;
		}

		return false;
	}
	@Override
	public String toString() {
		return getDisplayName();
	}

	public String getDisplayName() {
		return gender.getSalutation() + " " + getFirstName() + " " + getLastName();
	}

	public void changePassword(String newPassword) {
		this.password = encodePassword(newPassword);
	}

	public static final String PASSWORD_SALT = "ace6b4f53";

	private String adresse;

	private String email;

	@ManyToOne
	private Company company;

	private String encodePassword(String input) {
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		md5PasswordEncoder.setEncodeHashAsBase64(false);
		return md5PasswordEncoder.encodePassword(input, PASSWORD_SALT);
	}

	public boolean checkExistingPasword(String input) {
		return StringUtils.equals(encodePassword(input), password);
	}

	public Set<GpaoUserGroup> getGpaoUserGroups() {
		return gpaoUserGroups;
	}

	public void setGpaoUserGroups(Set<GpaoUserGroup> gpaoUserGroups) {
		this.gpaoUserGroups = gpaoUserGroups;
	}

	public static TypedQuery<GpaoUser> findGpaoUsersByIdUpperThan(Long id) {
		EntityManager em = GpaoUser.entityManager();
		TypedQuery<GpaoUser> q = em.createQuery("SELECT o FROM GpaoUser AS o WHERE  o.id > :id ORDER BY o.id ", GpaoUser.class);
		q.setParameter("id", id);
		return q;
	}

	public static TypedQuery<GpaoUser> findGpaoUsersByIdLowerThan(Long id) {
		EntityManager em = GpaoUser.entityManager();
		TypedQuery<GpaoUser> q = em.createQuery("SELECT o FROM GpaoUser AS o WHERE  o.id < :id ORDER BY o.id DESC ", GpaoUser.class);
		q.setParameter("id", id);
		return q;
	}

	// finders

	public static TypedQuery<GpaoUser> findGpaoUsersByUserNameEquals(String userName) {
		if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
		EntityManager em = GpaoUser.entityManager();
		TypedQuery<GpaoUser> q = null;
		q = em.createQuery("SELECT o FROM GpaoUser AS o WHERE o.userName = :userName ", GpaoUser.class);
		q.setParameter("userName", userName);
		return q;
	}

	public static TypedQuery<GpaoUser> findGpaoUsersByUserNameLike(String userName) {
		if (userName == null || userName.length() == 0) throw new IllegalArgumentException("The userName argument is required");
		userName = userName.replace('*', '%');
		if (userName.charAt(0) != '%') {
			userName = "%" + userName;
		}
		if (userName.charAt(userName.length() - 1) != '%') {
			userName = userName + "%";
		}
		EntityManager em = GpaoUser.entityManager();
		TypedQuery<GpaoUser> q = em.createQuery("SELECT o FROM GpaoUser AS o WHERE LOWER(o.userName) LIKE LOWER(:userName)  ORDER BY  o.userName ASC", GpaoUser.class);
		q.setParameter("userName", userName);
		return q;
	}
}
