package aliirawan.aquariumproject.container;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import aliirawan.aquariumproject.core.FishFactory;
import aliirawan.aquariumproject.core.FishType;
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
	JComboBox<FishType> comboType = new JComboBox<FishType>();

	/**
	 * Default constructor
	 */
	public TopPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		buttonAddFish.addActionListener(new ButtonAddFishHandler());

		FishType[] fishTypeClasses = FishFactory.getInstance().getFishTypeList();

		if (fishTypeClasses != null) {
			for (FishType item : fishTypeClasses) {
				comboType.addItem(item);
			}
		}

		add(buttonAddFish);
		add(comboType);
	}

	/**
	 * Default button click handler
	 * 
	 * @author Ali Irawan
	 *
	 */
	protected class ButtonAddFishHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// Check which fish chosen
			FishType fishType = (FishType) comboType.getSelectedItem();
			Fish newFish;
			try {
				newFish = (Fish) fishType.getClazz().newInstance();
				InsideContainer.getInstance().add(newFish);
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
}
