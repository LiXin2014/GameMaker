package com.gamemaker.main;

import com.gamemaker.controllers.Controller;
import com.gamemaker.controllers.MakerController;
import com.gamemaker.views.GameMakerView;
import com.gamemaker.views.StartView;

public class GameMaker {
	
	public static void main(String[] args) {
		StartView startView = new StartView();
		GameMakerView gameMakerView = new GameMakerView();
		Controller controller = new MakerController(startView, gameMakerView);
		
	}

}
