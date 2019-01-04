package aliirawan.aquariumproject.container;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import aliirawan.aquariumproject.model.Fish;
import aliirawan.aquariumproject.model.impl.RedFish;

/**
 * Top panel object
 * 
 * @author Ali Irawan
 *
 */
public class TopPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8383794170958056561L;
	JButton buttonAddFish = new JButton("Add Fish");
	
	/**
	 * Default constructor
	 */
	public TopPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(buttonAddFish);

		buttonAddFish.addActionListener(new ButtonAddFishHandler());
	}

	/**
	 * Default button click handler
	 * @author Ali Irawan
	 *
	 */
	protected class ButtonAddFishHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Fish sprite = new RedFish();
			InsideContainer.getInstance().add(sprite);

		}

	}
}
