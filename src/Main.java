import javax.swing.*;
import java.awt.*;

public class Main {
	public static JFrame frame;
	
	/**
	 * Make frame, loop on repaint and wait
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		frame = new JFrame();
		Controller controller = new Controller();
		Model model = controller.getModel();
		View view = controller.getView();
		frame.getContentPane().add(view);
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(View.getFrameWidth(), View.getFrameHeight());
		frame.setVisible(true);
		//controller.start();
		
		for (int i = 0; i < 1000; i++) {
			model.updateLocationAndDirection();
			frame.repaint();
			view.update(model.getX(), model.getY(), model.getDirect());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
