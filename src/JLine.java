import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class JLine extends JPanel {
	private int x1, x2, y1, y2;
	public JLine(int x1, int x2, int y1, int y2) {
		
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.drawLine(x1, y1, x2, y2);
	}
}
