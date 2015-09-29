package com.gamemaker.dnd;

import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.gamemaker.views.GameMakerViewable;

public class MyDropTargetListImp extends DropTargetAdapter {

	int dragX;
	int dragY;
	private ImageIcon icon;
	private JButton JSprite;
	private GameMakerViewable gameMakerView;
	private DropTarget dropTarget;

	public MyDropTargetListImp(GameMakerViewable gameMakerView) {
		this.gameMakerView = gameMakerView;
		this.gameMakerView.setDropPanel(this.gameMakerView.getRightPanel());
		dropTarget = new DropTarget(this.gameMakerView.getDropPanel(), DnDConstants.ACTION_COPY, this, true, null);
	}

	@Override
	public void drop(DropTargetDropEvent event) {
		try {
			Transferable tr = event.getTransferable();
			JSprite = (JButton) tr.getTransferData(gameMakerView.getTransferableSprite().getDataFlavor());
			icon = (ImageIcon) JSprite.getIcon();
			JSprite = new JButton(icon);
			String desc = ((ImageIcon) JSprite.getIcon()).getDescription();
			gameMakerView.setSelectedIcon(desc);

			gameMakerView.setSprite((int) event.getLocation().getX(), (int) event.getLocation().getY());

			if (event.isDataFlavorSupported(gameMakerView.getTransferableSprite().getDataFlavor())) {
				event.acceptDrop(DnDConstants.ACTION_COPY);
				gameMakerView.getDropPanel().setLayout(null);
				gameMakerView.getDropPanel().add(JSprite);
				event.dropComplete(true);

				gameMakerView.getDropPanel().validate();
				return;
			}
			event.rejectDrop();
		} catch (Exception e) {
			e.printStackTrace();
			event.rejectDrop();
		}
	}
}
