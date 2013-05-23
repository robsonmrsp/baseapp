package br.com.sints.app.security;

import br.com.sints.app.model.AppUser;

public interface UserContext {

	AppUser getCurrentUser();

	void setCurrentUser(AppUser user);
}
