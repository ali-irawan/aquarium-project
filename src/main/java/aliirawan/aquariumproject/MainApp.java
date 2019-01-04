/**
 * 
 */
package aliirawan.aquariumproject;

import aliirawan.aquariumproject.container.MainFrame;

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
