package com.gamemaker.helper;

import net.sf.json.JSONObject;

import com.gamemaker.action.Action;
import com.gamemaker.action.ActionNameConstants;
import com.gamemaker.action.AutoMoveAction;
import com.gamemaker.action.DecreaseScoreAction;
import com.gamemaker.action.DownHopAction;
import com.gamemaker.action.DownMovementAction;
import com.gamemaker.action.FireAction;
import com.gamemaker.action.IncreaseScoreAction;
import com.gamemaker.action.LeftHopAction;
import com.gamemaker.action.LeftMovementAction;
import com.gamemaker.action.PlayCollisionSoundAction;
import com.gamemaker.action.ReflectAction;
import com.gamemaker.action.RepetativeAction;
import com.gamemaker.action.RightHopAction;
import com.gamemaker.action.RightMovementAction;
import com.gamemaker.action.SetDirectionToDown;
import com.gamemaker.action.SetDirectionToLeft;
import com.gamemaker.action.SetDirectionToRight;
import com.gamemaker.action.SetDirectionToUp;
import com.gamemaker.action.UnsupportedAction;
import com.gamemaker.action.UpHopAction;
import com.gamemaker.action.UpMovementAction;
import com.gamemaker.action.VanishAction;
import com.gamemaker.controllers.GameState;

public class ActionDeserializer {

	/*
	 * Helper function that can convert JSONObject back to Event Object.
	 */
	public static Action getActionObjectFromString(String name, GameState state)
			throws UnsupportedAction {
		if (ActionNameConstants.AUTO_MOVE.equals(name)) {
			return new AutoMoveAction();
		} else if (ActionNameConstants.DOWN_MOVEMENT_ACTION.equals(name)) {
			return new DownMovementAction();
		} else if (ActionNameConstants.LEFT_MOVEMENT_ACTION.equals(name)) {
			return new LeftMovementAction();
		} else if (ActionNameConstants.PLAY_COLLISION_SOUND_ACTION.equals(name)) {
			return new PlayCollisionSoundAction(state);
		} else if (ActionNameConstants.REFLECT_ACTION.equals(name)) {
			return new ReflectAction(state);
		} else if (ActionNameConstants.RIGHT_MOVEMENT_ACTION.equals(name)) {
			return new RightMovementAction();
		} else if (ActionNameConstants.UP_MOVEMENT_ACTION.equals(name)) {
			return new UpMovementAction();
		} else if (ActionNameConstants.VANISH.equals(name)) {
			return new VanishAction();
		} else if (ActionNameConstants.REPETATIVE.equals(name)) {
			return new RepetativeAction();
		} else if (ActionNameConstants.FIRE.equals(name)) {
			return new FireAction();
		} else if (ActionNameConstants.SET_DIRECTION_TO_LEFT.equals(name)) {
			return new SetDirectionToLeft();
		} else if (ActionNameConstants.SET_DIRECTION_TO_RIGHT.equals(name)) {
			return new SetDirectionToRight();
		} else if (ActionNameConstants.SET_DIRECTION_TO_UP.equals(name)) {
			return new SetDirectionToUp();
		} else if (ActionNameConstants.SET_DIRECTION_TO_DOWN.equals(name)) {
			return new SetDirectionToDown();
		} else if (ActionNameConstants.LEFT_HOP_ACTION.equals(name)) {
			return new LeftHopAction();
		} else if (ActionNameConstants.RIGHT_HOP_ACTION.equals(name)) {
			return new RightHopAction();
		} else if (ActionNameConstants.UP_HOP_ACTION.equals(name)) {
			return new UpHopAction();
		} else if (ActionNameConstants.DOWN_HOP_ACTION.equals(name)) {
			return new DownHopAction();
		} else if (ActionNameConstants.INCREASE_SCORE_ACTION.equals(name)) {
			return new IncreaseScoreAction();
		} else if (ActionNameConstants.DECREASE_SCORE_ACTION.equals(name)) {
			return new DecreaseScoreAction();
		} else {
			throw new UnsupportedAction(
					"No JSON deserialization support available for " + name);
		}
	}

	public static Action getActionObjectFromString(String name)
			throws UnsupportedAction {
		return getActionObjectFromString(name, GameState.GAME_CONTINUE);
	}

	public static Action getActionObjectFromString(JSONObject jsonEventObject)
			throws UnsupportedAction {
		GameState state = GameState.valueOf(jsonEventObject.getString("state"));
		String name = jsonEventObject.getString("name");
		return getActionObjectFromString(name, state);
	}

	public static Action getActionObjectFromString(String name, String stateStr)
			throws UnsupportedAction {
		GameState state = GameState.valueOf(stateStr);
		return getActionObjectFromString(name, state);
	}
}
