/**
 * 
 */
package id.ststech.javabasic.aquarium;

import id.ststech.javabasic.aquarium.container.MainFrame;

/**
 * @author Ali Irawan
 *
 */
public class MainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MainFrame app = MainFrame.getInstance();
		app.setVisible(true);
	}

}
