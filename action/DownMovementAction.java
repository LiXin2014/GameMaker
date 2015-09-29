package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class DownMovementAction extends Action {

	public DownMovementAction() {
		super(ActionNameConstants.DOWN_MOVEMENT_ACTION);
	}

	public DownMovementAction(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		// Down movement is achieved by increase y position
		int yPos = sprite.getY();
		yPos = yPos + GameConstants.DEFAULT_Y_SPEED;
		sprite.setY(yPos);
		return state;
	}

}
