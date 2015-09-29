package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class UpHopAction extends Action {

	public UpHopAction() {
		super(ActionNameConstants.UP_HOP_ACTION);
	}

	public UpHopAction(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		// Down movement is achieved by reducing y position
		int yPos = sprite.getY();
		yPos = yPos - GameConstants.HOP_Y_SPEED;
		sprite.setY(yPos);
		return state;
	}

}
