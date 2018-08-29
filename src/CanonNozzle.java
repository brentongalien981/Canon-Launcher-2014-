import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.JPanel;


public class CanonNozzle extends JPanel {
	private final int NOZ_LEN = 100;
	private int canonMouthX;
	private int canonMouthY;
	
	public CanonNozzle(int length) {
		setSize(length*2, length);
		setLayout(null);
		canonMouthX = getWidth()/2;
		canonMouthY = 0;
		setBackground(Color.red);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawLine(getWidth()/2, getHeight(), canonMouthX, canonMouthY);
	}
	
	public void setEndPoint(double angle) {
		double x = NOZ_LEN * Math.cos(angle);
		double y = NOZ_LEN * Math.sin(angle);
		
		canonMouthX = (int) (100 + x);
		canonMouthY = (int) (100 - x);
		repaint();
	}
}
