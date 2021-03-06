package aliirawan.aquariumproject.model.impl;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

import aliirawan.aquariumproject.model.Fish;

public class RedFish extends Fish {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5344563095423316659L;
	private static Image leftRedFish;
	private static Image rightRedFish;

	static {
		try {
			leftRedFish = ImageIO.read(RedFish.class.getResourceAsStream("/img/fish-red.png"));
			rightRedFish = ImageIO.read(RedFish.class.getResourceAsStream("/img/fish-red-right.png"));
		} catch (IOException e) {
			System.exit(1);
		}
	}

	public RedFish() {
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
