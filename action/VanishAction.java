package com.gamemaker.action;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class VanishAction extends Action{

	public VanishAction() {
		super(ActionNameConstants.VANISH);
	}
	
	public VanishAction(GameState state) {
		super(ActionNameConstants.VANISH, state);
	}
	
	
	@Override
	public GameState perform(Sprite sprite) {
		sprite.setVanish(true);
		return state;
	}
}
