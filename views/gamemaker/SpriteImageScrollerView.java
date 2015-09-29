package com.gamemaker.views.gamemaker;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.gamemaker.controllers.GameConstants;
import com.gamemaker.views.VerticalFlowLayout;

public class SpriteImageScrollerView {
	private JPanel panel;
	private JScrollPane imageScrollPane;
	private JPanel imagePanel;

	private SpriteImageScrollerView() {

	}

	public SpriteImageScrollerView(JPanel panel) {
		this();
		this.panel = panel;
		decoratePanel();
	}

	private void decoratePanel() {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Step 1: Choose images for your sprites", TitledBorder.LEADING, TitledBorder.TOP, new Font("Serif",
						Font.ITALIC, 14), new Color(0, 0, 0)));

		
		imageScrollPane = new JScrollPane();
		imagePanel = new JPanel(new GridLayout(6,7));
		imagePanel.setToolTipText(GameConstants.HELP_TEXT_IMAGE_DRAGNDROP);
		imageScrollPane.setViewportView(imagePanel);
		imageScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(imageScrollPane);
		panel.revalidate();
	}

	public Component[] getComponents() {
		return ((JPanel) (this.imageScrollPane.getViewport()).getComponent(0)).getComponents();
	}

	public void add(JButton imageBtn) {
		imagePanel.add(imageBtn);
		imagePanel.repaint();
	}
}
