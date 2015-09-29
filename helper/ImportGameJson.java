package com.gamemaker.helper;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.gamemaker.action.Action;
import com.gamemaker.action.UnsupportedAction;
import com.gamemaker.event.Event;
import com.gamemaker.event.UnsupportedEvent;
import com.gamemaker.models.GameModel;
import com.gamemaker.models.Sprite;

public class ImportGameJson {

	private JSONObject gameJSON;
	private final Logger logger = Logger.getLogger(ExportGameJson.class);;
	private final String jsonString;
	private final GameModel gameModel;

	public ImportGameJson(GameModel gameModel) {
		this.gameModel = gameModel;
		gameJSON = new JSONObject();
		BasicConfigurator.configure();
		jsonString = null;
	}

	public GameModel importSpriteState(String json) throws UnsupportedEvent, UnsupportedAction {
		
		gameJSON = (JSONObject) JSONSerializer.toJSON(json);
		List<Sprite> spritesData = gameModel.getSpriteList();
		spritesData.clear();

		JSONArray jsonArray = gameJSON.getJSONArray("sprites");
		JSONObject jsonGame = gameJSON.getJSONObject("Game");

		for (Object key : jsonArray) {
			Sprite loadSprite = new Sprite();
			loadSprite.setName((String) ((JSONObject) key).get("Name"));
			loadSprite.setImageName((String) ((JSONObject) key).get("imageName"));
			loadSprite.setX((Integer) ((JSONObject) key).get("x"));
			loadSprite.setY((Integer) ((JSONObject) key).get("y"));
			loadSprite.setVx((Integer) ((JSONObject) key).get("Vx"));
			loadSprite.setVy((Integer) ((JSONObject) key).get("Vy"));
			loadSprite.setHeight((Integer) ((JSONObject) key).get("height"));
			loadSprite.setWidth((Integer) ((JSONObject) key).get("width"));
			loadSprite.setInitX((Integer) ((JSONObject) key).get("initX"));
			loadSprite.setInitY((Integer) ((JSONObject) key).get("initY"));

			JSONArray jsonEventsArray = (JSONArray) ((JSONObject) key).get("events");
			for (Object object : jsonEventsArray) {
				JSONObject jsonEventObject = ((JSONObject) object).getJSONObject("event");
				Event event = EventeDeserializer.getEventObjectFromString(jsonEventObject);
				JSONArray jsonActionsArray = (JSONArray) ((JSONObject) object).get("actions");
				ArrayList<Action> actions = getActionListFromJSON(jsonActionsArray);
				loadSprite.getNewEventActionPairs().put(event, actions);
			}

			jsonEventsArray = (JSONArray) ((JSONObject) key).get("specialActions");
			for (Object object : jsonEventsArray) {
				String spriteName = ((JSONObject) object).getString("sprite");
				JSONArray jsonActionsArray = (JSONArray) ((JSONObject) object).get("actions");
				ArrayList<Action> actions = getActionListFromJSON(jsonActionsArray);
				loadSprite.getSpecialActions().put(spriteName, actions);
			}

			spritesData.add(loadSprite);
		}
		gameModel.setBkgURL((String) ((JSONObject) jsonGame).get("bgURL"));
		gameModel.setName((String) ((JSONObject) jsonGame).get("GameName"));
		gameModel.setSpriteList(spritesData);
		return gameModel;
	}

	private ArrayList<Action> getActionListFromJSON(JSONArray jsonActionsArray) throws UnsupportedAction {
		ArrayList<Action> actions = new ArrayList<Action>();

		// Here we iterate over all objects. Create java object from given
		// object
		for (Object object : jsonActionsArray) {
			Action action = ActionDeserializer.getActionObjectFromString((JSONObject) object);
			actions.add(action);
		}

		return actions;
	}

}
