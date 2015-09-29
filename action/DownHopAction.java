package com.gamemaker.action;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class DownHopAction extends Action {

	public DownHopAction() {
		super(ActionNameConstants.DOWN_HOP_ACTION);
	}

	public DownHopAction(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		// Down movement is achieved by increase y position
		int yPos = sprite.getY();
		yPos = yPos + GameConstants.HOP_Y_SPEED;
		sprite.setY(yPos);
		return state;
	}

}
