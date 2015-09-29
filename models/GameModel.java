package com.gamemaker.models;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameModel {

	private String name;;
	private List<Sprite> spriteList;
	private String bkgURL;
	// Currently we do not store this but this will be used in run time to
	// maintain score. In future, please decouple this model in tow models;
	// StaticModel and RunTimeModel
	private GameScore activeScore;

	public GameModel() {
		spriteList = new CopyOnWriteArrayList<Sprite>();
		activeScore = new GameScore();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sprite> getSpriteList() {
		return spriteList;
	}

	public void setSpriteList(List<Sprite> spriteList) {
		this.spriteList = spriteList;
	}

	public String getBkgURL() {
		return bkgURL;
	}

	public void setBkgURL(String bkgURL) {
		this.bkgURL = bkgURL;
	}

	public GameScore getActiveScore() {
		return activeScore;
	}

	public void setActiveScore(GameScore activeScore) {
		this.activeScore = activeScore;
	}

}
