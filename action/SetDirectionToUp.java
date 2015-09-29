package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class SetDirectionToUp extends Action {

	public SetDirectionToUp() {
		super(ActionNameConstants.SET_DIRECTION_TO_UP);
	}

	public SetDirectionToUp(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		sprite.setVy(-GameConstants.DEFAULT_Y_SPEED);
		return state;
	}

}
