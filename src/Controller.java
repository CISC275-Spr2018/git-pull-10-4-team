import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
	
	private Model model;
	private View view;
	private Timer updater;

	public Controller() {
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), View.getImageWidth(), View.getImageHeight());
		view.setButtonListener(this); //add this as a listener to the button in view

		initTimer();
	}

	private void initTimer(){
		//define what the timer should do on each tick
		Action updateAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//increment the x and y coordinates, alter direction if necessary
				model.updateLocationAndDirection();
				//update the view
				view.update(model.getX(), model.getY(), model.getDirect());
			}
		};

		//create the actual timer
		updater = new Timer(30, updateAction);
		
	}
	
	// When the button in the view is clicked
	@Override
	public void actionPerformed(ActionEvent e) {
	    	if (updater.isRunning()) {
	    		updater.stop();
			} else {
				updater.start();
			}
	    	
	    	view.updateButton(updater.isRunning());
	}
	
	//run the simulation
	public void start() {
        EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				updater.start();
			}
		});
	}

	
}
