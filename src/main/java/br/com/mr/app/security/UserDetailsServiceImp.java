package br.com.mr.app.security;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import br.com.mr.app.model.AppUser;
import br.com.mr.app.persistence.DaoUser;

@Named
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {
	private final DaoUser userDao;

	@Inject
	public UserDetailsServiceImp(DaoUser userDao) {
		if (userDao == null) {
			throw new IllegalArgumentException("calendarUserDao cannot be null");
		}
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser appUser = userDao.findByUserName(username);
		if (appUser == null) {
			throw new UsernameNotFoundException("Invalid username...");
		}

		Collection<? extends GrantedAuthority> authorities = UserAuthorityUtils.createAuthorities(appUser);

		User user = new User(appUser.getUsername(), appUser.getPassword(), authorities);
		return user;
	}
}
