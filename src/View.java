import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Color;
import java.util.HashMap;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

public class View extends JPanel {
    //should never change; otherwise would mess up Model
    final static int imageWidth = 165;
    final static int imageHeight = 165;
    final static int frameWidth = 500;
    final static int frameHeight = 300;

    private int imageNum;
    private HashMap<Direction, Sprite> spriteMap;
    private JFrame frame;

    //cached from last update call
    private int curX;
    private int curY;
    private Direction curDirect;

    public View() {
        //create JFrame and add this to it
        initJFrame();
    }

    private void initJFrame() {
        frame = new JFrame();
        frame.getContentPane().add(this);
        frame.setBackground(Color.gray);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
    }

    public void update(double x, double y, Direction direct) {
        curX = (int) x;
        curY = (int) y;
        curDirect = direct;

        frame.repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getImageWidth(){
        return imageWidth;
    }

    public static int getImageHeight() {
        return imageHeight;
    }

    private BufferedImage getImage() {
        Sprite curSprite = Sprite.getSprite(curDirect);
        imageNum = (imageNum + 1) % curSprite.getNumImages();
        return curSprite.getImage(imageNum);
    }

    //Override this JPanel's paint method to cycle through picture array and draw images
    public void paint(Graphics g) {
        g.drawImage(getImage(),
                    curX,
                    curY,
                    Color.gray,
                    this);
    }
}
