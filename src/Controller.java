import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {
	
	private Model model;
	private View view;
	private Action updateAction;
	
	public Controller() {
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		updateAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//increment the x and y coordinates, alter direction if necessary
				model.updateLocationAndDirection();
				//update the view
				view.update(model.getX(), model.getY(), model.getDirect());
			}
		};
	}
	
	//run the simulation
	public void start() {
		Timer timer = new Timer(30, updateAction);
		timer.start();
	}
}
