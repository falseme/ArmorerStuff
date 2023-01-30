package com.falseme.main;

import com.falseme.gui.Assets;
import com.falseme.ui.Window;

public class Main {

	public static void main(String[] args) {

		Assets.load();

		Window window = new Window();
		window.setVisible(true);

	}

}
