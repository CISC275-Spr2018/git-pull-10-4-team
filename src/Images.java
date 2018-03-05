import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
