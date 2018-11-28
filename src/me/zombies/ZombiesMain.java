package me.zombies;

import java.awt.Color;
import javax.swing.*;

public class ZombiesMain {

	public static void main(String[] args) {
		new ZombiesMain();
	}

	JFrame window = new JFrame();
	DrawingPanel drPanel;
	int panW = 800, panH = 500;
	
	
	ZombiesMain(){
		setup();
	}
	
	
	void setup() {
		window = new JFrame("Zombies");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		drPanel = new DrawingPanel();
		window.add(drPanel);

		window.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	private class DrawingPanel extends JPanel {
		DrawingPanel() {
			this.setBackground(Color.WHITE);			
		}
	}
}
