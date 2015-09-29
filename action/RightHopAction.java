package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class RightHopAction extends Action {

	public RightHopAction() {
		super(ActionNameConstants.RIGHT_HOP_ACTION);
	}

	public RightHopAction(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		// Right movement is achieved by changing x position towards ->
		// direction
		// to make any sprite move right, we can increase the x position
		int xPos = sprite.getX();
		xPos = xPos + GameConstants.HOP_X_SPEED;
		sprite.setX(xPos);

		return state;
	}

}
