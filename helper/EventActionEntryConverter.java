/*
 * This class takes a string that is parsed by the app to read a action-event pair,
 * and converts to a user friendly string for display purposes
 */

package com.gamemaker.helper;

import java.util.HashMap;

import com.gamemaker.controllers.GameState;

public class EventActionEntryConverter {
	private String value;
	private String readableValue;
	private static HashMap<String, String> valueToString = new HashMap<String, String>();
	{
		valueToString.put(GameState.GAME_CONTINUE.toString(), "continue playing");
		valueToString.put(GameState.GAME_WIN.toString(), "win game");
		valueToString.put(GameState.GAME_LOOSE.toString(), "lose game");
	}
	
	public EventActionEntryConverter (String value){
		this.value = value;
		this.readableValue = getUserReadable(value);
	}
	
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString(){
		return readableValue;
	}

	private String getUserReadable(String value)
	{
		String[] values = value.split("-");
		values[0]=values[0].replace("Event ", "");
		String readable = "User will " + valueToString.get(values[2]) + ", on action " + values[1] + ", of event " + values[0].replace("Add ", "") + "." ;
		return readable;	
	}

}
