package com.gamemaker.action;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class AutoMoveAction extends Action {

	public AutoMoveAction() {
		super(ActionNameConstants.AUTO_MOVE);
	}

	public AutoMoveAction(String name) {
		this();
	}

	@Override
	public GameState perform(Sprite sprite) {
		sprite.update();
		return state;
	}

}
