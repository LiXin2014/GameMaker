package com.gamemaker.helper;

import net.sf.json.JSONObject;

import com.gamemaker.controllers.GameState;
import com.gamemaker.event.Auto;
import com.gamemaker.event.Collision;
import com.gamemaker.event.Event;
import com.gamemaker.event.EventNameConstants;
import com.gamemaker.event.GameInitEvent;
import com.gamemaker.event.Hop;
import com.gamemaker.event.KeyboardPressEvent;
import com.gamemaker.event.UnsupportedEvent;

public class EventeDeserializer {

	/*
	 * Helper function that can convert JSONObject back to Action Object.
	 */
	public static Event getEventObjectFromString(String name, GameState state) throws UnsupportedEvent {
		if (EventNameConstants.KEYBOARD_PRESS_EVENT.toString().equals(name)) {
			return new KeyboardPressEvent();
		} else if (EventNameConstants.AUTO.toString().equals(name)) {
			return new Auto();
		} else if (EventNameConstants.COLLISION_EVENT.toString().equals(name)) {
			return new Collision(state);
		} else if (EventNameConstants.HOP_EVENT.toString().equals(name)) {
			return new Hop();
		} else if (EventNameConstants.GAME_INIT_EVENT.toString().equals(name)) {
			return new GameInitEvent();
		} else {
			throw new UnsupportedEvent("No JSON deserialization support available for " + name);
		}
	}

	public static Event getEventObjectFromString(String name) throws UnsupportedEvent {
		return getEventObjectFromString(name, GameState.GAME_CONTINUE);
	}

	public static Event getEventObjectFromString(JSONObject jsonEventObject) throws UnsupportedEvent {
		String name = jsonEventObject.getString("name");
		GameState state = GameState.valueOf(jsonEventObject.getString("state"));
		return getEventObjectFromString(name, state);
	}
}
