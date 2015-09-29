package com.gamemaker.helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import com.gamemaker.controllers.Controller;
import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.event.GameInitEvent;
import com.gamemaker.models.Sprite;

public class TimerTask {

	private final Timer t;
	private final Controller controller;

	public TimerTask(Controller controller) {
		this.controller = controller;
		t = new Timer(10, new TimerTaskListener());
	}

	public void stop() {
		t.stop();
	}
	
	public void run() {
		controller.getEventActionHandler().executeEvent(new GameInitEvent());
		t.start();
	}
	
	public boolean isRunning(){
		return t.isRunning();
	}

	/*
	 * Action listener class for timer
	 */
	class TimerTaskListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			notifyObservers();
		}
	}

	public void notifyObservers() {

		// controller.getEventActionHandler().executeEventActionPairs();
		GameState state = controller.getEventActionHandler().executeEventActionPairs();
		
		handleGameState(state);
		
		/* no idea what is happening here. Commeting this whole code. - Amey */
		List<Sprite> tempSpriteList = controller
				.getEventActionSpriteData();

		controller.getView().draw(tempSpriteList);
		
		controller.getView().setScore(controller.getGameModel().getActiveScore().getScore());
	}

	private void handleGameState(GameState state) {
		if(state == GameState.GAME_WIN){
			controller.getView().setMessage(GameConstants.GAME_WIN);
			this.stop();
		}
		if(state == GameState.GAME_LOOSE){
			controller.getView().setMessage(GameConstants.GAME_LOOSE);
			this.stop();
		}
	}

}
