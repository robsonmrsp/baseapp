package br.com.mr.app.json;

import java.io.Serializable;

public class JsonUser implements Serializable {
	private Long id;
	private String username;
	private String name;
	private Long roleId;
	private String password;
	private String photo;
	private JsonRole role;

	public JsonUser(Long id, String username, String name, String password, String photo, JsonRole role) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.photo = photo;
		this.setRole(role);
	}

	public JsonUser(Long id, String username, String name, String password, String photo) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.photo = photo;
	}

	public JsonUser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public JsonRole getRole() {
		return role;
	}

	public void setRole(JsonRole role) {
		this.role = role;
	}
}
