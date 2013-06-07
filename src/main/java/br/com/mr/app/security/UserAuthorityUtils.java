package br.com.mr.app.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.mr.app.model.AppUser;
import br.com.mr.app.model.Role;

//deverá mudar quando tivermos que implementar autorização.
public final class UserAuthorityUtils {

	public static  List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

	public static Collection<? extends GrantedAuthority> createAuthorities(AppUser calendarUser) {
		Role role = calendarUser.getRole();

		if(role != null) {
			String authority = role.getAuthority();
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
			AUTHORITIES.add(simpleGrantedAuthority);
		}
		return AUTHORITIES;
	}

	private UserAuthorityUtils() {

	}
}