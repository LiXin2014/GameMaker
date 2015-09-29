package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class LeftMovementAction extends Action {

	public LeftMovementAction() {
		super(ActionNameConstants.LEFT_MOVEMENT_ACTION);
	}

	public LeftMovementAction(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		// Left movement is achieved by changing x position towards <- direction
		// to make any sprite move left, we can reduce the x position
		int xPos = sprite.getX();
		xPos = xPos - GameConstants.DEFAULT_X_SPEED;
		sprite.setX(xPos);
		return state;
	}

}
