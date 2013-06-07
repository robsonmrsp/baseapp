package br.com.mr.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.com.mr.app.json.JsonUser;
import br.com.mr.app.model.AppUser;
import br.com.mr.app.persistence.DaoUser;
import br.com.mr.app.security.SpringSecurityUserContext;
import br.com.mr.app.utils.Parser;

@Named
@Transactional
public class UserServiceImp implements UserService {
	@Inject
	DaoUser daoUser;

	@Inject
	private SpringSecurityUserContext context;

	@Override
	public List<JsonUser> allUsers() {
		List<JsonUser> users = new ArrayList<JsonUser>();
		List<AppUser> allUsers = daoUser.getAll();
		for (AppUser user : allUsers) {
			users.add(Parser.toJson(user));
		}
		return users;
	}

	@Override
	public JsonUser update(JsonUser user) {
		return Parser.toJson(daoUser.save(Parser.toEntity(user)));
	}

	@Override
	public JsonUser getUser(Long id) {
		return Parser.toJson(daoUser.find(id));
	}

	@Override
	public JsonUser save(JsonUser user) {
		return Parser.toJson(daoUser.save(Parser.toEntity(user)));
	}

	@Override
	public Boolean delete(Long id) {
		return daoUser.delete(id);
	}

	@Override
	public JsonUser getLoguedUser() {
		return Parser.toJson(context.getCurrentUser());
	}
}
