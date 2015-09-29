package com.gamemaker.event;

import java.util.List;

import com.gamemaker.action.Action;
import com.gamemaker.controllers.GameState;

public abstract class Event {
	private final String name;
	protected GameState state;
	
	public Event(String name) {
		this.name = name;
		state = GameState.GAME_CONTINUE;
	}

	public Event(String name, GameState state) {
		this(name);
		this.state = state;
	}

	/**
	 * To understand why we need this, we must understand how hashMap works in
	 * java. By default, each instance of same class will hash to different
	 * position. But in this architecture, we are doing lookup often based on
	 * classname rather than instance of that class. That is why we have
	 * overridden this behavior where all instances will hash to single hash
	 * value
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Event))
			return false;
		Event other = (Event) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public abstract List<Action> getAssociatedActions();

	public String getName() {
		return name;
	}
	
	public GameState getState() {
		return state;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
}
