package com.gamemaker.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.event.Auto;
import com.gamemaker.event.Event;
import com.gamemaker.models.Sprite;

public class FireAction extends Action {

	private List<Sprite> dynamicSprites = null;

	public FireAction() {
		super(ActionNameConstants.FIRE);
	}

	public FireAction(List<Sprite> dynamicSprites) {
		super(ActionNameConstants.FIRE);
		this.dynamicSprites = dynamicSprites;
	}

	@Override
	public synchronized GameState perform(Sprite sprite) {

		// new Sprite is fired dynamically
		Sprite fire = new Sprite();
		fire.setY(sprite.getY());
		fire.setX(sprite.getX());
		fire.setImageName(GameConstants.BULLET_IMAGE);
		fire.setVx(0);
		fire.setVy(-7);
		fire.setWidth(20);
		fire.setHeight(10);

		HashMap<Event, ArrayList<Action>> newEventActionPairs = new HashMap<Event, ArrayList<Action>>();
		ArrayList<Action> actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());

		newEventActionPairs.put(new Auto(), actions);
		fire.setNewEventActionPairs(newEventActionPairs);
		if (dynamicSprites != null) {
			dynamicSprites.add(fire);
		}

		return state;
	}

}
