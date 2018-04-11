import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements ActionListener{
	
	// Pull req test
	
	private Model model;
	private View view;
	private Timer updater;

	public Controller() {
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), View.getImageWidth(), View.getImageHeight());
		view.setButtonListener(this); //add this as a listener to the button in view
		view.setKeyListener(new DirectionKeyListener());
		view.setKeyListener(new ActionKeyListener());

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
				view.updateDirection(model.getDirect());
				view.updateLocation(model.getX(), model.getY());
			}
		};

		//create the actual timer
		updater = new Timer(50, updateAction);
		
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

	// add an instance of this as a key listener to the view
	private class DirectionKeyListener implements KeyListener {
		boolean pressed = false;
		int currentKey;
		int lastKey;

        @Override
        public void keyPressed(KeyEvent e) {
            if(!pressed) {
                pressed = true;
                currentKey = e.getKeyCode();
                model.changeDirection(currentKey); // what happens when a key is released
                if(!updater.isRunning() && currentKey != lastKey) {
                    view.updateDirection(model.getDirect());
                    lastKey = e.getKeyCode();
                } else if (updater.isRunning()) {
                    lastKey = 0;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == currentKey){
                pressed = false;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
	}
	
	// a key listener for actions such as jump and fire
	private class ActionKeyListener implements KeyListener{
		boolean pressed = false;
		int currentKey;

		@Override
		public void keyPressed(KeyEvent e) {
			if(!pressed && updater.isRunning()) {
                pressed = true;
                currentKey = e.getKeyCode();
                model.doAction(currentKey);
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == currentKey){
                pressed = false;
            }
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
			
		}
		
		
	}

	
}
