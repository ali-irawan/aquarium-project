package aliirawan.aquariumproject.model.impl;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import aliirawan.aquariumproject.model.Fish;

public class YellowFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5925239944846565376L;
	private static Image leftRedFish;
	private static Image rightRedFish;

	static {
		try {
			leftRedFish = ImageIO.read(YellowFish.class.getResourceAsStream("/img/fish-yellow.png"));
			rightRedFish = ImageIO.read(YellowFish.class.getResourceAsStream("/img/fish-yellow-right.png"));
		} catch (IOException e) {
			System.exit(1);
		}
	}


	public YellowFish() {
		super();

	}

	@Override
	protected Image getLeftImage() {
		return leftRedFish;
	}

	@Override
	protected Image getRightImage() {
		return rightRedFish;
	}

}
