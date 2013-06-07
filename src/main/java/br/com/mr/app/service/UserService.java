package br.com.mr.app.service;

import java.util.List;

import br.com.mr.app.json.JsonUser;

public interface UserService {

	List<JsonUser> allUsers();

	JsonUser getUser(Long id);

	JsonUser save(JsonUser event);

	JsonUser update(JsonUser event);

	Boolean delete(Long id);

	JsonUser getLoguedUser();
}
