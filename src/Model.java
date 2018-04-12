import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 * <p>
 * has methods to
 * detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model extends Rectangle {
	final int dx = 8;
	final int dy = 8;
	Rectangle bounds;
	Direction direct;
	
	public Model(int width, int height, int imageWidth, int imageHeight) {
		super(imageWidth, imageHeight);
		bounds = new Rectangle(width, height);
		direct = new Direction(dx, dy);
	}
	
	public Direction getDirect() {
		return direct;
	}
	
	public void updateLocationAndDirection() {
		//increment the image's location
		if (!direct.isFiring()) {
			translate(direct.getHorizontalSign() * dx,
					direct.getVerticalSign() * dy);
		}
		
		//and check whether we're out of bounds
		bounceOffWalls();
	}
	
	private void bounceOffWalls() {
		//check vertical bounds...
		if (!bounds.contains(getCenterX(), getMinY())
				|| !bounds.contains(getCenterX(), getMaxY())) {
			//...and reverse vertical direction if we're offscreen
			direct.toggleVertical();
		}
		
		//check horizontal bounds...
		if (!bounds.contains(getMinX(), getCenterY())
				|| !bounds.contains(getMaxX(), getCenterY())) {
			//...and reverse horizontal direction if we're offscreen
			direct.toggleHorizontal();
		}
	}
	
	private boolean checkBounds() {
		return (!bounds.contains(getMinX(), getCenterY()) || !bounds.contains(getMaxX(), getCenterY())) ||
				(!bounds.contains(getCenterX(), getMinY()) || !bounds.contains(getCenterX(), getMaxY()));
	}
	
	// How to handle directions
	public void changeDirection(int e) {
		if (!checkBounds()) {
			switch (e){
				// Orthogonal Movement
				case KeyEvent.VK_UP:
				direct.setDirection(0, -1);
				break;
				case KeyEvent.VK_RIGHT:
				direct.setDirection(1, 0);
				break;
				case KeyEvent.VK_LEFT:
				direct.setDirection(-1, 0);
				break;
				case KeyEvent.VK_DOWN:
				direct.setDirection(0, 1);
				break;
				
				// Diagonal Movement
				case KeyEvent.VK_Q:
				direct.setDirection(-1, -1);
				break;
				case KeyEvent.VK_W:
				direct.setDirection(1, -1);
				break;
				case KeyEvent.VK_A:
				direct.setDirection(-1, 1);
				break;
				case KeyEvent.VK_S:
				direct.setDirection(1, 1);
				break;
			}
		}
	}
	
	// How to handle actions
	public void doAction(int e) {
		if (e == KeyEvent.VK_J && !direct.isJumping()) {
			direct.jump();
		} else if (e == KeyEvent.VK_F) {
			direct.fire();
		}
	}
}
