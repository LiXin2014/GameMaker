package com.gamemaker.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.gamemaker.action.UnsupportedAction;
import com.gamemaker.event.UnsupportedEvent;
import com.gamemaker.helper.DatabaseManager;
import com.gamemaker.helper.EventActionHandler;
import com.gamemaker.helper.ExportGameJson;
import com.gamemaker.helper.ImportGameJson;
import com.gamemaker.models.GameModel;
import com.gamemaker.models.GameModelDAO;
import com.gamemaker.models.Sprite;
import com.gamemaker.views.GamePlayable;

public abstract class Controller {
	private final Logger logger = Logger.getLogger(Controller.class);

	protected GameModel gameModel;
	protected final ExportGameJson exportGameJson;
	protected final ImportGameJson importGameJson;
	protected DatabaseManager dataBaseManager = null;

	public abstract GamePlayable getView();

	public abstract EventActionHandler getEventActionHandler();

	public abstract List<Sprite> getEventActionSpriteData();

	public Controller() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		dataBaseManager = new DatabaseManager(sessionFactory);

		this.gameModel = new GameModel();
		exportGameJson = new ExportGameJson(gameModel);
		importGameJson = new ImportGameJson(gameModel);
	}

	public GameModel getGameModel() {
		return gameModel;
	}

	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}

	public List<Sprite> getSpritesData() {
		return this.gameModel.getSpriteList();
	}

	public void setSpritesData(List<Sprite> spritesData) {
		this.gameModel.setSpriteList(spritesData);
	}

	protected void loadGame(String gameName) {

		String loadGame = dataBaseManager.loadFromDatabase(gameName);

		try {
			importGameJson.importSpriteState(loadGame);

		} catch (UnsupportedEvent e) {

			e.printStackTrace();
		} catch (UnsupportedAction e) {

			e.printStackTrace();
		}
	}

	protected void saveGame(String gameName) {
		boolean isUpdated = false;
		if (dataBaseManager != null) {
			String gameJson = exportGameJson.exportSpriteState();
			GameModelDAO gameModelDAO = new GameModelDAO(gameJson, gameName);
			dataBaseManager.saveToDatabase(gameModelDAO);

		}

	}

}
