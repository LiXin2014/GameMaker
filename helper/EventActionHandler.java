package com.gamemaker.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.gamemaker.action.Action;
import com.gamemaker.controllers.GameState;
import com.gamemaker.controllers.PlayController;
import com.gamemaker.event.Auto;
import com.gamemaker.event.Collision;
import com.gamemaker.event.Event;
import com.gamemaker.models.Sprite;

public class EventActionHandler {
	private final Logger logger = Logger.getLogger(PlayController.class.getName());;
	private List<Sprite> sprites = null;
	private ArrayList<Sprite> dynamicSprites = null;

	public EventActionHandler(List<Sprite> spriteList, ArrayList<Sprite> dynamicSprites) {
		BasicConfigurator.configure();
		this.sprites = spriteList;
		this.dynamicSprites = dynamicSprites;
	}

	/*
	 * It basically checks who all are interested for this event. For example, X
	 * event is defined and sprite1 and 2 are interested in X event Then actions
	 * related to that events are performed. Otherwise, sprite's request is
	 * ignored.
	 */
	public void executeEvent(Event event, Sprite sprite) {
		HashMap<Event, ArrayList<Action>> eventActionPair = sprite.getNewEventActionPairs();
		if (eventActionPair != null) {
			ArrayList<Action> actions = eventActionPair.get(event);
			if (actions != null) {
				for (Action action : actions) {
					action.perform(sprite);
				}
			}
		}
	}

	/*
	 * As we don't have efficient way to lookup all subscribed sprites for an
	 * action, we will iterate through sprite and check if they have
	 * subscription for that event.
	 * 
	 * TODO: Remove event dependency. For now we are passing event, because it
	 * will break whole code if we go for just action based lookup. Need to do
	 * this as part of other improvement
	 */
	public void executeAction(Event event, Action action) {
		for (Sprite sprite : sprites) {
			HashMap<Event, ArrayList<Action>> eventActionPair = sprite.getNewEventActionPairs();
			if (eventActionPair != null) {
				ArrayList<Action> actions = eventActionPair.get(event);
				if (actions != null) {
					if (actions.contains(action)) {
						action.perform(sprite);
					}
				}
			}
		}

		if (dynamicSprites != null) {
			for (Sprite sprite : dynamicSprites) {
				executeEvent(new Auto(), sprite);
			}
		}

	}

	/*
	 * Update: Initially, we were using hashmap based sprite access. This was
	 * because sprites were hardcoded. I have changed this and now it doesn't
	 * care about identity of the objects when processing.
	 * 
	 * This is still inefficient but we can't do anything about it. Once we
	 * optimize the actionToSprite lookup then this inefficiency will be removed
	 */
	public GameState executeEventActionPairs() {
		GameState state = GameState.GAME_CONTINUE;
		// 1. Check for collision, and then call collision related actions
		// need to decide if collision is happening with any sprite
		// We iterate over all the sprites for now and then check whether
		// any two sprites are colliding with each other
		for (int i = 0; i < sprites.size() - 1; i++) {
			for (int j = i + 1; j < sprites.size(); j++) {
				Sprite sprite1 = sprites.get(i);
				Sprite sprite2 = sprites.get(j);
				if (spritesBelongToSameGroup(sprite1, sprite2) == false) {
					if (checkStatCollision(sprite1, sprite2)) {
						state = executeCollisionEvent(new Collision(GameState.GAME_CONTINUE), sprite1, sprite2);
						if(shouldWeStop(state))
							return state;
						state = executeCollisionEvent(new Collision(GameState.GAME_CONTINUE), sprite2, sprite1);
						if(shouldWeStop(state))
							return state;
					}
				}
			}
		}

		for (Sprite sprite : sprites) {
			// 2. Then call automove action for interested sprites
			executeEvent(new Auto(), sprite);
		}
		for (Sprite sprite : dynamicSprites) {
			// dynamic sprites automove
			executeEvent(new Auto(), sprite);
		}

		// see if any sprite is marked for deletion
		List<Sprite> toBeDeleted = new ArrayList<Sprite>(sprites.size());
		for (Sprite sprite : sprites) {
			if (sprite.isVanish()) {
				toBeDeleted.add(sprite);
			}
		}

		// collect sprites for deletion
		for (Sprite sprite : toBeDeleted) {
			boolean isDeleted = false;
			isDeleted = sprites.remove(sprite);
		}
		
		return state;
	}

	private boolean spritesBelongToSameGroup(Sprite sprite1, Sprite sprite2) {
		String firstSprite = sprite1.getName().split("_")[0];
		String secondSprite = sprite2.getName().split("_")[0];
		return firstSprite.equals(secondSprite);
	}

	private GameState executeCollisionEvent(Event event, Sprite thisSprite, Sprite otherSprite) {
		GameState state = GameState.GAME_CONTINUE;
		// Here, two things can be possible. Either this sprite has special
		// behavior with otherSprite
		// or we have execute normal action.
		ArrayList<Action> actionsToBeExecuted = null;
		boolean specialActionToBePerformed = false;
		HashMap<String, ArrayList<Action>> specialActions = thisSprite.getSpecialActions();
		if (specialActions != null) {
			if (specialActions.containsKey(otherSprite.getName())) {
				// thisSprite is interested in performing separate action for
				// this event and with this sprite
				specialActionToBePerformed = true;
				actionsToBeExecuted = specialActions.get(otherSprite.getName());
			}
		}

		if (specialActionToBePerformed == false) {
			HashMap<Event, ArrayList<Action>> eventActionPair = thisSprite.getNewEventActionPairs();
			if (eventActionPair != null) {
				// Higher priority actions should be executed first
				actionsToBeExecuted = eventActionPair.get(event);
			}
		}

		if (actionsToBeExecuted != null) {
			for (Action action : actionsToBeExecuted) {
				state = action.perform(thisSprite);
				if(shouldWeStop(state))
					return state;
			}
		}
		
		return state;
	}

	/*
	 * Update: Before making this change, we were taking actions when collison
	 * was detected. But this is no OO. This is hard to test. One function must
	 * do one thing. So we removed all actions from here and handled as part of
	 * action execution.
	 */
	private boolean checkStatCollision(Sprite movingSprite, Sprite statSprite) {
		boolean doWeHaveACollision = false;
		if (movingSprite.getX() + movingSprite.getWidth() >= statSprite.getX()
				&& movingSprite.getX() <= statSprite.getX() + statSprite.getWidth()
				&& movingSprite.getY() <= statSprite.getY() + statSprite.getHeight()
				&& movingSprite.getY() + movingSprite.getHeight() >= statSprite.getY()) {
			doWeHaveACollision = true;
		}
		return doWeHaveACollision;
	}

	public void executeEvent(Event event) {
		for (Sprite sprite : sprites) {
			HashMap<Event, ArrayList<Action>> eventActionPair = sprite.getNewEventActionPairs();
			if (eventActionPair != null) {
				ArrayList<Action> actions = eventActionPair.get(event);
				if (actions != null) {
					for (Action action : actions) {
						action.perform(sprite);
					}
				}
			}
		}
	}
	
	private boolean shouldWeStop(GameState state){
		if(state == GameState.GAME_LOOSE || state == GameState.GAME_WIN)
			return true;
		else
			return false;
	}

}
