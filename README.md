Make a new github team for your project team at [this assignment](https://classroom.github.com/g/MPcdjfzc)

Your team name must start with the numbers (e.g. 10-2) from the list below.

10-1 3 [(xluox)(zwwpaul)(lucaswu)]  
10-2 5 [(csconroy)(karlstom)(udpboyle)(acucuzza)(vconte)]  
10-3 4 [(tymonty)(mattst)(jturk)(rmccann)]  
10-4 5 [(tkitson)(jskrip)(rgeary)(alinac)(zelinsky)]  
10-5 5 [(quncheng)(kylewang)(jomartin)(boomsaka)(chendai)]  
10-6 5 [(adamic)(wwalker)(alcoop)(muhammet)(rmelillo)]  
10-7 5 [(cbanning)(ryang)(lymike)(bphillip)(jakepdm)]  
10-8 5 [(oliviapy)(brivamc)(erino)(aashakad)(enahe)]  
10-9 5 [(emacd)(onweller)(chrisout)(sfreaney)(sbenton)]  

11-1 6 [(mattig)(mecuesta)(ljarrett)(ttopeor)(jclort)(kpeach)]  
11-2 5 [(klodzack)(gargamat)(hsuchyj)(kalloyan)(juanfv)]  
11-3 5 [(arvino)(gwhite)(ecaplan)(hinrichs)(rlyshw)]  
11-4 5 [(mtmiller)(mdell)(thangdao)(fmorales)(tmazal)]  
11-5 5 [(woodyhu)(skothare)(tongzhao)(abindra)(ariegner)]  
11-6 5 [(mmizikar)(jkr)(kburd)(nrife)(mferrato)]  
11-7 5 [(danpaddk)(jmaloyjr)(csgrasso)(mkong)(schichko)]  
11-8 5 [(raymondh)(udcymen)(zqs)(shiyu)(yumh)]  

Read [How to Use Buttons, Check Boxes, and Radio Buttons](https://docs.oracle.com/javase/tutorial/uiswing/components/button.html)

Run this:

```java
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class Animation4Thread extends JFrame {
	
	final int frameCount = 10;
	BufferedImage[] pics;
	int xloc = 100;
	int yloc = 100;
	final int xIncr = 1;
	final int yIncr = 1;
	final int picSize = 165;
	final int frameStartSize = 800;
	final int drawDelay = 30; //msec
	
	DrawPanel drawPanel = new DrawPanel();
	Action drawAction;
	
	public Animation4Thread() {
		drawAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				drawPanel.repaint();
			}
		};
		
		add(drawPanel);
		BufferedImage img = createImage();
		pics = new BufferedImage[frameCount];//get this dynamically
		for (int i = 0; i < frameCount; i++)
			pics[i] = img.getSubimage(picSize * i, 0, picSize, picSize);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameStartSize, frameStartSize);
		setVisible(true);
		pack();
	}
	
	@SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
		int picNum = 0;
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			picNum = (picNum + 1) % frameCount;
			g.drawImage(pics[picNum], xloc += xIncr, yloc += yIncr, Color.gray, this);
		}
		
		public Dimension getPreferredSize() {
			return new Dimension(frameStartSize, frameStartSize);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Animation4Thread a = new Animation4Thread();
				Timer t = new Timer(a.drawDelay, a.drawAction);
				t.start();
			}
		});
	}
	
	//Read image from file and return
	private BufferedImage createImage() {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/orc/orc_forward_southeast.png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
```


Once you have it running, incorporate this code into your MVC version. If you didn't receive full points for the MVC version, you must make adjustments this week.

In your MVC orc code:

1. Work on your code together in the assignment repo for your team. Everyone must do work, so *plan* the work and take turns coding. Your repo will be your submission.
2. Implement a single JButton that starts orc movement when you press it, and stops orc movement when you press it again. The button should work always.
3. Implement a second component (your choice) that changes orc direction.