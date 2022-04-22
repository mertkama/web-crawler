package com.example.crawler_web;

public class AdDescription {
	private String registrationNumber;
	private String type;
	private String location;
	private String typeDescription;

	public AdDescription() {
		super();
	}

	public AdDescription(String registrationNumber, String type, String location, String typeDescription) {
		super();
		this.registrationNumber = registrationNumber;
		this.type = type;
		this.location = location;
		this.typeDescription = typeDescription;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	@Override
	public String toString() {
		return "İlan Bilgileri ==> [ İhale Kayıt No = " + registrationNumber + "    ||    Niteliği, Türü ve Miktarı = "
				+ type + "    ||    İşin Yapılacağı Yer = " + location + "    ||    İhale Türü = " + typeDescription
				+ "]";
	}

}
