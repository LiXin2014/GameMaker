package com.gamemaker.action;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class RepetativeAction extends Action {

	public RepetativeAction() {
		super(ActionNameConstants.REPETATIVE);
	}

	public RepetativeAction(String name) {
		this();
	}
	
	
	@Override
	public GameState perform(Sprite sprite) {
		if(sprite.getX()<0)
		{
			sprite.setX(sprite.getInitX());
			sprite.setY(sprite.getInitY());
		}
		return state;
	}

}
