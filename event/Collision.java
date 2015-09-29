package com.gamemaker.event;

import java.util.ArrayList;
import java.util.List;

import com.gamemaker.action.Action;
import com.gamemaker.action.DecreaseScoreAction;
import com.gamemaker.action.IncreaseScoreAction;
import com.gamemaker.action.PlayCollisionSoundAction;
import com.gamemaker.action.ReflectAction;
import com.gamemaker.action.VanishAction;
import com.gamemaker.controllers.GameState;

public class Collision extends Event {
	
	public Collision() {
		super(EventNameConstants.COLLISION_EVENT);
	}

	public Collision(GameState state) {
		super(EventNameConstants.COLLISION_EVENT, state);
	}

	@Override
	public List<Action> getAssociatedActions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(new ReflectAction(state));
		actions.add(new PlayCollisionSoundAction(state));
		actions.add(new VanishAction(state));
		actions.add(new DecreaseScoreAction(state));
		actions.add(new IncreaseScoreAction(state));
		return actions;
	}

}
