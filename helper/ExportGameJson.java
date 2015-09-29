 package com.gamemaker.helper;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.gamemaker.action.Action;
import com.gamemaker.event.Event;
import com.gamemaker.models.GameModel;
import com.gamemaker.models.Sprite;

public class ExportGameJson {
	private final JSONObject gameJSON;
	private final Logger logger = Logger.getLogger(ExportGameJson.class);
	public FileWriter file;
	private final GameModel gameModel;
	
	public ExportGameJson(GameModel gameModel) {
		this.gameModel = gameModel;
		gameJSON = new JSONObject();
		BasicConfigurator.configure();
	}

	public String exportSpriteState(){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonGame	= new JSONObject();
		jsonGame.put("bgURL", gameModel.getBkgURL());
		jsonGame.put("GameName", gameModel.getName());
		for (Sprite sprite : gameModel.getSpriteList()) {
			JSONObject jsonSpriteObject = new JSONObject().element("Name", sprite.getName())
					.element("x", sprite.getX()).element("y", sprite.getY()).element("Vx", sprite.getVx())
					.element("Vy", sprite.getVy()).element("height", sprite.getHeight())
					.element("width", sprite.getWidth())
					.element("imageName", sprite.getImageName())
					.element("initX", sprite.getInitX())
					.element("initY", sprite.getInitY());

			HashMap<Event, ArrayList<Action>> eventActionPair = sprite.getNewEventActionPairs();
			JSONArray jsonEventsArray = new JSONArray();
			if (eventActionPair != null) {
				for (Event event : eventActionPair.keySet()) {
					List<Action> actions = eventActionPair.get(event);
					JSONObject jsonEventObject = new JSONObject();
					jsonEventObject.put("event",event);
					jsonEventObject.put("actions", actions);
					jsonEventsArray.add(jsonEventObject);
				}
			}
			jsonSpriteObject.put("events", jsonEventsArray);
			
			
			HashMap<String, ArrayList<Action>> specialActions = sprite.getSpecialActions();
			jsonEventsArray = new JSONArray();
			if (specialActions != null) {
				for (String spriteName : specialActions.keySet()) {
					List<Action> actions = specialActions.get(spriteName);
					JSONObject jsonEventObject = new JSONObject();
					jsonEventObject.put("sprite",spriteName);
					jsonEventObject.put("actions", actions);
					jsonEventsArray.add(jsonEventObject);
				}
			}
			jsonSpriteObject.put("specialActions", jsonEventsArray);

			jsonArray.add(jsonSpriteObject);
		}
		gameJSON.put("Game", jsonGame);
		gameJSON.put("sprites", jsonArray);
		return gameJSON.toString();
	}
}
