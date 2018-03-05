/**
 * T Harvey
 * based loosely on http://www.java2s.com/Code/JavaAPI/java.awt/GraphicsdrawImageImageimgintxintyImageObserverob.htm
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

class Animation extends JPanel {
	
	final int frameCount = 10;
	int frameNum = 0;
	int imgNum = 0;
	BufferedImage[][] pics;
	int xloc = 0;
	int yloc = 0;
	final int xIncr = 8;
	final int yIncr = 2;
	final static int frameWidth = 500;
	final static int frameHeight = 300;
	final static int imgWidth = 165;
	final static int imgHeight = 165;
	boolean moveNorth = false;
	boolean moveSouth = true;
	boolean moveEast = true;
	boolean moveWest = false;
	
	/**
	 * Override this JPanel's paint method to cycle through picture array and draw images
	 *
	 * @param g
	 */
	public void paint(Graphics g) {
		handleOrc();
		moveOrc();
		drawOrc(g);
	}
	
	
	/**
	 * Reads from movement booleans to change the position of the orc
	 */
	void moveOrc() {
		if (!moveEast && !moveWest && !moveNorth && !moveSouth) {
			System.out.println("Nothing is true, don't move!");
		}
		if (moveEast) {
			//System.out.println("moveEast!");
			xloc += xIncr;
		}
		if (moveWest) {
			//System.out.println("moveWest!");
			xloc -= xIncr;
		}
		if (moveSouth) {
			//System.out.println("moveSouth!");
			yloc += yIncr;
		}
		if (moveNorth) {
			//System.out.println("moveNorth!");
			yloc -= yIncr;
		}
	}
	
	/**
	 * Writes to movement booleans under certain conditions
	 */
	void handleOrc() {
		if (xloc < 0) {
			System.out.println("Hit west wall");
			moveEast = true;
			moveWest = false;
		}
		if (xloc > frameWidth - imgWidth) {
			System.out.println("Hit east wall");
			moveWest = true;
			moveEast = false;
		}
		if (yloc < 0) {
			System.out.println("Hit north wall");
			moveSouth = true;
			moveNorth = false;
		}
		if (yloc > frameHeight - imgHeight) {
			System.out.println("Hit south wall");
			moveNorth = true;
			moveSouth = false;
		}
	}
	
	
	/**
	 * Draws the orc, reads location information
	 *
	 * @param g
	 * @return
	 */
	Graphics drawOrc(Graphics g) {
		//TODO: figure out how to reference images by enum instead of an index if that's a thing, because using words would be nicer than using numbers that mean nothing in context.
		frameNum = (frameNum + 1) % frameCount;
		
		if (moveNorth) {
			imgNum = 0;
		}
		if (moveSouth) {
			imgNum = 1;
		}
		if (moveEast) {
			imgNum = 2;
		}
		if (moveWest) {
			imgNum = 3;
		}
		if (moveNorth && moveEast) {
			imgNum = 4;
		}
		if (moveNorth && moveWest) {
			imgNum = 5;
		}
		if (moveSouth && moveEast) {
			imgNum = 6;
		}
		if (moveSouth && moveWest) {
			imgNum = 7;
		}
		g.drawImage(pics[imgNum][frameNum], xloc, yloc, Color.gray, this);
		
		// DONE: Keep the orc from walking off-screen, turn around when bouncing off walls.
		// Be sure that animation picture direction matches what is happening on screen.
		
		return g;
	}
	
	
	/**
	 * Constructor: get image, segment and store in array
	 */
	public Animation() {
		
		final BufferedImage[] imgs = {
				//TODO: figure out how to add all images via enum, including ones with different frame counts/sizes/layouts
				Images.ORC_FW_N.getImage(),
				Images.ORC_FW_S.getImage(),
				Images.ORC_FW_E.getImage(),
				Images.ORC_FW_W.getImage(),
				
				Images.ORC_FW_NE.getImage(),
				Images.ORC_FW_NW.getImage(),
				Images.ORC_FW_SE.getImage(),
				Images.ORC_FW_SW.getImage()};
		
		pics = new BufferedImage[imgs.length][frameCount];
		
		for (int i = 0; i < imgs.length; i++) {
			for (int f = 0; f < frameCount; f++)
				pics[i][f] = imgs[i].getSubimage(imgWidth * f, 0, imgWidth, imgHeight);
		}
		// DONE: Change this constructor so that at least eight orc animation pngs are loaded
	}
	

}
