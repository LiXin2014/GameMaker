package com.gamemaker.action;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.GameScore;
import com.gamemaker.models.Sprite;

/**
 * Currently we are keeping decrease score action to be simple. This helps us to
 * keep the design simple for a while. Should you support different game score,
 * just enhance this class for variable decrease in score. Change score model to
 * support random score decrease.
 * 
 */
public class DecreaseScoreAction extends AbstractScoreAction {
	public DecreaseScoreAction() {
		super(ActionNameConstants.DECREASE_SCORE_ACTION, new GameScore());
	}

	public DecreaseScoreAction(GameScore gameScore) {
		super(ActionNameConstants.DECREASE_SCORE_ACTION, gameScore);
	}

	public DecreaseScoreAction(GameState state) {
		super(ActionNameConstants.DECREASE_SCORE_ACTION, state, new GameScore());
	}

	@Override
	public GameState perform(Sprite sprite) {
		gameScore.decreaseScoreByOne();
		return state;
	}

}
