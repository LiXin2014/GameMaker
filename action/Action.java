package com.gamemaker.action;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

/**
 * Defines abstract action. It contains all the basic things that are needed for
 * any action. Make sure you name each action differently and add that to
 * ActionNameConstants. This differentiates each action from other during json
 * serialization and deserialization.
 * 
 * To support json deserialization, add your entry into ActionDeserializer.
 * 
 * 
 */
public abstract class Action {
	private final String name;
	protected GameState state = GameState.GAME_CONTINUE;

	public Action(String name) {
		this.name = name;
	}

	public Action(String name, GameState state) {
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
		if (!(obj instanceof Action))
			return false;
		Action other = (Action) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

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

	public abstract GameState perform(Sprite sprite);

	public GameState perform(Sprite thisSprite, Sprite otherSprite) {
		return perform(thisSprite);
	}
}
