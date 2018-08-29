import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JPanel;


public class DrawBall extends JPanel {
	
	public DrawBall() {
		setSize(30, 30);
		setLayout(null);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillOval(0, 0, 30, 30);
	}
}
