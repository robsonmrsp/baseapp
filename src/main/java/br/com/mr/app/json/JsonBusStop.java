package br.com.mr.app.json;

import java.io.Serializable;
import java.util.List;

public class JsonBusStop implements Serializable {

	private static final long serialVersionUID = -5858958615318000417L;
	private Long id;
	private String address;
	private List<Long> busservices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Long> getBusservices() {
		return busservices;
	}

	public void setBusservices(List<Long> busservices) {
		this.busservices = busservices;
	}
}
