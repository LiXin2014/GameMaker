package com.gamemaker.event;

import java.util.ArrayList;
import java.util.List;

import com.gamemaker.action.Action;
import com.gamemaker.action.DecreaseScoreAction;
import com.gamemaker.action.IncreaseScoreAction;
import com.gamemaker.action.SetDirectionToDown;
import com.gamemaker.action.SetDirectionToLeft;
import com.gamemaker.action.SetDirectionToRight;
import com.gamemaker.action.SetDirectionToUp;

public class GameInitEvent extends Event {

	public GameInitEvent() {
		super(EventNameConstants.GAME_INIT_EVENT);
	}

	public GameInitEvent(String name) {
		this();
	}

	@Override
	public List<Action> getAssociatedActions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(new SetDirectionToLeft());
		actions.add(new SetDirectionToRight());
		actions.add(new SetDirectionToUp());
		actions.add(new SetDirectionToDown());
		actions.add(new DecreaseScoreAction());
		actions.add(new IncreaseScoreAction());
		return actions;
	}

}
