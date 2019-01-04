package aliirawan.aquariumproject.container;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * MainFrame main application
 * 
 * @author Ali Irawan
 *
 */
public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4484891114354651061L;
	
	private static MainFrame mainFrame;
	
	/**
	 * Static method for getting singleton object
	 * @return
	 */
	public static MainFrame getInstance() {
		if( mainFrame == null ) {
			mainFrame = new MainFrame();
		}
		return mainFrame;
	}

	/**
	 * Default constructor, make it private, only can be instantiated using getInstance()
	 */
	private MainFrame() {
		
		// Set the container width and height
		InsideContainer container = InsideContainer.getInstance();
		container.setSize(900, 600);
		
		setTitle("Aquarium Project");
		setSize(container.getWidth(), container.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Centering screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		// Set layout and components
		this.setLayout(new BorderLayout());
		this.add(new TopPanel(), BorderLayout.NORTH);
		this.add(container, BorderLayout.CENTER);
		
		// Play sound
		new BackgroundSound();
	}
}
