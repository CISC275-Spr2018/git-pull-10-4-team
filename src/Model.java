import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/

public class Model extends Rectangle{
    final int dx = 8;
    final int dy = 2;
    Rectangle bounds;
    Direction direct;

    public Model (int width, int height, int imageWidth, int imageHeight) {
        super(imageWidth, imageHeight);
        bounds = new Rectangle(width, height);
        direct = new Direction(dx, dy);
    }

    public Direction getDirect() {
        return direct;
    }

    public void updateLocationAndDirection(){
        //increment the image's location
        translate(direct.getHorizontalSign() * dx,
                  direct.getVerticalSign() * dy);

        //and check whether we're out of bounds
        bounceOffWalls();
    }

    private void bounceOffWalls(){
        //check vertical bounds...
        if (! bounds.contains(getCenterX(), getMinY())
                || ! bounds.contains(getCenterX(), getMaxY())) {
            //...and reverse vertical direction if we're offscreen
            direct.toggleVertical();
        }

        //check horizontal bounds...
        if (! bounds.contains(getMinX(), getCenterY())
                || ! bounds.contains(getMaxX(), getCenterY())) {
            //...and reverse horizontal direction if we're offscreen
            direct.toggleHorizontal();
        }
    }
    
    private boolean checkBounds() {
    	return (! bounds.contains(getMinX(), getCenterY()) || ! bounds.contains(getMaxX(), getCenterY())) || 
    			(! bounds.contains(getCenterX(), getMinY())|| ! bounds.contains(getCenterX(), getMaxY()));
    }
    
    // How to handle key presses
    public void changeDirection(int e) {
    	if (!checkBounds()) {
    		String curDir = direct.getName();
    		if(e == KeyEvent.VK_UP && !curDir.equals("NORTH")) {
    			direct.setDirection(0, -1);
    		} else if (e == KeyEvent.VK_RIGHT && !curDir.equals("EAST")) {
    			direct.setDirection(1, 0);
    		} else if (e == KeyEvent.VK_LEFT && !curDir.equals("WEST")) {
    			direct.setDirection(-1, 0);
    		} else if (e == KeyEvent.VK_DOWN && !curDir.equals("SOUTH")) {
    			direct.setDirection(0, 1);
    		}
    	}
    }
}
