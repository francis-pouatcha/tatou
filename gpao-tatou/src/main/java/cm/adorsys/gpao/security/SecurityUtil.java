package cm.adorsys.gpao.security ;

/**
 * Give different security utilities on a user.
 */
import java.util.HashSet;
import java.util.Set;

import javax.persistence.TypedQuery;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import cm.adorsys.gpao.model.GpaoUser;
import cm.adorsys.gpao.model.GpaoUserGroup;
import cm.adorsys.gpao.model.RoleNames;

@Service
public class SecurityUtil {
	public static UserDetails getUserDetails(){
		SecurityContext context = SecurityContextHolder.getContext();
		if(context==null) return null;
		Authentication authentication = context.getAuthentication();
		if(authentication==null) return null;
		Object principal = authentication.getPrincipal();
		if(principal==null) return null;
		if (principal instanceof UserDetails) {
			return ((UserDetails)principal);
		} else {
			return null;
		}
	}

	public static String getUserName(){
		UserDetails userDetails = getUserDetails();
		if(userDetails!=null) return userDetails.getUsername();
		return "system";
	}

	public static GpaoUser getGpaoUser(){
		UserDetails userDetails = getUserDetails();
		if(userDetails==null) return new GpaoUser();
		String username = userDetails.getUsername();
		TypedQuery<GpaoUser> query = GpaoUser.findGpaoUsersByUserNameEquals(username) ;
		return query.getSingleResult();
	}

	public static Set<RoleNames> getRoleFromGpoaUserGroups(Set<GpaoUserGroup> gpaoUserGroups){
		HashSet<RoleNames> rolenames = new HashSet<RoleNames>();
		if(gpaoUserGroups==null ) throw new IllegalArgumentException("gpaoUserGroups collection is required !") ;
		for (GpaoUserGroup gpaoUserGroup : gpaoUserGroups) {
			for (RoleNames roleName : gpaoUserGroup.getRoleNames()) {
				rolenames.add(roleName);
			}
		}
		return rolenames;
	}




}
