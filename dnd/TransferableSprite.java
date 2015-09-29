package com.gamemaker.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JButton;

import com.gamemaker.models.Sprite;

public class TransferableSprite implements Transferable {
	private final DataFlavor dataFlavor;
	private final JButton sprite;

	public DataFlavor getDataFlavor() {
		return dataFlavor;
	}

	public TransferableSprite(JButton sprite) {
		this.dataFlavor = new DataFlavor(Sprite.class, Sprite.class.getSimpleName());
		this.sprite = sprite;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] { dataFlavor };
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(dataFlavor);
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

		if (flavor.equals(dataFlavor))
			return sprite;
		else
			throw new UnsupportedFlavorException(flavor);
	}
}
