package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class RightMovementAction extends Action {

	public RightMovementAction() {
		super(ActionNameConstants.RIGHT_MOVEMENT_ACTION);
	}

	public RightMovementAction(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		// Right movement is achieved by changing x position towards ->
		// direction
		// to make any sprite move right, we can increase the x position
		int xPos = sprite.getX();
		xPos = xPos + GameConstants.DEFAULT_X_SPEED;
		sprite.setX(xPos);

		return state;
	}

}
