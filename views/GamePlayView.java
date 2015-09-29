package com.gamemaker.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.gamemaker.controllers.PlayController;
import com.gamemaker.helper.Dimensions;
import com.gamemaker.models.Sprite;

public class GamePlayView extends JFrame implements GamePlayable {

	private static final long serialVersionUID = 1L;
	private PlayController controller = null;
	private ArrayList<Sprite> dynamicSprites;
	private String gameName;

	private final JLabel msgLabel;

	private JButton playButton;
	private JComboBox saveLoadCB;
	private final JLabel scoreLable;
	private List<Sprite> spritesData;

	private final JButton stopButton;

	public GamePlayView(PlayController controller) {
		this.controller = controller;
		setTitle("Game Maker: Play game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH, Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
				Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT));

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(20, 20));

		GamePanel gamePanelObj = new GamePanel();
		gamePanelObj.setBackground(Color.WHITE);
		gamePanelObj.setMinimumSize(new Dimension(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
				Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT - 50));
		gamePanelObj.setPreferredSize(new Dimension(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
				Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT - 50));
		gamePanelObj.revalidate();
		contentPane.add(gamePanelObj, BorderLayout.CENTER);

		JSeparator jSeparator = new JSeparator();
		contentPane.add(jSeparator, BorderLayout.SOUTH);

		msgLabel = new JLabel();
		msgLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		msgLabel.setBounds(200, 200, 200, 50);

		JPanel panelLeft = new JPanel();
		panelLeft.setBackground(Color.LIGHT_GRAY);
		panelLeft.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));
		contentPane.add(panelLeft, BorderLayout.SOUTH);

		playButton = new JButton();
		playButton.setText("Play");
		playButton.setFocusable(false);
		playButton.setEnabled(true);
		panelLeft.add(playButton);
		playButton.addActionListener(new playListener());

		stopButton = new JButton();
		stopButton.setText("Stop");
		stopButton.setFocusable(false);
		stopButton.setEnabled(true);
		panelLeft.add(stopButton);
		stopButton.addActionListener(new stopListener());

		scoreLable = new JLabel("Score: ");
		scoreLable.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		panelLeft.add(scoreLable);

		addKeyListener(new StrokeAdapter());

		contentPane.validate();
		setLocationRelativeTo(null);
	}

	@Override
	public void draw(List<Sprite> spritesData) {
		this.spritesData = spritesData;
		repaint();
	}

	@Override
	public JButton getPlayButton() {
		return playButton;
	}

	@Override
	public void setGameName(String name) {
		this.gameName = name;
		this.setTitle(name);
	}

	@Override
	public void setMessage(String message) {
		msgLabel.setText(message);
	}

	@Override
	public void setPlayButton(JButton playButton) {
		this.playButton = playButton;
	}

	@Override
	public void setScore(Integer score) {
		scoreLable.setText("Score: " + score.toString());
	}

	private JButton getStopButton() {
		return stopButton;
	}

	public class GamePanel extends JPanel {

		private static final long serialVersionUID = 1L;
		private final Graphics2D bufferedGraphics;
		private final BufferedImage image;

		public GamePanel() {
			image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			bufferedGraphics = image.createGraphics();
			this.setBackground(Color.LIGHT_GRAY);
			this.setLayout(null);
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			this.removeAll();
			this.add(msgLabel);
			bufferedGraphics.setColor(Color.LIGHT_GRAY);
			paintSprites(spritesData);
			paintSprites(dynamicSprites);
			this.validate();
			this.repaint();
		}

		private void paintSprites(List<Sprite> sprites) {

			if (sprites != null) {
				for (int i = 0; i < sprites.size(); i++) {

					// get all sprites with getresource
					URL imagePath = this.getClass().getResource(sprites.get(i).getImageName().toString());
					ImageIcon icon = new ImageIcon(imagePath);
					JLabel picLabel = new JLabel(icon);
					picLabel.setBounds(sprites.get(i).getX(), sprites.get(i).getY(), icon.getIconWidth(),
							icon.getIconHeight());
					this.add(picLabel);
				}

				ImageIcon bgImageIcon = new ImageIcon(this.getClass()
						.getResource(controller.getGameModel().getBkgURL()));
				Image reImage = bgImageIcon.getImage();
				reImage = reImage.getScaledInstance(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
						Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT, Image.SCALE_SMOOTH);
				bgImageIcon = new ImageIcon(reImage);
				JLabel bkgImageLabel = new JLabel(bgImageIcon);
				bkgImageLabel.setBounds(0, Dimensions.GAME_MAKER_RIGHT_PANEL_Y,
						Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH, Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);
				this.add(bkgImageLabel);

			}
		}
	}

	class playListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (getPlayButton().getText().equals("Play")) {
				getPlayButton().setText("Pause");
				controller.playIsRequested();
				draw(spritesData);
				setMessage("");
			} else if (getPlayButton().getText().equals("Pause")) {
				getPlayButton().setText("Play");
				controller.pauseIsRequested();
			}
			getStopButton().setEnabled(true);
		}
	}

	class stopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			controller.stopIsRequested();
			getPlayButton().setEnabled(true);
			getPlayButton().setText("Play");
			setMessage("");
			getStopButton().setEnabled(false);
		}
	}

	class StrokeAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent ke) {
			controller.keyIsPressed(ke);
		}

		@Override
		public void keyReleased(KeyEvent ke) {

		}
	}
}
