package com.gamemaker.event;

import java.util.ArrayList;
import java.util.List;

import com.gamemaker.action.Action;
import com.gamemaker.action.AutoMoveAction;
import com.gamemaker.action.DecreaseScoreAction;
import com.gamemaker.action.IncreaseScoreAction;
import com.gamemaker.action.RepetativeAction;

public class Auto extends Event {

	public Auto() {
		super(EventNameConstants.AUTO);
	}

	public Auto(String name) {
		this();
	}

	@Override
	public List<Action> getAssociatedActions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(new AutoMoveAction());
		actions.add(new RepetativeAction());
		actions.add(new DecreaseScoreAction());
		actions.add(new IncreaseScoreAction());
		return actions;
	}

}
