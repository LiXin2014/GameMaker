package com.gamemaker.dnd;

import java.awt.Cursor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;

import javax.swing.JButton;

import com.gamemaker.views.GameMakerViewable;

// selection helper for fetching image url from the button containing
// the image in the jscrollpane
public class SelectAdapter implements DragGestureListener {
	
	private GameMakerViewable gameMakerView;
	
	public SelectAdapter(GameMakerViewable gameMakerView) {
		this.gameMakerView = gameMakerView;;
	}
	@Override
	public void dragGestureRecognized(DragGestureEvent event) {
		Cursor cursor = null;
		JButton lblSprite = (JButton) event.getComponent();

		if (event.getDragAction() == DnDConstants.ACTION_COPY) {
			cursor = DragSource.DefaultCopyDrop;
		}

		gameMakerView.setTransferableSprite(new TransferableSprite(lblSprite));
		event.startDrag(cursor, gameMakerView.getTransferableSprite());
	}

}

