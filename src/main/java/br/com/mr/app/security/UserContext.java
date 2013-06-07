package br.com.mr.app.security;

import br.com.mr.app.model.AppUser;

public interface UserContext {

	AppUser getCurrentUser();

	void setCurrentUser(AppUser user);
}
