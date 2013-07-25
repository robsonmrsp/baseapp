package br.com.mr.app.json;

import java.io.Serializable;
import java.util.List;

public class JsonBusService implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String code;
	private String description;
	private List<JsonBusStop> bustops;

	private String[] cordnates;

	public JsonBusService(Long id, String name, String code, String description, List<JsonBusStop> bustops, String[] cordnates) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.bustops = bustops;
		this.cordnates = cordnates;
	}

	public JsonBusService() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String[] getCordnates() {
		return cordnates;
	}

	public void setCordnates(String[] cordnates) {
		this.cordnates = cordnates;
	}

	public List<JsonBusStop> getBustops() {
		return bustops;
	}

	public void setBustops(List<JsonBusStop> bustops) {
		this.bustops = bustops;
	}
}
