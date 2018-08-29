import java.awt.*;
import java.awt.event.*;
import java.applet.*;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class Canon2 extends JFrame
	implements KeyListener, MouseMotionListener
{
	// CONSTANTS
	private final int NOZ_LEN = 100;
	
	// INSTANCE VARIABLES
	private int canonBaseX;
	private int canonBaseY;	
	private int mouseX, mouseY;
	private int deltaX, deltaY; 	// distance from mouse to origin
	private double canonMouthX;
	private double canonMouthY;
	private double angle;	
	
	private DrawBall ball;
	private CanonNozzle nozzle;
	
	// CONSTRUCTOR		
	public Canon2() {
		setLayout(null);
		setSize(1350, 750);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		nozzle = new CanonNozzle(NOZ_LEN);
		ball = new DrawBall();
		
		mouseX = mouseY = deltaX = deltaY = 0;
		angle = canonMouthX = canonMouthY = 0;

		nozzle.setLocation(getWidth()/2-100, 550);
		ball.setLocation(getWidth()/2, (getHeight()/2)-30);
		
		canonBaseX = nozzle.getX()+100;
		canonBaseY = nozzle.getY()+100;
		
		
		add(nozzle);
		add(ball);

		addKeyListener(this);
		addMouseMotionListener(this);
	}
	
	
	public void mouseMoved(MouseEvent me)
	{		
		mouseX = me.getX();
		mouseY = me.getY();
		deltaX = mouseX - canonBaseX;
		deltaY = (mouseY - canonBaseY) * -1;
		
		System.out.println("deltaY: " + deltaY);
	
		// position the canon mouth
		{
			if (deltaX == 0) 	// we don't want division of zero in getting the angle
			{
				if (deltaY >= 0)
				{
					canonMouthX = canonBaseX;
					canonMouthY = canonBaseY - NOZ_LEN;	
				}
				else return;
			}	
			else if (deltaY == 0)
			{
				if (deltaX > 0) {canonMouthX = canonBaseX + NOZ_LEN;}
				else if (deltaX < 0) {canonMouthX = canonBaseX - NOZ_LEN;}
				canonMouthY = canonBaseY;				
			}
			else if (deltaY <= 0) { System.out.println("out of scope!"); }
			else setCanonMouth(); 				// in position to fire
		}	
		System.out.println("angle: " + Math.toDegrees(angle));
		nozzle.setEndPoint(angle);
	}
	
	private void setCanonMouth()
	{
		setAngle();
		setCanonMouthPos();		
	}
	
	private void setAngle()
	{
		angle = Math.atan((double) deltaY/deltaX);
		double degAngle = Math.toDegrees(angle);
		
		// this checks if your mouse is
		// in the 2nd quadrant and makes
		// makes the angle equal to the complementary angle
		if (degAngle < 0)
		{
			degAngle = 180+degAngle;
			angle = Math.toRadians(degAngle);
		}
	}
	
	private void setCanonMouthPos()
	{
		canonMouthX = canonBaseX + (NOZ_LEN * Math.cos(angle));
		canonMouthY = canonBaseY - (NOZ_LEN * Math.sin(angle));
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		System.out.println("draged");
		
	}
	
	public static void main(String[] args)
	{
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new Canon2();
				}
			});
		}
		catch(Exception exc) {
		}
	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("typed");
		
	}
}

