package com.gamemaker.helper;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;

import com.gamemaker.models.GameModelDAO;

public class DatabaseManager {

	private final Logger logger = Logger.getLogger(DatabaseManager.class);
	Connection conn = null;
	SessionFactory sessionFactory;
	Session session;
	GameModelDAO gameModelDAO = null;

	public DatabaseManager(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String[] savedGames() {
		List<GameModelDAO> array = null;

		String[] gameList = null;
		try {
			array = new ArrayList<GameModelDAO>();
			session = sessionFactory.openSession();
			session.beginTransaction();

			Criteria cr = session.createCriteria(GameModelDAO.class)
					.setProjection(Projections.projectionList().add(Projections.property("gameName"), "gameName"))
					.setResultTransformer(Transformers.aliasToBean(GameModelDAO.class));
			array = cr.list();
			gameList = new String[array.size()];

			for (int i = 0; i < array.size(); i++) {
				gameList[i] = array.get(i).getGameName();
			}

		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.getTransaction().commit();
			session.close();
		}

		return gameList;
	}

	public void saveToDatabase(GameModelDAO gameModelDAO) {
		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			gameModelDAO.getGameName();
			gameModelDAO.getGamejson();

			session.saveOrUpdate(gameModelDAO);

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
	}

	public String loadFromDatabase(String gameName) {
		try {
			gameModelDAO = new GameModelDAO(gameName);
			session = sessionFactory.openSession();
			session.beginTransaction();
			gameModelDAO = (GameModelDAO) session.get(GameModelDAO.class, gameName);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.getTransaction().commit();
			session.close();
		}
		return gameModelDAO.getGamejson();

	}
}
