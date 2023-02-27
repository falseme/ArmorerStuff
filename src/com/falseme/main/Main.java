package com.falseme.main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.falseme.gui.Assets;
import com.falseme.ui.Window;
import com.falseme.user.Config;

public class Main {

	public static void main(String[] args) {

		Assets.load();
		Config.init();

		Window window = new Window();
		window.setVisible(true);

		window.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				Config.serialize();
			}
		});

	}

}
