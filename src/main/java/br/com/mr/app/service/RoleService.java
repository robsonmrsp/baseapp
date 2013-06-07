package br.com.mr.app.service;

import java.util.List;

import br.com.mr.app.json.JsonRole;

public interface RoleService {

	List<JsonRole> allRoles();

	JsonRole getRole(Long id);

	JsonRole save(JsonRole role);

	JsonRole update(JsonRole role);

	Boolean delete(Long id);

}
