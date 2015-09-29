package com.gamemaker.views;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.gamemaker.action.UnsupportedAction;
import com.gamemaker.controllers.MakerController;
import com.gamemaker.dnd.TransferableSprite;
import com.gamemaker.event.UnsupportedEvent;
import com.gamemaker.models.Sprite;

public interface GameMakerViewable {

	public abstract void activateViewForSprite(Sprite selectedSprite, String popupMenuEditName);

	// Handler to delete a sprite. Calls deleteSprite of the controller.
	// And then repaints the new set of Sprites
	public abstract void deleteSprite(Sprite sprite);

	public abstract List<Sprite> getAllSprites();

	public abstract JLabel getBkgImageLabel();

	public abstract JPanel getDropPanel();

	public abstract JTextField getNameVal();

	public abstract JPanel getRightPanel();

	public abstract String getSelectedIcon();

	public abstract TransferableSprite getTransferableSprite();

	public abstract void paint(Graphics g);

	public abstract void paintSprites(List<Sprite> sprites, boolean clearBeforePaint, boolean deleteSprite);

	public abstract void setActionsForSelectedEvent(Sprite sprite, String eventName);

	public abstract void setController(MakerController makerController2);

	public abstract void setDropPanel(JPanel dropPanel);

	public abstract void setLblGameName(String gameName);

	public abstract void setNameVal(JTextField nameVal);

	public abstract void setSelectedIcon(String selectedIcon);

	public abstract void setSprite(int xPos, int yPos) throws UnsupportedAction, UnsupportedEvent;

	// set the sprite name
	public abstract void setSpriteName(String str);

	public abstract void setSummaryTextArea(String summaryText);

	public abstract void setTransferableSprite(TransferableSprite transferableSprite);

	public abstract void setVisible(boolean visible);

	public abstract void validate();

}