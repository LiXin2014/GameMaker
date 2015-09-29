package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class SetDirectionToLeft extends Action {

	public SetDirectionToLeft() {
		super(ActionNameConstants.SET_DIRECTION_TO_LEFT);
	}

	public SetDirectionToLeft(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		sprite.setVx(-GameConstants.DEFAULT_X_SPEED);
		return state;
	}

}
