package com.gamemaker.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.models.Sprite;

// MouseListener for right click of sprites
public class PopupForSpriteListener extends MouseAdapter {

	boolean spriteFound = false;
	List<Sprite> sprites;
	private final GameMakerViewable gameMakerView;
	private Sprite selectedSprite;
	PopupForSpriteView popupForSprite = new PopupForSpriteView();

	public PopupForSpriteListener(GameMakerViewable gameMakerView) {
		this.gameMakerView = gameMakerView;
		this.sprites = gameMakerView.getAllSprites();
		popupForSprite.registerActionListner(new MenuActionListener());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
			spriteFound = checkForMousePosition(e);
			if (spriteFound)
				showPopup(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			spriteFound = checkForMousePosition(e);
			if (spriteFound)
				showPopup(e);
		}
	}

	// Check mouse position - if the mouse is right-clicked
	// where a sprite is present, return true.
	private boolean checkForMousePosition(MouseEvent e) {
		for (Sprite sprite : sprites) {
			if (e.getX() >= sprite.getX() && e.getX() <= sprite.getX() + sprite.getWidth() && e.getY() >= sprite.getY()
					&& e.getY() <= sprite.getY() + sprite.getHeight()) {
				selectedSprite = sprite;
				return true;
			}
		}
		return false;
	}

	private void showPopup(MouseEvent e) {
		popupForSprite.show(e.getComponent(), e.getX(), e.getY());
	}

	class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals(GameConstants.POPUP_MENU_EDIT_NAME)) {
				gameMakerView.activateViewForSprite(selectedSprite, GameConstants.POPUP_MENU_EDIT_NAME);
			} else if (e.getActionCommand().equals(GameConstants.POPUP_MENU_DELETE)) {
				gameMakerView.deleteSprite(selectedSprite);
			} else if (e.getActionCommand().equals(GameConstants.POPUP_MENU_KEYBOARD_PRESS)) {
				gameMakerView.setActionsForSelectedEvent(selectedSprite, GameConstants.POPUP_MENU_KEYBOARD_PRESS);
			} else if (e.getActionCommand().equals(GameConstants.POPUP_MENU_COLLISION)) {
				gameMakerView.setActionsForSelectedEvent(selectedSprite, GameConstants.POPUP_MENU_COLLISION);
			} else if (e.getActionCommand().equals(GameConstants.POPUP_MENU_HOP)) {
				gameMakerView.setActionsForSelectedEvent(selectedSprite, GameConstants.POPUP_MENU_HOP);
			} else if (e.getActionCommand().equals(GameConstants.POPUP_MENU_AUTO)) {
				gameMakerView.setActionsForSelectedEvent(selectedSprite, GameConstants.POPUP_MENU_AUTO);
			} else if (e.getActionCommand().equals(GameConstants.POPUP_MENU_GAME_INITIALIZATION)) {
				gameMakerView.setActionsForSelectedEvent(selectedSprite, GameConstants.POPUP_MENU_GAME_INITIALIZATION);
			}
		}
	}
}