package com.gamemaker.views;

import java.util.List;

import javax.swing.JButton;

import com.gamemaker.models.Sprite;

/**
 * 
 * GamePlayable interface defines the properties of GamePlayView that controller expects.
 *
 */
public interface GamePlayable {

	public void draw(List<Sprite> spritesData);

	public JButton getPlayButton();

	public void setGameName(String name);

	public void setMessage(String message);

	public void setPlayButton(JButton playButton);

	public void setScore(Integer score);

}