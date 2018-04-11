import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.awt.image.BufferedImage;

public enum Sprite {
    ORC_FORWARD_NORTH,
    ORC_FORWARD_NORTHEAST,
    ORC_FORWARD_EAST,
    ORC_FORWARD_SOUTHEAST,
    ORC_FORWARD_SOUTH,
    ORC_FORWARD_SOUTHWEST,
    ORC_FORWARD_WEST,
    ORC_FORWARD_NORTHWEST,

	ORC_JUMP_NORTH,
    ORC_JUMP_NORTHEAST,
    ORC_JUMP_EAST,
    ORC_JUMP_SOUTHEAST,
    ORC_JUMP_SOUTH,
    ORC_JUMP_SOUTHWEST,
    ORC_JUMP_WEST,
    ORC_JUMP_NORTHWEST,

    ORC_FIRE_NORTH,
    ORC_FIRE_NORTHEAST,
    ORC_FIRE_EAST,
    ORC_FIRE_SOUTHEAST,
    ORC_FIRE_SOUTH,
    ORC_FIRE_SOUTHWEST,
    ORC_FIRE_WEST,
    ORC_FIRE_NORTHWEST;
	

    BufferedImage[] pics;

    final static String IMAGE_DIR = "images/orc/";
    final static String PREFIX = "ORC_FORWARD_";
    final static String IMAGE_TYPE = ".png";
    
    final static String JUMP_PREFIX = "ORC_JUMP_";
    final static String FIRE_PREFIX = "ORC_FIRE_";

    final static int frameWidth = 165;
    final static int frameHeight = 165;

    Sprite(){
        //get image should be in the folder IMAGE_DIR
        //and have the lowercase version of the name of the constant
        //with the IMAGE_TYPE file extension
        String imagePath = IMAGE_DIR + name().toLowerCase() + IMAGE_TYPE;

        //load in the image
        BufferedImage img = createImage(imagePath);

        //then seperate it into frames
        int frameCount = img.getWidth() / frameWidth;
        pics = new BufferedImage[frameCount];
        for(int i = 0; i < frameCount; i++)
            pics[i] = img.getSubimage(frameWidth*i, 0, frameWidth, frameHeight);
    }

    //Read image from file and return
    private BufferedImage createImage(String imagePath) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(imagePath));
            return bufferedImage;
        } catch (IOException e) {
            System.out.println(imagePath);
            e.printStackTrace();
        }
        return null;
    }

    public static Sprite getSprite(Direction direct){ // Load a different image based on current action
    	String name;
    	if (direct.isJumping()) {
    		name = JUMP_PREFIX + direct.getName();
    	}else if (direct.isFiring()){
            name = FIRE_PREFIX+direct.getName();
        }else{
    		name = PREFIX + direct.getName();
    	}
        return Sprite.valueOf(name.toUpperCase());
    }

    public BufferedImage getImage(int imageNum) {
        return pics[imageNum];
    }

    public int getNumImages(){
        return pics.length;
    }
}
