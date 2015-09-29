package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class UpMovementAction extends Action {

	public UpMovementAction() {
		super(ActionNameConstants.UP_MOVEMENT_ACTION);
	}

	public UpMovementAction(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		// Down movement is achieved by reducing y position
		int yPos = sprite.getY();
		yPos = yPos - GameConstants.DEFAULT_Y_SPEED;
		sprite.setY(yPos);
		return state;
	}

}
