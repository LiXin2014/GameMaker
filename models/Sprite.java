package com.gamemaker.models;

import java.util.ArrayList;
import java.util.HashMap;

import com.gamemaker.action.Action;
import com.gamemaker.event.Event;

/**
 * Sprite is a central model. Everything that is needed for a sprite to perform,
 * display, behave should be stored here.
 */
public class Sprite {
	private String Name;
	private int x;
	private int y;
	private int Vx;
	private int Vy;
	private int width;
	private int height;
	private int initX;
	private int initY;
	private boolean isVanish;
	private String imageName;
	private HashMap<Event, ArrayList<Action>> eventActionPairs;
	private HashMap<String, ArrayList<Action>> specialActions;

	public Sprite() {
		Name = "";
		x = 0;
		y = 0;
		Vx = 0;
		Vy = 0;
		width = 0;
		height = 0;
		isVanish = false;
		imageName = "";
		eventActionPairs = new HashMap<Event, ArrayList<Action>>();
		specialActions = new HashMap<String, ArrayList<Action>>();
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public boolean isVanish() {
		return isVanish;
	}

	public void setVanish(boolean isVanish) {
		this.isVanish = isVanish;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVx() {
		return Vx;
	}

	public void setVx(int vx) {
		Vx = vx;
	}

	public int getVy() {
		return Vy;
	}

	public void setVy(int vy) {
		Vy = vy;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getInitX() {
		return initX;
	}

	public void setInitX(int initX) {
		this.initX = initX;
	}

	public int getInitY() {
		return initY;
	}

	public void setInitY(int initY) {
		this.initY = initY;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void update() {
		this.setX(this.getX() + Vx);
		this.setY(this.getY() + Vy);
	}

	public HashMap<Event, ArrayList<Action>> getNewEventActionPairs() {
		return eventActionPairs;
	}

	public void setNewEventActionPairs(HashMap<Event, ArrayList<Action>> newEventActionPairs) {
		this.eventActionPairs = newEventActionPairs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sprite other = (Sprite) obj;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		return true;
	}

	public HashMap<String, ArrayList<Action>> getSpecialActions() {
		return specialActions;
	}

	public void setSpecialActions(HashMap<String, ArrayList<Action>> specialActions) {
		this.specialActions = specialActions;
	}

}
