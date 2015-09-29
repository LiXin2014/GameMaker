package com.gamemaker.event;

import java.util.ArrayList;
import java.util.List;

import com.gamemaker.action.Action;
import com.gamemaker.action.DecreaseScoreAction;
import com.gamemaker.action.DownHopAction;
import com.gamemaker.action.IncreaseScoreAction;
import com.gamemaker.action.LeftHopAction;
import com.gamemaker.action.RightHopAction;
import com.gamemaker.action.UpHopAction;

public class Hop extends Event {

	public Hop() {
		super(EventNameConstants.HOP_EVENT);
	}

	@Override
	public List<Action> getAssociatedActions() {
		List<Action> actions = new ArrayList<Action>();
		actions.add(new LeftHopAction());
		actions.add(new RightHopAction());
		actions.add(new UpHopAction());
		actions.add(new DownHopAction());
		actions.add(new DecreaseScoreAction());
		actions.add(new IncreaseScoreAction());
		return actions;
	}

}
