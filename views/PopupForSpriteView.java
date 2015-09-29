/*
 * This class is the popup class for the Sprite properties.
 * It will be instantiated when a user right-clicks on any of the Sprites
 * which are on the Game Play panel.
 */

package com.gamemaker.views;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import com.gamemaker.controllers.GameConstants;

@SuppressWarnings("serial")
public class PopupForSpriteView extends JPopupMenu{
	
	private JMenuItem editItem;
	private JMenuItem deleteItem;
	private JMenuItem keyBoardPressEventItem;
	private JMenuItem collisionEventItem;
	private JMenuItem hopEventItem;
	private JMenuItem autoEventItem;
	private JMenuItem gameInitializationEventItem;
	
	public PopupForSpriteView() {
		editItem = new JMenuItem(GameConstants.POPUP_MENU_EDIT_NAME);
		deleteItem = new JMenuItem(GameConstants.POPUP_MENU_DELETE);
		keyBoardPressEventItem = new JMenuItem(GameConstants.POPUP_MENU_KEYBOARD_PRESS);
		collisionEventItem = new JMenuItem(GameConstants.POPUP_MENU_COLLISION);
		hopEventItem = new JMenuItem(GameConstants.POPUP_MENU_HOP);		
		autoEventItem = new JMenuItem(GameConstants.POPUP_MENU_AUTO);
		gameInitializationEventItem = new JMenuItem(GameConstants.POPUP_MENU_GAME_INITIALIZATION);
		
		this.add(editItem);
		this.add(keyBoardPressEventItem);
		this.add(collisionEventItem);
		this.add(hopEventItem);
		this.add(autoEventItem);
		this.add(gameInitializationEventItem);
		this.add(deleteItem);
	}
	
	public void registerActionListner(ActionListener actionListener){
		editItem.addActionListener(actionListener);
		deleteItem.addActionListener(actionListener);
		keyBoardPressEventItem.addActionListener(actionListener);
		collisionEventItem.addActionListener(actionListener);
		hopEventItem.addActionListener(actionListener);
		autoEventItem.addActionListener(actionListener);
		gameInitializationEventItem.addActionListener(actionListener);
		
	}
}
