package com.gamemaker.action;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class ReflectAction extends Action {

	public ReflectAction() {
		super(ActionNameConstants.REFLECT_ACTION);
	}

	public ReflectAction(GameState state) {
		super(ActionNameConstants.REFLECT_ACTION, state);
	}

	@Override
	public GameState perform(Sprite sprite) {
		// Basically we obtain the current direction depending on the current
		// values of vx and vy.
		// Following chart helps us to decode the current direction and then
		// provides with new direction
		// +x,-y => -x, -y
		// -x, -y => -x, +y
		// -x, +y => +x, +y
		// +x, +y => +x, -y
		int vx = sprite.getVx();
		int vy = sprite.getVy();

		if (vx > 0 && vy < 0) {
			vx = -vx;
		} else if (vx < 0 && vy < 0) {
			vy = -vy;
		} else if (vx < 0 && vy > 0) {
			vx = -vx;
		} else if (vx > 0 && vy > 0) {
			vy = -vy;
		} else if (vx > 0 && vy < 0) {
			vx = -vx;
		} else if (vx < 0 && vy < 0) {
			vy = -vy;
		} else if (vx < 0 && vy > 0) {
			vx = -vx;
		} else if (vx > 0 && vy > 0) {
			vy = -vy;
		} if (vx > 0 && vy == 0) {
			vx = -vx;
		} else if (vx < 0 && vy == 0) {
			vx = -vx;
		} else if (vx == 0 && vy > 0) {
			vy = -vy;
		} else if (vx == 0 && vy < 0) {
			vy = -vy;
		}
		sprite.setVx(vx);
		sprite.setVy(vy);

		return state;
	}

}
