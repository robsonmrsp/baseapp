package br.com.mr.app.json;

import java.io.Serializable;

public class JsonRole implements Serializable {
	private Long id;
	private String authority;
	private String description;

	public JsonRole() {
	}

	public JsonRole(Long id, String authority, String description) {
		super();
		this.id = id;
		this.authority = authority;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
