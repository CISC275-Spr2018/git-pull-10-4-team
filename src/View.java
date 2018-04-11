import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

public class View extends JPanel {
    //should never change; otherwise would mess up Model
    final static int imageWidth = 165;
    final static int imageHeight = 165;
    final static int frameWidth = 500*2;
    final static int frameHeight = 300*2;

    private int imageNum = 0;
    private HashMap<Direction, Sprite> spriteMap;
    private JFrame frame;
    
    private int jumpImageFrame = -1; // -1 means we are not jumping, so there are no jump image frames

    //cached from last update call
    private int curX;
    private int curY;
    private Sprite curSprite = Sprite.getSprite(new Direction());
    
    //start stop button and JPanel
    JButton startStopButton;
    JPanel buttonPanel;
    
    public View() {
    	
        //create a JPanel with a start/stop button on it
        initButton();
    	
        //create JFrame and add this to it
        initJFrame();        
    }

    private void initJFrame() {
        frame = new JFrame();
        frame.getContentPane().add(buttonPanel, BorderLayout.NORTH); //add the JPanel with the button
        frame.getContentPane().add(this);
        frame.getContentPane().setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);      
    }
    
    //Create the button and its own JPanel to hold it
    private void initButton() {
    	buttonPanel = new JPanel();
    	startStopButton = new JButton("Stop");
    	startStopButton.setMnemonic(KeyEvent.VK_S);
    	startStopButton.setToolTipText("Click to start/stop the animation");

		JLabel directionChangeLabel = new JLabel("Arrow Keys to change direction | F to toggle fire | J to jump");
		directionChangeLabel.setForeground(Color.white);
    	buttonPanel.add(startStopButton);
		buttonPanel.add(directionChangeLabel);
    	buttonPanel.setBackground(Color.gray);
    	buttonPanel.setFocusable(false);
    	startStopButton.setFocusable(false);
    }
    
    // For the controller to add its ActionListner to the button
    public void setButtonListener(ActionListener l) {
    	startStopButton.addActionListener(l);
    }
    
    // For the controller to add its KeyListener to the view
    public void setKeyListener(KeyListener k) {
    	frame.requestFocus();
    	frame.addKeyListener(k);
    }
    
    // change the button text based on whether the animation is running or not
    public void updateButton(boolean running) {
    	if (running) {
    		startStopButton.setText("Stop");
    	} else {
    		startStopButton.setText("Start");
    	}
    }
    

    public void updateLocation(double x, double y) {
        curX = (int) x;
        curY = (int) y;

        imageNum = (imageNum + 1) % curSprite.getNumImages();
        
        frame.repaint();
    }

    public void updateDirection(Direction direct) {
        curSprite = Sprite.getSprite(direct);
        if(direct.isJumping() && jumpImageFrame == -1) { // If this is the start of the jump animation, load the first frame
        	imageNum = 0;
        }
        
        if (direct.isJumping()) {	// If we're jumping, keep track of the jump frames and stop jumping when the animation is over
        	jumpImageFrame++;
        	if (jumpImageFrame == curSprite.getNumImages() - 1) {
        		direct.jump();
        		jumpImageFrame = -1;
        	}
        }
        
        frame.repaint();
    }

    public static int getImageWidth(){
        return imageWidth;
    }

    public static int getImageHeight() {
        return imageHeight;
    }

    private BufferedImage getImage() {
        return curSprite.getImage(imageNum);
    }

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
        g.drawImage(getImage(),
                    curX,
                    curY,
                    Color.gray,
                    this);
    }

}
