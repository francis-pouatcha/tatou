package cm.adorsys.gpao.security ;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cm.adorsys.gpao.model.GpaoUser;
import cm.adorsys.gpao.model.RoleNames;
/**
 * @author clovisgakam
 * This class is a custom implementation of spring it's UserDetailsService.
 */

@Service
@Transactional
public class GpaoUserDetailsService implements UserDetailsService {


	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		try {
			if(StringUtils.isBlank(username)){
				throw new UsernameNotFoundException("Estate user with email not found");
			}
			TypedQuery<GpaoUser> typedQuery = GpaoUser.findGpaoUsersByUserNameEquals(username);
			List<GpaoUser> resultList = typedQuery.getResultList();
			GpaoUser  user  = null ;
			if (!resultList.isEmpty()) user = resultList.iterator().next();
			if ( user == null)
				throw new UsernameNotFoundException("Estate user with email not found");
			return createUserDetails(user);
		} finally {

		}
	}

	
	public static UserDetails createUserDetails(GpaoUser  user)
			throws UsernameNotFoundException, DataAccessException 
			{
		String password = user.getPassword();
		boolean enabled = !user.isDisableLogin();
		boolean accountNonExpired = true ;
		if (user.getAccountExpiration()!=null) {
			accountNonExpired = new Date().before(user.getAccountExpiration());	
		}
		boolean credentialsNonExpired = true ;
		if (user.getCredentialExpiration()!= null) {
			credentialsNonExpired = new Date().before(user.getCredentialExpiration());
		}
		boolean accountNonLocked =!user.isAccountLocked();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Set<RoleNames> roles = user.getRoleNames();
		for (RoleNames roleName : roles) {
			@SuppressWarnings("deprecation")
			GrantedAuthorityImpl ga = new GrantedAuthorityImpl(
					roleName.name());
			authorities.add(ga);
		}

		UserDetails userDetails = new User(user.getUserName(), password, enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked,authorities);
		return userDetails;
			}
}
