package com.gamemaker.models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * GameScore defines a model for active score of the game. This model is should
 * remain decoupled from other elements like sprites or user. This helps us to
 * keep the dependency to the minimum.
 */
public final class GameScore {
	private AtomicInteger score;

	public GameScore() {
		score = new AtomicInteger();
	}

	public Integer increaseScoreByOne() {
		return score.incrementAndGet();
	}

	public Integer decreaseScoreByOne() {
		return score.decrementAndGet();
	}

	public Integer getScore() {
		return score.get();
	}
}
