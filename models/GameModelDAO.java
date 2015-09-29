package com.gamemaker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameModelDAO {

	@Id
	private String gameName;
	@Column(name = "gamejson", length = 10000)
	private String gamejson;

	public String getGamejson() {
		return gamejson;
	}

	public void setGamejson(String gamejson) {
		this.gamejson = gamejson;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public GameModelDAO(String gamejson, String gameName) {
		this.gamejson = gamejson;
		this.gameName = gameName;
	}

	public GameModelDAO(String gameName) {
		this.gameName = gameName;
	}

	public GameModelDAO() {

	}
}
