package com.gamemaker.action;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.GameScore;
import com.gamemaker.models.Sprite;

/**
 * Currently we are keeping increase score action to be simple. This helps us to
 * keep the design simple for a while. Should you support different game score,
 * just enhance this class for variable increase in score. Change score model to
 * support random score increase.
 * 
 */
public class IncreaseScoreAction extends AbstractScoreAction {
	public IncreaseScoreAction() {
		super(ActionNameConstants.INCREASE_SCORE_ACTION, new GameScore());
	}

	public IncreaseScoreAction(GameScore gameScore) {
		super(ActionNameConstants.INCREASE_SCORE_ACTION, gameScore);
	}

	public IncreaseScoreAction(GameState state) {
		super(ActionNameConstants.INCREASE_SCORE_ACTION, state, new GameScore());
	}

	@Override
	public GameState perform(Sprite sprite) {
		gameScore.increaseScoreByOne();
		return state;
	}

}
