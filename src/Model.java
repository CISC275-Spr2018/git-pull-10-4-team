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

class Model {
	private static int worldWidth;
	private static int worldHeight;
	private static int characterWidth;
	private static int characterHeight;
	
	private static int x = 0;
	private static int y = 0;
	
	private final static int xIncr = 8;
	private final static int yIncr = 2;
	
	private static boolean moveNorth = false;
	private static boolean moveSouth = true;
	private static boolean moveEast = true;
	private static boolean moveWest = false;
	private boolean direct[] = {moveNorth, moveSouth, moveEast, moveWest};
	
	public Model(int width, int height, int imageWidth, int imageHeight) {
		worldWidth = width;
		worldHeight = height;
		characterWidth = imageWidth;
		characterHeight = imageHeight;
	}
	
	
	public int getY() {
		return y;
	}
	
	public int getX() {
		return x;
	}
	
	/**
	 * Calls all the model methods
	 */
	public void updateLocationAndDirection() {
		handleOrc();
		moveOrc();
	}
	
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
		if (x > worldWidth - characterWidth) {
			System.out.println("Hit east wall");
			moveWest = true;
			moveEast = false;
		}
		if (y < 0) {
			System.out.println("Hit north wall");
			moveSouth = true;
			moveNorth = false;
		}
		if (y > worldHeight - characterHeight) {
			System.out.println("Hit south wall");
			moveNorth = true;
			moveSouth = false;
		}
	}
	
	public boolean[] getDirect() {
		return direct;
	}
}
