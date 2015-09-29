package com.gamemaker.views;

import com.gamemaker.controllers.MakerController;

/**
 * For GameMaker, it defines a contract between GameMaker controller and a concerete start view.
 */
public interface StartViewable {
	public abstract void setController(MakerController makerController2);

	public abstract void setVisible(boolean visible);

	public abstract void validate();
}