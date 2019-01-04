package id.ststech.javabasic.aquarium.model;

import java.awt.Image;

import javax.swing.JLabel;

import id.ststech.javabasic.aquarium.container.InsideContainer;

public abstract class Fish extends JLabel implements Movable, Runnable {

	private final static int STEP_X = 1;
	private final static int STEP_Y = 1;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2430726441774335201L;
	protected Image currentImage;

	private int minLeft;
	private int maxRight;
	private int minTop;
	private int maxBottom;

	private FishHorizontalDirection horizontalDirection;
	private FishVerticalDirection verticalDirection;

	/**
	 * Default constructor, initialize everything
	 */
	public Fish() {
		
		// Make sure random the fish movement area
		this.randomMovementArea();
		this.randomHorizontalDirection();
		this.randomVerticalDirection();

		this.randomStartPosition();
		
		// Set size based on image
		this.setSize(getCurrentImage().getWidth(null), getCurrentImage().getHeight(null));

		new Thread(this).start();
	}

	/**
	 * Define the left image
	 * @return
	 */
	protected abstract Image getLeftImage();

	/**
	 * Define the right image
	 * @return
	 */
	protected abstract Image getRightImage();

	private void randomStartPosition() {
		// Set a random position
		int randomX = (int) (Math.random() * ((InsideContainer.getInstance().getWidth() - this.getWidth()) / 2)) + 50;
		int randomY = (int) (Math.random() * ((InsideContainer.getInstance().getHeight() - this.getHeight()) / 2)) + 50;
		this.setLocation(randomX, randomY);
	}
	
	private void randomMovementArea() {
		randomMinLeft();
		randomMinTop();
		randomMaxRight();
		randomMaxBottom();
	}
	private void randomHorizontalDirection() {
		switch ((int) (Math.random() * 2)) {
		case 0:
			this.horizontalDirection = FishHorizontalDirection.GO_LEFT;
			this.setCurrentImage(getLeftImage());
			break;
		case 1:
			this.horizontalDirection = FishHorizontalDirection.GO_RIGHT;
			this.setCurrentImage(this.getRightImage());
			break;
		}
	}
	private void randomVerticalDirection() {
		switch ((int) (Math.random() * 2)) {
		case 0:
			this.verticalDirection = FishVerticalDirection.UP;
			break;
		case 1:
			this.verticalDirection = FishVerticalDirection.DOWN;
			break;
		}
	}
	
	private void randomMinLeft() {
		this.minLeft = (int) (Math.random() * 100);
	}

	private void randomMaxRight() {
		InsideContainer container = InsideContainer.getInstance();
		this.maxRight = (int) (Math.random() * (container.getWidth() - 100));
	}

	private void randomMinTop() {
		this.minTop = (int) (Math.random() * 100);
	}

	private void randomMaxBottom() {
		InsideContainer container = InsideContainer.getInstance();
		this.maxBottom = (int) (Math.random() * (container.getHeight() - 100));
	}

	public void moveUp() {
		int y = this.getY();
		if (y > 0)
			this.setLocation(this.getX(), y - STEP_Y);
	}

	public void moveDown() {
		int y = this.getY();
		if (y < InsideContainer.getInstance().getHeight())
			this.setLocation(this.getX(), y + STEP_Y);

	}

	public void moveLeft() {
		int x = this.getX();
		if (x > 0)
			this.setLocation(x - STEP_X, this.getY());
		this.currentImage = this.getLeftImage();
	}

	public void moveRight() {
		int x = this.getX();
		if (x < InsideContainer.getInstance().getWidth())
			this.setLocation(x + STEP_X, this.getY());
		this.currentImage = this.getRightImage();
	}

	public Image getCurrentImage() {
		if (currentImage == null)
			currentImage = this.getLeftImage();
		return currentImage;
	}

	protected void setCurrentImage(Image currentImage) {
		this.currentImage = currentImage;
	}

	public void run() {
		while (true) {
			// System.out.println("minLeft: " + minLeft + " maxRight: " + maxRight);
			// System.out.println("X = " + this.getX() + " Y = " + this.getY());
			// System.out.println("GoingLeft = " + this.goingLeft);
			// System.out.println();

			if (horizontalDirection == FishHorizontalDirection.GO_LEFT) {
				this.moveLeft();
				if (this.getX() <= this.minLeft) {
					horizontalDirection = FishHorizontalDirection.GO_RIGHT;
					// this.randomMinLeft();
				}
			} else if (horizontalDirection == FishHorizontalDirection.GO_RIGHT) {
				this.moveRight();
				if (this.getX() >= this.maxRight) {
					horizontalDirection = FishHorizontalDirection.GO_LEFT;
					// this.randomMaxRight();
				}
			}

			if (verticalDirection == FishVerticalDirection.UP) {
				this.moveUp();
				if (this.getY() <= this.minTop) {
					verticalDirection = FishVerticalDirection.DOWN;
					// this.randomMinTop();
				}
			} else if (verticalDirection == FishVerticalDirection.DOWN) {
				this.moveDown();
				if (this.getY() >= this.maxBottom) {
					verticalDirection = FishVerticalDirection.UP;
					// this.randomMaxBottom();
				}
			}

			InsideContainer.getInstance().repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
