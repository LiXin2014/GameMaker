package com.gamemaker.controllers;

public class GameConstants {
	public static final String JSONSUFFIX = ".json";
	public static final String BULLET_IMAGE = "/bullet-blue-icon.png";
	public static final int DEFAULT_X_SPEED = 3;
	public static final int DEFAULT_Y_SPEED = 3;
	public static final int HOP_Y_SPEED = 12;
	public static final int HOP_X_SPEED = 12;
	public static final String GAME_WIN = "You Won!!!";
	public static final String GAME_LOOSE = "You Lost!!!";

	// GameMaker view constants
	public static final String POPUP_MENU_EDIT_NAME = "Edit Sprite Properties";
	public static final String POPUP_MENU_DELETE = "Delete Sprite";
	public static final String POPUP_MENU_KEYBOARD_PRESS = "Add Key Pressed Event";
	public static final String POPUP_MENU_COLLISION = "Add Collision Event";
	public static final String POPUP_MENU_HOP = "Add Hop Event";
	public static final String POPUP_MENU_AUTO = "Add AutoMove";
	public static final String POPUP_MENU_GAME_INITIALIZATION = "Add Game Initialization Event";	
	public static final String HELP_TEXT_IMAGE_DRAGNDROP = "Choose an image and drag it to your game's staging area by keeping your mouse pressed.";
	public static final String HELP_TEXT_EVENT_ACTION_CHOICE_1 = "<html>To add an event, right click on a sprite and choose appropriate event. Hover on menu items for more help. </html>";
	public static final String HELP_TEXT_EVENT_ACTION_CHOICE_2_1 = "Select appropriate actions for your event below, associate with your game win or loose condition";
	public static final String HELP_TEXT_EVENT_ACTION_CHOICE_2_2 = "You may also choose to continue playing if resulting action doesn't decide game win or loose";
	public static final String HELP_TEXT_EVENT_ACTION_CHOICE_2_3 = "Finally, decide whether selected action will affect all sprites or just particular sprite";
	public static final String HELP_TEXT_ASSOCIATE_BUTTON = "To add event-action pair, click on attach.";
	public static final String HELP_TEXT_DEASSOCIATE_BUTTON = "To remove existing event action pair, click on event entry in summary box and then click detach.";
}
