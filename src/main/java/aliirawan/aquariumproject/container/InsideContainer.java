package aliirawan.aquariumproject.container;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import aliirawan.aquariumproject.model.Fish;

/**
 * The aquarium container
 * 
 * @author Ali Irawan
 *
 */
public class InsideContainer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 675421455881391779L;

    /**
     * Main instance container
     */
	private static InsideContainer container;

	/**
	 * Hold fish collections
	 */
	private Vector<Fish> fishCollection;

	/**
	 * Hold background image
	 */
	private Image baseBackground;

	/**
	 * Static method for getting singleton object
	 * @return
	 */
	public static InsideContainer getInstance() {
		if (container == null) {
			container = new InsideContainer();
		}
		return container;
	}

	/**
	 * Default constructor.  make it private, only can be instantiated using getInstance()
	 */
	private InsideContainer() {
		try {
			// Load based on image in classpath (see main/resources)
			baseBackground = ImageIO.read(this.getClass().getResourceAsStream("/img/SeaScape.jpg"));
		} catch (IOException e) {
			System.out.println("Could not load /img/SeaScape.jpg");
			System.exit(1);
		}

		// Initialize the fish collections
		fishCollection = new Vector<Fish>();
	}

	/**
	 * Add some fish to container
	 * @param aFish
	 */
	public void add(Fish aFish) {
		fishCollection.add(aFish);
		this.repaint();
	}

	/**
	 * Override what to paint
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		BufferedImage bufferImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = (Graphics2D) bufferImage.createGraphics();
		g2.drawImage(this.baseBackground, 0, 0, this.getWidth(), this.getHeight(), null);
		
		// Draw the sprites
		for(Fish item : this.fishCollection) {
			g2.drawImage(item.getCurrentImage(), item.getX(), item.getY(), null);
		}

		g.drawImage(bufferImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	/**
	 * Shows the fish count inside container
	 * @return
	 */
	public int getCount() {
		return this.fishCollection.size();
	}
}
