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
	private int x;
	private int y;
	private int direct;
	
	public Model(int width, int height, int imageWidth, int imageHeight) {
	
	}
	
	public void updateLocationAndDirection() {
	
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDirect() {
		return direct;
	}
}