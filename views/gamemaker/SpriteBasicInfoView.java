package com.gamemaker.views.gamemaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class SpriteBasicInfoView {
	private JLabel lblGameName;
	private JPanel leftTopPanel;
	private JLabel spriteName;
	private JTextField spriteNameTextField;

	private SpriteBasicInfoView() {

	}

	public SpriteBasicInfoView(JPanel panel) {
		this();
		this.leftTopPanel = panel;
		decoratePanel();
	}

	private void decoratePanel() {
		this.leftTopPanel.setLayout(new GridLayout(1, 2));
		this.leftTopPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Step 2: Choose a suitable name for your sprites", TitledBorder.LEADING, TitledBorder.TOP, new Font("Serif",
						Font.ITALIC, 14), new Color(0, 0, 0)));

		// Adding sprite name lable and textbox to take the value
		this.spriteName = new JLabel("Sprite Name:");
		this.spriteName.setFont(new Font("serif", Font.ITALIC, 15));
		this.lblGameName = new JLabel("Game Name");
		this.leftTopPanel.add(spriteName);
		this.spriteNameTextField = new JTextField();
		this.disableSpriteNameText();
		this.leftTopPanel.add(this.spriteNameTextField);
		this.leftTopPanel.validate();
		this.leftTopPanel.revalidate();
	}

	public String getSpriteName() {
		return spriteNameTextField.getText();
	}

	public void setSpriteName(String spriteName) {
		this.spriteNameTextField.setText(spriteName);
	}
	
	public void disableSpriteNameText(){
		this.spriteNameTextField.setEnabled(false);
	}
	
	public void enableSpriteNameText(){
		this.spriteNameTextField.setEnabled(true);
	}
}
