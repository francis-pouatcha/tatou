package cm.adorsys.gpao.services;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import cm.adorsys.gpao.model.Company;
import cm.adorsys.gpao.model.Devise;
import cm.adorsys.gpao.model.GpaoUser;
import cm.adorsys.gpao.model.GpaoUserGroup;
import cm.adorsys.gpao.model.Location;
import cm.adorsys.gpao.model.PartnerGroup;
import cm.adorsys.gpao.model.ProductSubFamily;
import cm.adorsys.gpao.model.Taxe;
import cm.adorsys.gpao.model.UnitOfMesures;

@Service
@Transactional
public class ApplicationInitFilter extends OncePerRequestFilter {
	boolean done=false;
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {
		if(!done){
			Taxe.init();
			Devise.init();
			UnitOfMesures.init();
			ProductSubFamily.init();
			Location.init();
			Company.init();
			GpaoUserGroup.init();
			GpaoUser.init();
			PartnerGroup.init();
			done=true;
		}
		filterChain.doFilter(request, response);

	}

}
