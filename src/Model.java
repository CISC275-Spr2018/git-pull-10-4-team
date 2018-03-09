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

public class Model {
	public final static int xIncr = 8;
	public final static int yIncr = 2;
	public static boolean moveNorth = false;
	public static boolean moveSouth = true;
	public static boolean moveEast = true;
	public static boolean moveWest = false;
	public static int x = 0;
	public static int y = 0;
	public boolean direct;
	
	/**
	 * Reads from movement booleans to change the position of the orc
	 */
	public static void moveOrc() {
		if (!moveEast && !moveWest && !moveNorth && !moveSouth) {
			System.out.println("Nothing is true, don't move!");
		}
		if (moveEast) {
			//System.out.println("moveEast!");
			x += xIncr;
		}
		if (moveWest) {
			//System.out.println("moveWest!");
			x -= xIncr;
		}
		if (moveSouth) {
			//System.out.println("moveSouth!");
			y += yIncr;
		}
		if (moveNorth) {
			//System.out.println("moveNorth!");
			y -= yIncr;
		}
	}
	
	/**
	 * Writes to movement booleans under certain conditions
	 */
	public static void handleOrc() {
		if (x < 0) {
			System.out.println("Hit west wall");
			moveEast = true;
			moveWest = false;
		}
		if (x > View.getFrameWidth() - View.getFrameHeight()) {
			System.out.println("Hit east wall");
			moveWest = true;
			moveEast = false;
		}
		if (y < 0) {
			System.out.println("Hit north wall");
			moveSouth = true;
			moveNorth = false;
		}
		if (y > View.getFrameHeight() - View.getFrameWidth()) {
			System.out.println("Hit south wall");
			moveNorth = true;
			moveSouth = false;
		}
	}
	
	private int getY() {
		return y;
	}
	
	private int getX() {
		return x;
	}
	
	public void updateLocationAndDirection() {
		handleOrc();
		moveOrc();
	}
	
	private boolean getDirect() {
		return direct;
	}
}
