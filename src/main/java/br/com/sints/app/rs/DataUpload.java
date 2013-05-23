package br.com.sints.app.rs;

import java.io.Serializable;

public class DataUpload implements Serializable {

	private String dataType;
	private String dataUrl;

	public DataUpload(String dataType, String dataUrl) {
		super();
		this.dataType = dataType;
		this.dataUrl = dataUrl;
	}

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
}
