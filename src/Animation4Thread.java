

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Animation4Thread extends JFrame {
	
	final int frameCount = 10;
	BufferedImage[] pics;
	int xloc = 100;
	int yloc = 100;
	final int xIncr = 1;
	final int yIncr = 1;
	final int picSize = 165;
	final int frameStartSize = 800;
	final int drawDelay = 30; //msec	
	DrawPanel drawPanel = new DrawPanel();
	Action drawAction;
	JButton startButton = new JButton("Stop"); // THE BUTTON
	
	public Animation4Thread() {
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				drawPanel.repaint();
			}
		};
		
		startButton.setMnemonic(KeyEvent.VK_S);	// BUTTON STUFF
		startButton.setToolTipText("Click to start/stop the animation");
		drawPanel.add(startButton); // ADD THE BUTTON TO THE DRAWPANEL
		add(drawPanel);
		BufferedImage img = createImage();
		pics = new BufferedImage[frameCount];//get this dynamically
		for (int i = 0; i < frameCount; i++)
			pics[i] = img.getSubimage(picSize * i, 0, picSize, picSize);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameStartSize, frameStartSize);
		setVisible(true);
		pack();
	}
	
	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
		int picNum = 0;
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			picNum = (picNum + 1) % frameCount;
			g.drawImage(pics[picNum], xloc += xIncr, yloc += yIncr, Color.gray, this);
		}
		
		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Animation4Thread a = new Animation4Thread();
				Timer t = new Timer(a.drawDelay, a.drawAction);
				StartButtonListener sbl = new StartButtonListener(a.startButton, t); // CREATE A LISTENER FOR THE BUTTON
				a.startButton.addActionListener(sbl);								// ADD IT TO THE BUTTON
				t.start();
			}
		});
	}
	

	
	
	//Read image from file and return
	private BufferedImage createImage() {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

// THE BUTTON LISTENER
class StartButtonListener implements ActionListener {
	
	Timer t;	// THE TIMER IT STARTS/STOPS
	JButton b;	// THE BUTTON IT MODIFIES
	boolean moving = true; // KEEP TRACK OF THE STATE OF THE BUTTON/TIMER

	public StartButtonListener(JButton b, Timer t) {
		this.t= t;
		this.b = b;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (moving) {
			b.setText("Start");
			moving = false;
			t.stop();
		} else {
			b.setText("Stop");
			moving = true;
			t.start();
		}
		
	}
	
}


