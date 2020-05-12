package com.mediaocean.prokabaddievent.model;

import lombok.Data;

@Data
public class Team implements RequestModel,ResponseModel {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String description;
	private String city;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
