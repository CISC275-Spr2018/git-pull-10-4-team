import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 * <p>
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel {
	private final static int frameWidth = 500;
	private final static int frameHeight = 300;
	private final static int imageWidth = 165;
	private final static int imageHeight = 165;
	private final static int frameCount = 10;
	private static int frameNum = 0;
	private static int imgNum = 0;
	private static BufferedImage[][] pics;
	
	private int xLocation;
	private int yLocation;
	private boolean north;
	private boolean south;
	private boolean east;
	private boolean west;
	
	public int getImageHeight() {
		return imageHeight;
	}
	
	public int getImageWidth() {
		return imageWidth;
	}
	
	public static int getFrameWidth() {
		return frameWidth;
	}
	
	public static int getFrameHeight() {
		return frameHeight;
	}
	
	/**
	 * Get image, segment and store in array
	 */
	View() {
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
			for (int f = 0; f < frameCount; f++) {
				pics[i][f] = imgs[i].getSubimage(imageWidth * f, 0, imageWidth, imageHeight);
			}
		}
	}
	
	/**
	 * Draws the orc, reads location information
	 *
	 * @return
	 */
	public void update(int x, int y, boolean[] direct) {
		xLocation = x;
		yLocation = y;
		north = direct[0];
		south = direct[1];
		east = direct[2];
		west = direct[3];
	}
	
	/**
	 * Override this JPanel's paint method to cycle through picture array and draw images
	 *
	 * @param g
	 */
	public void paint(Graphics g) {
		//TODO: figure out how to reference images by enum instead of an index if that's a thing, because using words would be nicer than using numbers that mean nothing in context.
		frameNum = (frameNum + 1) % frameCount;
		
		if (north) {
			imgNum = 0;
		}
		if (south) {
			imgNum = 1;
		}
		if (east) {
			imgNum = 2;
		}
		if (west) {
			imgNum = 3;
		}
		if (north && east) {
			imgNum = 4;
		}
		if (north && west) {
			imgNum = 5;
		}
		if (south && east) {
			imgNum = 6;
		}
		if (south && west) {
			imgNum = 7;
		}
		g.drawImage(pics[imgNum][frameNum], xLocation, yLocation, Color.gray, this);
		
		// DONE: Keep the orc from walking off-screen, turn around when bouncing off walls.
		// Be sure that animation picture direction matches what is happening on screen.
	}
	
	/**
	 * All of the image paths and their enumerated names.
	 */
	public enum Images {
		ORC_IDLE_EWNS("images/orc/orc_idle_ewns.png"),
		ORC_IDLE_NWNESWSE("images/orc/orc_idle_nwneswse.png"),
		
		ORC_FW_E("images/orc/orc_forward_east.png"),
		ORC_FW_N("images/orc/orc_forward_north.png"),
		ORC_FW_S("images/orc/orc_forward_south.png"),
		ORC_FW_W("images/orc/orc_forward_west.png"),
		
		ORC_FW_NE("images/orc/orc_forward_northeast.png"),
		ORC_FW_NW("images/orc/orc_forward_northwest.png"),
		ORC_FW_SE("images/orc/orc_forward_southeast.png"),
		ORC_FW_SW("images/orc/orc_forward_southwest.png"),
		
		ORC_JUMP_E("images/orc/orc_jump_east.png"),
		ORC_JUMP_N("images/orc/orc_jump_north.png"),
		ORC_JUMP_S("images/orc/orc_jump_south.png"),
		ORC_JUMP_W("images/orc/orc_jump_west.png"),
		
		ORC_JUMP_NE("images/orc/orc_jump_northeast.png"),
		ORC_JUMP_NW("images/orc/orc_jump_northwest.png"),
		ORC_JUMP_SE("images/orc/orc_jump_southeast.png"),
		ORC_JUMP_SW("images/orc/orc_jump_southwest.png"),
		
		ORC_FIRE_E("images/orc/orc_fire_east.png"),
		ORC_FIRE_N("images/orc/orc_fire_north.png"),
		ORC_FIRE_S("images/orc/orc_fire_south.png"),
		ORC_FIRE_W("images/orc/orc_fire_west.png"),
		
		ORC_FIRE_NE("images/orc/orc_fire_northeast.png"),
		ORC_FIRE_NW("images/orc/orc_fire_northwest.png"),
		ORC_FIRE_SE("images/orc/orc_fire_southeast.png"),
		ORC_FIRE_SW("images/orc/orc_fire_southwest.png"),
		
		ORC_DIE_E("images/orc/orc_die_east.png"),
		ORC_DIE_N("images/orc/orc_die_north.png"),
		ORC_DIE_S("images/orc/orc_die_south.png"),
		ORC_DIE_W("images/orc/orc_die_west.png");
		
		private BufferedImage image;
		
		Images(String imagePath) {
			image = createImage(imagePath);
		}
		
		public BufferedImage getImage() {
			return image;
		}
		
		/**
		 * Read image from file and return
		 *
		 * @param imagePath The image's path
		 * @return
		 */
		static BufferedImage createImage(String imagePath) {
			File imageFile = new File(imagePath);
			BufferedImage bufferedImage;
			
			try {
				bufferedImage = ImageIO.read(imageFile);
				return bufferedImage;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
}
