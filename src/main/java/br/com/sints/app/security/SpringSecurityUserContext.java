package br.com.sints.app.security;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import br.com.sints.app.model.AppUser;
import br.com.sints.app.persistence.DaoUser;

@Named
public class SpringSecurityUserContext implements UserContext {
	private Collection<? extends GrantedAuthority> credentials;

	@Inject
	DaoUser daoUser;

	@Override
	public AppUser getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}
		User user = (User) authentication.getPrincipal();
		if (user != null) {
			return daoUser.findByUserName(user.getUsername());
		}
		return null;
	}

	@Override
	public void setCurrentUser(AppUser user) {
		if (user == null) {
			throw new IllegalArgumentException("user cannot be null");
		}
		Collection<? extends GrantedAuthority> authorities = UserAuthorityUtils.createAuthorities(user);
		setCredentials(authorities);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private void setCredentials(Collection<? extends GrantedAuthority> credentials) {
		this.credentials = credentials;
	}

	public Collection<? extends GrantedAuthority> getUserCredentials() {
		return credentials;
	}
}
