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
		Controller controller = new Controller();
		frame.getContentPane().add(controller.getView());
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(View.getFrameWidth(), View.getFrameHeight());
		frame.setVisible(true);
		//controller.start();
		for (int i = 0; i < 1000; i++) {
			controller.getModel().updateLocationAndDirection();
			frame.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
