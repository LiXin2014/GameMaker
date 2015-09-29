package com.gamemaker.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.gamemaker.action.Action;
import com.gamemaker.action.UnsupportedAction;
import com.gamemaker.controllers.GameConstants;
import com.gamemaker.controllers.GameState;
import com.gamemaker.controllers.MakerController;
import com.gamemaker.dnd.DragNDropHelper;
import com.gamemaker.dnd.MyDropTargetListImp;
import com.gamemaker.dnd.SelectAdapter;
import com.gamemaker.dnd.TransferableSprite;
import com.gamemaker.event.Event;
import com.gamemaker.event.UnsupportedEvent;
import com.gamemaker.helper.ActionDeserializer;
import com.gamemaker.helper.Dimensions;
import com.gamemaker.helper.EventActionEntryConverter;
import com.gamemaker.helper.EventeDeserializer;
import com.gamemaker.models.Sprite;
import com.gamemaker.views.gamemaker.SpriteBasicInfoView;
import com.gamemaker.views.gamemaker.SpriteImageScrollerView;

/*
 * GameMakerView will house the form to add sprites and also
 * the actual grid to place objects onto it to build the game map
 */

@SuppressWarnings("serial")
public class GameMakerView extends JFrame implements GameMakerViewable {

	public static String[] fileList(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		String[] lis = new String[listOfFiles.length];
		for (int i = 0; i < listOfFiles.length; i++) {
			lis[i] = listOfFiles[i].toString();
		}
		return lis;
	}

	public JPanel contentPane;
	private final JList actionTypeList;
	private final JButton associateBtn;
	private final JList associateWithSpriteList;
	private final JLabel bkgImageLabel;
	private final JPanel btnPanel2;
	private final JButton btnReset;
	private final JButton btnSave;
	private final JButton btnTest;
	private int count;
	private final JButton deassociateBtn;
	private DragNDropHelper dragNDropHelper;
	private JPanel dropPanel;
	private HashMap<String, ArrayList<String>> eventActionListTemp;

	private final JLabel eventType;;
	private final JLabel eventTypeLabel;

	private final JList gameConditionList;
	private JButton imageBtn;

	private final JPanel leftBottomSubPanel;
	private final JPanel leftImagePanel;
	private final JPanel leftMasterPanel;
	private final JPanel leftPanel;
	private final JPanel leftSubPanel;
	private final JPanel leftSubPanel1;
	private final JPanel leftTopPanel;
	private final Logger logger;

	private MakerController makerController;
	private JTextField nameVal;
	private final JPanel rightPanel;
	private String selectedIcon;

	private final List<Sprite> selectedSprites = new ArrayList<Sprite>();
	private final SpriteBasicInfoView spriteBasicInfoView;
	private final SpriteImageScrollerView spriteImageScrollerView;

	private final JLabel spritesToAssociateWithLabel;
	private final JLabel summaryLabel;
	private final JList summaryTextArea;

	private TransferableSprite transferableSprite;

	public GameMakerView() {
		this.setPreferredSize(getSize());

		logger = Logger.getLogger(this.getClass());
		BasicConfigurator.configure();

		count = 0;
		getContentPane().setLayout(new GridLayout(1, 2));
		getContentPane().setBackground(Color.lightGray);
		leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		getContentPane().add(leftPanel);

		// adding image jlabel and image display jscrollpane
		leftImagePanel = new JPanel();
		spriteImageScrollerView = new SpriteImageScrollerView(leftImagePanel);
		leftPanel.add(leftImagePanel);

		leftTopPanel = new JPanel();
		spriteBasicInfoView = new SpriteBasicInfoView(leftTopPanel);
		leftPanel.add(leftTopPanel);

		leftSubPanel = new JPanel();
		leftSubPanel.setLayout(new BoxLayout(leftSubPanel, BoxLayout.Y_AXIS));
		leftSubPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Step 3: Choose Event and Actions", TitledBorder.LEADING, TitledBorder.TOP, new Font("Serif",
						Font.ITALIC, 14), new Color(0, 0, 0)));

		JPanel leftSubPanel2 = new JPanel();
		leftSubPanel2.setLayout(new GridLayout(1, 1));
		JLabel helpMenuForEventActions = new JLabel(GameConstants.HELP_TEXT_EVENT_ACTION_CHOICE_1);
		leftSubPanel2.add(helpMenuForEventActions);
		leftSubPanel.add(leftSubPanel2);

		leftSubPanel1 = new JPanel();
		leftSubPanel1.setLayout(new GridLayout(1, 2));

		// adding event type jlabel and jcombobox
		eventTypeLabel = new JLabel("Selected Event:");
		eventTypeLabel.setFont(new Font("serif", Font.ITALIC, 15));
		leftSubPanel1.add(eventTypeLabel);

		eventType = new JLabel();
		leftSubPanel1.add(eventType);
		leftSubPanel.add(leftSubPanel1);

		JSeparator separator = new JSeparator();
		leftSubPanel.add(separator);

		JPanel leftMasterPanel1 = new JPanel();
		leftMasterPanel = new JPanel();
		leftMasterPanel.setLayout(new GridLayout(1, 3, 5, 5));
		leftMasterPanel1.setLayout(new GridLayout(1, 3, 5, 5));

		// adding the action jlabel and game condition jlabel
		JLabel actionLabel = new JLabel("Select Actions");
		actionLabel.setFont(new Font("serif", Font.ITALIC, 15));
		actionLabel.setToolTipText(GameConstants.HELP_TEXT_EVENT_ACTION_CHOICE_2_1);

		JLabel gameCondLabel = new JLabel("Select GameState");
		gameCondLabel.setFont(new Font("serif", Font.ITALIC, 15));
		gameCondLabel.setToolTipText(GameConstants.HELP_TEXT_EVENT_ACTION_CHOICE_2_2);

		spritesToAssociateWithLabel = new JLabel("Select applicable sprites");
		spritesToAssociateWithLabel.setFont(new Font("serif", Font.ITALIC, 15));
		spritesToAssociateWithLabel.setToolTipText(GameConstants.HELP_TEXT_EVENT_ACTION_CHOICE_2_3);

		actionTypeList = new JList();
		actionTypeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actionTypeList.setToolTipText(GameConstants.HELP_TEXT_EVENT_ACTION_CHOICE_2_1);
		actionTypeList.setBackground(Color.WHITE);
		actionTypeList.setEnabled(false);

		gameConditionList = new JList();
		gameConditionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gameConditionList.setToolTipText(GameConstants.HELP_TEXT_EVENT_ACTION_CHOICE_2_2);
		gameConditionList.setBackground(Color.WHITE);
		gameConditionList.setEnabled(false);

		associateWithSpriteList = new JList();
		associateWithSpriteList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		associateWithSpriteList.setToolTipText(GameConstants.HELP_TEXT_EVENT_ACTION_CHOICE_2_3);
		associateWithSpriteList.setBackground(Color.WHITE);
		associateWithSpriteList.setEnabled(false);

		leftMasterPanel1.add(actionLabel);
		leftMasterPanel1.add(gameCondLabel);
		leftMasterPanel1.add(spritesToAssociateWithLabel);
		leftSubPanel.add(leftMasterPanel1);
		leftMasterPanel.add(new JScrollPane(actionTypeList));
		leftMasterPanel.add(new JScrollPane(gameConditionList));
		leftMasterPanel.add(new JScrollPane(associateWithSpriteList));
		leftSubPanel.add(leftMasterPanel);

		// adding the associate and deassociate button
		JPanel btnSubPanel = new JPanel();
		btnSubPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		associateBtn = new JButton("Attach");
		associateBtn.addActionListener(new AssociateBtnActionListener());
		associateBtn.setToolTipText(GameConstants.HELP_TEXT_ASSOCIATE_BUTTON);
		associateBtn.setFont(new Font("serif", Font.ITALIC, 15));
		btnSubPanel.add(associateBtn);
		deassociateBtn = new JButton("Detach");
		deassociateBtn.setFont(new Font("serif", Font.ITALIC, 15));
		deassociateBtn.setToolTipText(GameConstants.HELP_TEXT_DEASSOCIATE_BUTTON);
		deassociateBtn.addActionListener(new DeassociateBtnActionListener());
		btnSubPanel.add(deassociateBtn);
		leftSubPanel.add(btnSubPanel);

		leftPanel.add(leftSubPanel);

		// adding summary to display event-action-game condition pairs selectd
		// for each sprite

		leftBottomSubPanel = new JPanel();
		leftBottomSubPanel.setLayout(new GridLayout(1, 1, 5, 5));

		summaryLabel = new JLabel("Summary:");
		summaryLabel.setFont(new Font("serif", Font.ITALIC, 15));

		summaryTextArea = new JList();
		summaryTextArea.setForeground(Color.BLACK);
		summaryTextArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		summaryTextArea.setBackground(Color.WHITE);
		leftBottomSubPanel.add(new JScrollPane(summaryTextArea));
		leftBottomSubPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Recent Activities", TitledBorder.LEADING, TitledBorder.TOP, new Font("Serif", Font.ITALIC, 14),
				new Color(0, 0, 0)));

		this.setBounds(0, 0, 940, 685);
		leftPanel.setBackground(Color.WHITE);
		leftPanel.add(leftBottomSubPanel);

		rightPanel = new JPanel();
		// setting right panel dimensions/bounds
		getRightPanel().setBounds(600, 0, 600, 700);

		getContentPane().add(getRightPanel());
		getRightPanel().setLayout(null);

		// modified to get background using getresource method.
		bkgImageLabel = new JLabel();
		bkgImageLabel.setBounds(0, Dimensions.GAME_MAKER_RIGHT_PANEL_Y, Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
				Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT);

		getRightPanel().add(bkgImageLabel);

		btnPanel2 = new JPanel();
		btnPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveBtnActionListener());
		btnSave.setFont(new Font("serif", Font.ITALIC, 15));
		btnPanel2.add(btnSave);

		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("serif", Font.ITALIC, 15));

		btnReset.addActionListener(new ResetBtnActionListener());
		btnPanel2.add(btnReset);

		btnTest = new JButton("Test");
		btnTest.addActionListener(new TestBtnActionListener());
		btnTest.setFont(new Font("serif", Font.ITALIC, 15));

		btnPanel2.add(btnTest);
		leftPanel.add(btnPanel2);

		setLocationRelativeTo(null);

	}

	@Override
	public void activateViewForSprite(Sprite selectedSprite, String popupMenuEditName) {
		if (GameConstants.POPUP_MENU_EDIT_NAME.equals(popupMenuEditName)) {
			this.spriteBasicInfoView.enableSpriteNameText();
			this.spriteBasicInfoView.setSpriteName("");
		}
		Sprite sprite = makerController.loadSavedSprites(selectedSprite.getName());
		this.selectedSprites.clear();
		this.selectedSprites.add(sprite);
	}

	// Handler to delete a sprite. Calls deleteSprite of the controller.
	// And then repaints the new set of Sprites
	@Override
	public void deleteSprite(Sprite sprite) {
		makerController.deleteSprite(sprite, getTitle());
		selectedSprites.remove(sprite);
		paintSprites(makerController.getSpritesData(), true, true);
		resetFormHelper();
	}

	@Override
	public List<Sprite> getAllSprites() {
		return makerController.getSpritesData();
	}

	@Override
	public JLabel getBkgImageLabel() {
		return bkgImageLabel;
	}

	@Override
	public JPanel getDropPanel() {
		return dropPanel;
	}

	@Override
	public JTextField getNameVal() {
		return nameVal;
	}

	@Override
	public JPanel getRightPanel() {
		return rightPanel;
	}

	@Override
	public String getSelectedIcon() {
		return selectedIcon;
	}

	@Override
	public TransferableSprite getTransferableSprite() {
		return transferableSprite;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

	@Override
	public void paintSprites(List<Sprite> sprites, boolean clearBeforePaint, boolean deleteSprite) {
		if (deleteSprite) {
			getRightPanel().removeAll();
			getRightPanel().add(bkgImageLabel);
		}
		if (clearBeforePaint) {
			getRightPanel().repaint();
			getRightPanel().revalidate();
		}
		for (Sprite sprite : sprites) {
			paintSprite(sprite.getImageName(), sprite.getX(), sprite.getY());
		}
	}

	@Override
	public void setActionsForSelectedEvent(Sprite sprite, String eventName) {
		activateViewForSprite(sprite, eventName);
		for (String key : eventActionListTemp.keySet()) {
			if (eventName.equals(key)) {
				eventType.setText(eventName);
				eventType.setVisible(true);
				actionTypeList.setEnabled(true);
				gameConditionList.setEnabled(true);
				associateWithSpriteList.setEnabled(true);
				actionTypeList.setListData(eventActionListTemp.get(key).toArray());
			}
		}
	}

	@Override
	public void setController(MakerController makerController2) {
		this.makerController = makerController2;
		eventActionListTemp = makerController.getEventActionPairs();
	}

	@Override
	public void setDropPanel(JPanel dropPanel) {
		this.dropPanel = dropPanel;
	}

	@Override
	public void setLblGameName(String gameName) {
		this.setTitle(gameName);
	}

	@Override
	public void setNameVal(JTextField nameVal) {
		this.nameVal = nameVal;
	}

	@Override
	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	@Override
	public void setSprite(int xPos, int yPos) throws UnsupportedAction, UnsupportedEvent {
		URL imageUrl = this.getClass().getResource(selectedIcon);
		ImageIcon draggedImageIcon = paintSprite(selectedIcon, xPos, yPos);

		Sprite sprite = new Sprite();
		sprite.setX(xPos);
		sprite.setY(yPos);
		sprite.setInitX(xPos);
		sprite.setInitY(yPos);
		sprite.setHeight(draggedImageIcon.getIconHeight());
		sprite.setWidth(draggedImageIcon.getIconWidth());

		sprite.setImageName(selectedIcon);
		selectedSprites.add(sprite);

		List<Sprite> sprites = getGeneratedSpriteDetails();
		makerController.addAll(new ArrayList<Sprite>(sprites), getTitle());
	}

	// set the sprite name
	@Override
	public void setSpriteName(String str) {
		spriteBasicInfoView.setSpriteName(str);
	}

	@Override
	public void setSummaryTextArea(String summaryText) {
		DefaultListModel model = new DefaultListModel();
		for (int i = 0; i < summaryTextArea.getModel().getSize(); i++) {
			EventActionEntryConverter c = (EventActionEntryConverter) summaryTextArea.getModel().getElementAt(i);
			// String previousRecord = (String)
			// summaryTextArea.getModel().getElementAt(i);
			model.addElement(c);
		}
		EventActionEntryConverter c = new EventActionEntryConverter(summaryText);
		model.addElement(c);
		summaryTextArea.setModel(model);
	}

	@Override
	public void setTransferableSprite(TransferableSprite transferableSprite) {
		this.transferableSprite = transferableSprite;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			lazyInitialization();
		}
	}

	private void clearEventActionLists() {
		DefaultListModel model = new DefaultListModel();
		eventType.setVisible(false);
		actionTypeList.setEnabled(false);
		gameConditionList.setEnabled(false);
		actionTypeList.setModel(model);
		associateWithSpriteList.setModel(spriteListModel());
	}

	// set the event type combobox items
	private String[] eventTypeComboList() {
		String[] eventTempList = new String[eventActionListTemp.size() + 1];
		int i = 0;
		eventTempList[i++] = "None";
		for (String key : eventActionListTemp.keySet()) {
			eventTempList[i++] = key;
		}
		return eventTempList;
	}

	private List<Sprite> getGeneratedSpriteDetails() throws UnsupportedAction, UnsupportedEvent {
		HashMap<Event, ArrayList<Action>> newEventActionPairs = new HashMap<Event, ArrayList<Action>>();
		HashMap<String, ArrayList<Action>> specialActions = new HashMap<String, ArrayList<Action>>();
		for (int i = 0; i < summaryTextArea.getModel().getSize(); i++) {
			EventActionEntryConverter c = (EventActionEntryConverter) summaryTextArea.getModel().getElementAt(i);
			String tempStr = c.getValue();
			String[] strList = tempStr.split("-");
			Event event = EventeDeserializer.getEventObjectFromString(strList[0]);
			Action action = ActionDeserializer.getActionObjectFromString(strList[1], strList[2]);
			if (strList.length >= 4 && strList[3].equals("") == false) {
				String[] associatedSpritesStr = strList[3].split(":");
				for (String str : associatedSpritesStr) {
					if (specialActions.containsKey(str) == false) {
						ArrayList<Action> actions = new ArrayList<Action>();
						specialActions.put(str, actions);
					}
					ArrayList<Action> actions = specialActions.get(str);
					actions.add(action);
					specialActions.put(str, actions);
				}
			}

			if (newEventActionPairs.containsKey(event) == false) {
				ArrayList<Action> actions = new ArrayList<Action>();
				newEventActionPairs.put(event, actions);
			}
			ArrayList<Action> actions = newEventActionPairs.get(event);
			actions.add(action);
			newEventActionPairs.put(event, actions);
		}

		for (Sprite sprite : selectedSprites) {
			String spriteTempName = new String(spriteBasicInfoView.getSpriteName());
			String[] nameArray = spriteBasicInfoView.getSpriteName().split("_");
			if (nameArray.length == 1 || nameArray[0].isEmpty()) {
				// That means we do not have extra name identifier. Add it.
				spriteTempName = spriteBasicInfoView.getSpriteName() + "_" + count++;
			}
			sprite.setName(spriteTempName);
			sprite.setNewEventActionPairs(newEventActionPairs);
			sprite.setSpecialActions(specialActions);
		}

		return selectedSprites;
	}

	private void lazyInitialization() {
		final List<String> fileNamesImgs = new ArrayList<String>();
		if (true) {
			URL urlString = this.getClass().getClassLoader().getResource("sprites.jar");
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
			if ((icon12.getIconWidth() > 45) || (icon12.getIconHeight() > 45)) {
				Image resizedImage = icon12.getImage();
				if (icon12.getIconWidth() > icon12.getIconHeight()) {
					double imgWidth = icon12.getIconWidth();
					double x = (45 / imgWidth);
					double y = icon12.getIconHeight() * x;
					resizedImage = resizedImage.getScaledInstance(45, (int) y, Image.SCALE_SMOOTH);
				} else {
					double imgHeight = icon12.getIconHeight();
					double x = (45 / imgHeight);
					double y = icon12.getIconWidth() * x;
					resizedImage = resizedImage.getScaledInstance((int) y, 45, Image.SCALE_SMOOTH);
				}
				icon12 = new ImageIcon(resizedImage);
			}
			icon12.setDescription(fileListString[j]);
			imageBtn = new JButton(icon12);
			imageBtn.setToolTipText(GameConstants.HELP_TEXT_IMAGE_DRAGNDROP);
			spriteImageScrollerView.add(imageBtn);
		}

		eventType.setVisible(true);
		eventType.validate();

		gameConditionList.removeAll();
		DefaultListModel listModel = new DefaultListModel();
		String[] gameStates = makerController.gameStateStr();
		for (int i = 0; i < gameStates.length; i++) {
			listModel.addElement(gameStates[i]);
		}
		gameConditionList.setModel(listModel);

		associateWithSpriteList.setModel(spriteListModel());

		ImageIcon bgImageIcon = new ImageIcon(this.getClass().getResource(makerController.getGameModel().getBkgURL()));
		Image reImage = bgImageIcon.getImage();
		reImage = reImage.getScaledInstance(Dimensions.GAME_MAKER_RIGHT_PANEL_WIDTH,
				Dimensions.GAME_MAKER_RIGHT_PANEL_HEIGHT, Image.SCALE_SMOOTH);
		bgImageIcon = new ImageIcon(reImage);
		bkgImageLabel.setIcon(bgImageIcon);

		DragSource ds = new DragSource();
		for (Component JlblSprite : spriteImageScrollerView.getComponents()) {
			ds.createDefaultDragGestureRecognizer(JlblSprite, DnDConstants.ACTION_COPY, new SelectAdapter(this));
		}
		new MyDropTargetListImp(this);

		dragNDropHelper = new DragNDropHelper(getAllSprites(), this);
		getRightPanel().addMouseListener(dragNDropHelper);
		getRightPanel().addMouseMotionListener(dragNDropHelper);
		getRightPanel().addMouseListener(new PopupForSpriteListener(this));
	}

	private ImageIcon paintSprite(String selectedIcon, int x, int y) {
		URL imageUrl = this.getClass().getResource(selectedIcon);
		ImageIcon draggedImageIcon = new ImageIcon(imageUrl);
		JLabel imageLabelRightPnl = new JLabel(draggedImageIcon);

		imageLabelRightPnl.setLocation(x, y);
		imageLabelRightPnl.setBounds(x, y, draggedImageIcon.getIconWidth(), draggedImageIcon.getIconHeight());

		getRightPanel().add(imageLabelRightPnl);
		getRightPanel().add(bkgImageLabel);
		getRightPanel().repaint();
		getRightPanel().revalidate();

		return draggedImageIcon;
	}

	// resetFormHelper, this method is called by the reset button and save
	// button
	// listeners, it clears all the form fields
	private void resetFormHelper() {
		spriteBasicInfoView.setSpriteName("");
		spriteBasicInfoView.disableSpriteNameText();
		DefaultListModel model = new DefaultListModel();
		clearEventActionLists();
		associateWithSpriteList.setModel(spriteListModel());
		summaryTextArea.setModel(model);
	}

	// sprite list model builder helper
	private DefaultListModel spriteListModel() {
		List<String> temp = new ArrayList<String>();
		temp = makerController.getAllSprites();
		DefaultListModel associatedSpritesModel = new DefaultListModel();
		for (String spriteName : temp) {
			associatedSpritesModel.addElement(spriteName);
		}
		return associatedSpritesModel;
	}

	// action listener for associate button
	public class AssociateBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String gameState = null;
			if (gameConditionList.getSelectedValue() == null) {
				gameState = GameState.GAME_CONTINUE.toString();
			} else {
				gameState = gameConditionList.getSelectedValue().toString();
			}
			String tempEventActionGameCond = eventType.getText() + "-" + actionTypeList.getSelectedValue() + "-"
					+ gameState + "-";
			if (associateWithSpriteList.getSelectedValues().length > 0) {
				for (int j = 0; j < associateWithSpriteList.getSelectedValues().length; j++) {
					tempEventActionGameCond = tempEventActionGameCond + associateWithSpriteList.getSelectedValues()[j]
							+ ":";
				}
			} else {
				tempEventActionGameCond += "";
			}

			DefaultListModel tempModel = new DefaultListModel();
			for (int i = 0; i < summaryTextArea.getModel().getSize(); i++) {
				EventActionEntryConverter c = (EventActionEntryConverter) summaryTextArea.getModel().getElementAt(i);
				tempModel.addElement(c);
			}
			EventActionEntryConverter c = new EventActionEntryConverter(tempEventActionGameCond);
			tempModel.addElement(c);
			summaryTextArea.setModel(tempModel);
			clearEventActionLists();
		}
	}

	// action listener for deassociate button
	public class DeassociateBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DefaultListModel model = (DefaultListModel) summaryTextArea.getModel();
			int selectedIndex = summaryTextArea.getSelectedIndex();
			if (selectedIndex != -1) {
				model.remove(selectedIndex);
			}
			summaryTextArea.setModel(model);
		}

	}

	// action listener for reset button
	public class ResetBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			resetFormHelper();
			spriteListModel();
		}

	}

	// action listener for save button
	public class SaveBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				List<Sprite> sprites = getGeneratedSpriteDetails();
				makerController.addAll(new ArrayList<Sprite>(sprites), getTitle());
				sprites.clear();
			} catch (UnsupportedAction e1) {
				e1.printStackTrace();
			} catch (UnsupportedEvent e1) {
				e1.printStackTrace();
			}
			resetFormHelper();
		}

	}

	public class TestBtnActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			makerController.gamePlayWindowIsRequested(getTitle());
		}

	}

}
