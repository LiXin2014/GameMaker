package com.gamemaker.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.log4j.Logger;

import com.gamemaker.controllers.MakerController;
import com.gamemaker.helper.Dimensions;


@SuppressWarnings("serial")
public class StartView extends JFrame implements StartViewable {
	private final JButton btnGo;
	private final JButton btnPlay;
	private final JPanel contentPane;
	private JButton imageBtn;

	private final JPanel imagePanel;
	private final JScrollPane imageScrollPane;
	private final JLabel lblBackground1;
	private final JLabel lblEditviewAnexisting;
	private final Logger logger = Logger.getLogger(StartView.class);
	private MakerController makerController = null;
	private final JComboBox savedGameList;
	private final String selectedGameName = "";
	private String selectedIcon;
	private final JTextField txtNewGameField;
	JLayeredPane layeredPane;

	public StartView() {
		setTitle("GameMaker - Load or Create a game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, Dimensions.START_WINDOW_WIDTH, Dimensions.START_WINDOW_HEIGHT);
		setVisible(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);

		// Added Panel to saveand load game
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, Dimensions.START_WINDOW_PANEL_WIDTH, Dimensions.START_WINDOW_PANEL_HEIGHT);
		layeredPane.add(panel);
		panel.setLayout(null);

		JLabel lblStartingMakingA = new JLabel("Create a New Game");
		lblStartingMakingA.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStartingMakingA.setBounds(Dimensions.START_WINDOW_MAKING_LABEL_X, Dimensions.START_WINDOW_MAKING_LABEL_Y,
				Dimensions.START_WINDOW_MAKING_LABEL_WIDTH, Dimensions.START_WINDOW_MAKING_LABEL_HEIGHT);
		panel.add(lblStartingMakingA);

		JLabel lblNewGame = new JLabel("New Game");
		lblNewGame.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewGame.setBounds(Dimensions.START_WINDOW_GAME_LABEL_X, Dimensions.START_WINDOW_GAME_LABEL_Y,
				Dimensions.START_WINDOW_GAME_LABEL_WIDTH, Dimensions.START_WINDOW_GAME_LABEL_HEIGHT);
		lblNewGame.setVisible(true);
		panel.add(lblNewGame);

		txtNewGameField = new JTextField();
		txtNewGameField.setBounds(Dimensions.START_WINDOW_GAME_TEXTFIELD_X, Dimensions.START_WINDOW_GAME_TEXTFIELD_Y,
				Dimensions.START_WINDOW_GAME_TEXTFIELD_WIDTH, Dimensions.START_WINDOW_GAME_TEXTFIELD_HEIGHT);
		txtNewGameField.setVisible(true);
		panel.add(txtNewGameField);
		txtNewGameField.setColumns(10);

		lblBackground1 = new JLabel("Background");
		lblBackground1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBackground1.setBounds(Dimensions.START_WINDOW_GAME_BACKGROUND_LABEL_X,
				Dimensions.START_WINDOW_GAME_BACKGROUND_LABEL_Y, Dimensions.START_WINDOW_GAME_BACKGROUND_LABEL_WIDTH,
				Dimensions.START_WINDOW_GAME_BACKGROUND_LABEL_HEIGHT);
		lblBackground1.setVisible(true);
		panel.add(lblBackground1);

		imageScrollPane = new JScrollPane();
		imagePanel = new JPanel();
		imagePanel.setLayout(new FlowLayout());
		final List<String> fileNamesImgs = new ArrayList<String>();
		if (true) {
			URL urlString = this.getClass().getClassLoader().getResource("backgrounds.jar");
			ZipInputStream zip;
			try {
				zip = new ZipInputStream(urlString.openStream());
				ZipEntry ze = null;

				while ((ze = zip.getNextEntry()) != null) {
					String entryName = ze.getName();
					if (entryName.endsWith(".jpg") || (entryName.endsWith(".png"))) {
						fileNamesImgs.add("/" + entryName);
					}
				}
			} catch (IOException e1) {
			}
		}

		
		String[] fileListString = new String[fileNamesImgs.size()];
		fileListString = fileNamesImgs.toArray(fileListString);
		

		for (int j = 0; j < fileListString.length; j++) {
		
			URL imageUrl = this.getClass().getResource(fileListString[j]);
			ImageIcon icon12 = new ImageIcon(imageUrl);
			
			/*
			 * Check for images to be of proportion to be represented as with a
			 * size of 70*70. Also maintaining the aspect ratio of these images.
			 * Resize only to be applied if either the width or height is
			 * greater than 70 pixels.
			 */
			if ((icon12.getIconWidth() > 70) || (icon12.getIconHeight() > 70)) {
				Image resizedImage = icon12.getImage();
				if (icon12.getIconWidth() > icon12.getIconHeight()) {
					double imgWidth = icon12.getIconWidth();
					double x = (70 / imgWidth);
					double y = icon12.getIconHeight() * x;
					resizedImage = resizedImage.getScaledInstance(70, (int) y, Image.SCALE_SMOOTH);
				} else {
					double imgHeight = icon12.getIconHeight();
					double x = (70 / imgHeight);
					double y = icon12.getIconWidth() * x;
					resizedImage = resizedImage.getScaledInstance((int) y, 70, Image.SCALE_SMOOTH);
				}
				icon12 = new ImageIcon(resizedImage);
			}
			icon12.setDescription(fileListString[j]);
			imageBtn = new JButton(icon12);
			imageBtn.addActionListener(new SelectAdapter());
			imagePanel.add(imageBtn);
			imagePanel.repaint();
		}
		imageScrollPane.setViewportView(imagePanel);
		imageScrollPane.setBounds(Dimensions.START_WINDOW_IMAGE_SCROLLPANE_LABEL_X,
				Dimensions.START_WINDOW_IMAGE_SCROLLPANE_LABEL_Y, Dimensions.START_WINDOW_IMAGE_SCROLLPANE_LABEL_WIDTH,
				Dimensions.START_WINDOW_IMAGE_SCROLLPANE_LABEL_HEIGHT);
		panel.add(imageScrollPane);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, Dimensions.START_WINDOW_SEPARATOR_Y, Dimensions.START_WINDOW_SEPARATOR_WIDTH, 1);
		panel.add(separator);

		lblEditviewAnexisting = new JLabel("Edit/View an Existing Game");
		lblEditviewAnexisting.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEditviewAnexisting.setBounds(Dimensions.START_WINDOW_LOADING_LABEL_X,
				Dimensions.START_WINDOW_LOADING_LABEL_Y, Dimensions.START_WINDOW_LOADING_LABEL_WIDTH,
				Dimensions.START_WINDOW_LOADING_LABEL_HEIGHT);
		panel.add(lblEditviewAnexisting);

		JLabel lblLoadGame = new JLabel("Load Game");
		lblLoadGame.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoadGame.setBounds(Dimensions.START_WINDOW_LOAD_LABEL_X, Dimensions.START_WINDOW_LOAD_LABEL_Y,
				Dimensions.START_WINDOW_LOAD_LABEL_WIDTH, Dimensions.START_WINDOW_LOAD_LABEL_HEIGHT);
		panel.add(lblLoadGame);

		btnGo = new JButton("Create Game");
		btnGo.setBounds(Dimensions.START_WINDOW_GO_BUTTON_X, Dimensions.START_WINDOW_GO_BUTTON_Y,
				Dimensions.START_WINDOW_GO_BUTTON_WIDTH, Dimensions.START_WINDOW_GO_BUTTON_HEIGHT);
		btnGo.addActionListener(new GoBtnListener());
		panel.add(btnGo);

		btnPlay = new JButton("Play Game");
		btnPlay.setBounds(Dimensions.START_WINDOW_PLAY_BUTTON_X, Dimensions.START_WINDOW_PLAY_BUTTON_Y,
				Dimensions.START_WINDOW_GO_BUTTON_WIDTH, Dimensions.START_WINDOW_GO_BUTTON_HEIGHT);
		btnPlay.addActionListener(new PlayBtnListener());
		panel.add(btnPlay);
		

		setLocationRelativeTo(null);

		savedGameList = new JComboBox();
		savedGameList.setBounds(Dimensions.START_WINDOW_LOAD_BOX_X, Dimensions.START_WINDOW_LOAD_BOX_Y,
				Dimensions.START_WINDOW_LOAD_BOX_WIDTH, Dimensions.START_WINDOW_LOAD_BOX_HEIGHT);
		savedGameList.addActionListener(new SelectSavedGamesListener());
		savedGameList.setSelectedIndex(-1);
		panel.add(savedGameList);
	}

	@Override
	public void setController(MakerController makerController2) {
		this.makerController = makerController2;
	}


	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible)
			lazyInitialization();
	}

	private String[] getListOfGames() {
				return makerController.getGamesList();
	}

	private void lazyInitialization() {		
		savedGameList.setModel(new DefaultComboBoxModel(getListOfGames()));	
		savedGameList.setSelectedIndex(-1);
	}

	public class GoBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (savedGameList.getSelectedIndex() == -1) {
				makerController.getGameModel().setBkgURL(selectedIcon);
				makerController.gameMakerWindowIsRequested(txtNewGameField.getText(), false);
			} else {
				makerController.gameMakerWindowIsRequested(savedGameList.getSelectedItem().toString(), true);
			}
		}

	}

	public class PlayBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			makerController.gamePlayWindowIsRequested(savedGameList.getSelectedItem().toString());
		}

	}
	
	// selection helper for fetching image url from the button containing
	// the image in the jscrollpane
	public class SelectAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String desc = ((ImageIcon) button.getIcon()).getDescription();
			selectedIcon = desc;
		}

	}
	
	public class SelectSavedGamesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (savedGameList.getSelectedIndex() != -1)
			{
				btnGo.setText("Edit Game");
				btnGo.repaint();
			}
			
		}
	}
}
