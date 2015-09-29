package com.gamemaker.action;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.GameScore;

public abstract class AbstractScoreAction extends Action {
	protected GameScore gameScore;

	public AbstractScoreAction() {
		super(ActionNameConstants.ABSTRACT_SCORE_ACTION);
	}

	public AbstractScoreAction(String name, GameScore gameScore) {
		super(name);
		this.gameScore = gameScore;
	}

	public AbstractScoreAction(String name, GameState state, GameScore gameScore) {
		super(name, state);
		this.gameScore = gameScore;
	}

	public GameScore getGameScore() {
		return gameScore;
	}

	public void setGameScore(GameScore gameScore) {
		this.gameScore = gameScore;
	}
}
