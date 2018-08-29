import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class BallVertical extends JFrame implements Runnable {
	private volatile int posInPx;
	private double v, a, t, d, vx;
	DrawBall ball, ball2;
	Thread yeyeThread;
	
	public BallVertical() {
		System.out.println("CREATED BY HECTOR CELEDONIO B!!!");
		setSize(1350, 750);
		setPreferredSize(new Dimension(1350, 750));
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		yeyeThread = new Thread(this);
		posInPx = 670;
		t = 0;
		vx = 50*Math.cos(Math.toRadians(35));	
		v = -50*Math.sin(Math.toRadians(35)); /* 50m/s	*/
		System.out.println("COMPONENTS INITIALIZATION:\n\tvx: " + vx + "\tvy: " + v);
		a = 9.8; // 9.8m/s2
		d = 0;
		
		ball = new DrawBall();		
		add(ball);
		ball.setLocation(0, posInPx);
		
		//yeyeThread.start();
		//getContentPane().setBackground(Color.black);
		
        addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent ke) {
        		yeyeThread.start();
        	}
		});
	}
	
	/*
	private void calculateD() {
		//y = (int)((v*t) + ((1/2)*a*t*t));
		v = v + a;
		//++t;
	}
	*/
	
	private void releaseBall() {
		double h =0;
		double dx = 0;
		int distanceInPx = 0;
		int count = 1;
		do {
			//calculateD();
			//h = (720*y)/127;
			//v += a;
			t += 0.1;

			d = (v*t) + ((1.0/2)*a*(t*t));
			// convert meters to pixels
			h = (670*d)/128; // distance in pixels
			posInPx = 670 + (int)h;
			
			
			// FOR X-COMPONENT
			dx = (vx*t);
			distanceInPx = (int) ((1300*dx)/233.8);
			
			
			
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			ball.setLocation(distanceInPx, posInPx);
			
			System.out.println("count: " + count);
			System.out.println("t: " + t + "s");
			System.out.println("ay: " + a + "m/s2");
			System.out.println("vy: " +v + "m/s");			
			System.out.println("dy: " + d + "m");			
			System.out.println("px-y: " + h + "px");
			System.out.println("getY: " + ball.getY() + "\n");
			
			System.out.println("vx: " +vx + "m/s");	
			System.out.println("dx: " + dx + "m");	
			System.out.println("dInPx: " + distanceInPx + "px\n\n");
			
			
			//System.out.println("position: " +posInPx + "px"+ "\n");
			
			//v += a;
			//++t;
			++count;
			
		} while (ball.getY() <= 670);
	}
	
	public static void main(String args[]) {		
		try {
			SwingUtilities.invokeLater(new Runnable () {
				public void run() {
					new BallVertical();
				}
			});
		}
		catch(Exception exc) { System.out.println("Can't create because of "+ exc); }
	}

	@Override
	public void run() {
		releaseBall();
		
	}
}