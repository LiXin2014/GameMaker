package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class LeftHopAction extends Action {

	public LeftHopAction() {
		super(ActionNameConstants.LEFT_HOP_ACTION);
	}

	public LeftHopAction(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		// Left movement is achieved by changing x position towards <- direction
		// to make any sprite move left, we can reduce the x position
		int xPos = sprite.getX();
		xPos = xPos - GameConstants.HOP_X_SPEED;
		sprite.setX(xPos);
		return state;
	}

}
