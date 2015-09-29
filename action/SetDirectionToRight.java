package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class SetDirectionToRight extends Action {

	public SetDirectionToRight() {
		super(ActionNameConstants.SET_DIRECTION_TO_RIGHT);
	}

	public SetDirectionToRight(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		sprite.setVx(GameConstants.DEFAULT_X_SPEED);
		return state;
	}

}
