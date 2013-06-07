package br.com.mr.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import br.com.mr.app.json.JsonRole;
import br.com.mr.app.model.Role;
import br.com.mr.app.persistence.DaoRole;
import br.com.mr.app.utils.Parser;

@Named
@Transactional
public class RoleServiceImp implements RoleService {
	@Inject
	DaoRole daoRole;

	@Override
	public List<JsonRole> allRoles() {
		List<JsonRole> roles = new ArrayList<JsonRole>();
		List<Role> allRoles = daoRole.getAll();
		for (Role role : allRoles) {
			roles.add(Parser.toJson(role));
		}
		return roles;
	}

	@Override
	public JsonRole update(JsonRole role) {
		return Parser.toJson(daoRole.save(Parser.toEntity(role)));
	}

	@Override
	public JsonRole getRole(Long id) {
		return Parser.toJson(daoRole.find(id));
	}

	@Override
	public JsonRole save(JsonRole role) {
		return Parser.toJson(daoRole.save(Parser.toEntity(role)));
	}

	@Override
	public Boolean delete(Long id) {
		return daoRole.delete(id);
	}
}
