package com.gamemaker.dnd;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import com.gamemaker.models.Sprite;
import com.gamemaker.views.GameMakerViewable;

// mouselistener for drag and drop on right panel
public class DragNDropHelper implements MouseListener, MouseMotionListener {
	int dragX = 0;
	int dragY = 0;
	boolean spriteFound = false;
	private Sprite draggedSprite = new Sprite();
	private final List<Sprite> selectedSprites;
	private final GameMakerViewable gameMakerView;

	public DragNDropHelper(List<Sprite> sprites, GameMakerViewable gameMakerView) {
		this.gameMakerView = gameMakerView;
		this.selectedSprites = sprites;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		for (Sprite sprite : selectedSprites) {
			if (e.getX() >= sprite.getX() && e.getX() <= sprite.getX() + sprite.getWidth() && e.getY() >= sprite.getY()
					&& e.getY() <= sprite.getY() + sprite.getHeight()) {
				this.draggedSprite = sprite;
				spriteFound = true;
				sprite.setX(e.getX());
				sprite.setY(e.getY());
			}
		}
		if (!spriteFound) {
			draggedSprite = new Sprite();
			draggedSprite.setWidth(0);
			draggedSprite.setHeight(0);
		}
		draggedSprite.setX(e.getX());
		draggedSprite.setY(e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gameMakerView.getRightPanel().removeAll();
		gameMakerView.getRightPanel().add(gameMakerView.getBkgImageLabel());
		gameMakerView.paintSprites(gameMakerView.getAllSprites(), true, false);
		spriteFound = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}