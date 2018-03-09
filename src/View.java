/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 * <p>
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
class View {
	private int width;
	private int height;
	private int imageWidth;
	private int imageHeight;
	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getImageWidth() {
		return imageWidth;
	}
	
	public int getImageHeight() {
		return imageHeight;
	}
	
	public void update(int x, int y, int direct) {
	
	}
}