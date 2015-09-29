package com.gamemaker.action;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.gamemaker.controllers.GameState;
import com.gamemaker.models.Sprite;

public class PlayCollisionSoundAction extends Action {

	public PlayCollisionSoundAction(GameState state) {
		super(ActionNameConstants.PLAY_COLLISION_SOUND_ACTION, state);
	}

	@Override
	public GameState perform(Sprite sprite) {
		try {
			File soundFile = new File("collision.wav");
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(soundFile);

			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);

			if (AudioSystem.isLineSupported(info)) {
				Clip clip = (Clip) AudioSystem.getLine(info);

				clip.open(audioInputStream);
				clip.start();
			}
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return state;
	}

}
