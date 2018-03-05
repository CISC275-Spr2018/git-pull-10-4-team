import javax.swing.*;
import java.awt.*;

public class Main {
	/**
	 * Make frame, loop on repaint and wait
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Controller());
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(View.getFrameWidth(), View.getFrameHeight());
		frame.setVisible(true);
		Model myModel = new Model();
		for (int i = 0; i < 1000; i++) {
			myModel.updateLocationAndDirection();
			frame.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
