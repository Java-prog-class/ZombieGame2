package me.zombies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;

public class ZombiesMain {

	public static void main(String[] args) {
		new ZombiesMain();
	}

	JFrame window = new JFrame();
	DrawingPanel drPanel;
	static int panW = 800, panH = 500;
	static int round = 1;
	Zombie ztest;
	Player player = new Player();
	static int mapSpeed = 0;
	ZombiesMain(){
		setup();
		ztest = new Zombie("light");
	}


	void setup() {
		window = new JFrame("Zombies");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		drPanel = new DrawingPanel();
		window.add(drPanel);

		window.setVisible(true);
	}





	private class DrawingPanel extends JPanel {
		boolean screenInit = false;
		DrawingPanel() {
			this.setBackground(Color.WHITE);	
		}

		@Override
		public void paintComponent(Graphics g) {
			panH = this.getHeight();
			panW = this.getWidth();
			if (!screenInit) {	//only do this the very first time that the screen is painted
				player.x=panW/2;				
				player.y=panH/2;
				
				//System.out.println(panW + " " + panH);
				if (panW > 10) screenInit = true;
			}
			super.paintComponent(g); //clear screen and repaint using background colour
			Graphics2D g2 = (Graphics2D) g;		
			
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g.setColor(Color.GREEN.darker());
			//g.drawLine(0, panSize/2, panSize/2, panSize/2);
			//g.drawLine(player, panSize/2, panSize/2, panSize);
			g.setColor(Color.BLUE);
			g.fillOval(player.x-player.r/2, player.y-player.r/2, player.r, player.r);	
			
		}
	}
}
