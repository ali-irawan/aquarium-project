# Aquarium Project

Java Based application to demonstrate how to use interface, inheritance

You will need:
- [Java 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Apache Maven](https://maven.apache.org)

How to run

```
git clone https://github.com/ali-irawan/aquarium-project.git
```

Go to folder

```
cd aquarium-project
mvn exec:java
```

## Code explain

### InsideContainer, initialization
This class is represent the Fish Container for the Aquarium

At the constructor


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
		fishCollection = new Vector<Fish>();
	}
	

We  initialize the baseBackground for the water image, and initialize fishCollection variable to hold the fishes

### InsideContainer, the paint method

In the paint method we override:
- Create a buffer image for Double Buffering (Drawing Image in memory first)
- Draw the background SeaScape
- Loop all fishCollection and draw the fish current image ( each fish will have two type of image, left or right)

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
	
### Fish Abstract Class
Fish is an abstract class, this class cannot be instantiate

	Fish fish = new Fish(); // This code will be error
 	
We will need to inherits from Fish and make a new class, for example RedFish

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
	
The RedFish class, use static initializer to load two image, **fish-red.png** and **fish-red-right.png**
You will see that RedFish will be forced to override two abstract methods from Fish class:
- getLeftImage()
- getRightImage()
But many methods its already inherited from Fish class:
- moveUp()
- moveDown()
- moveLeft()
- moveRight()
- getCurrentImage()
- run() - this is for Runnable thread

Some of them is either **protected** or **private**

**private** method in Fish class means the method is only visible inside the Fish class, and cannot be invoke from outside Fish class, for examples:
- randomStartPosition
- randomMovementArea
- randomHorizontalDirection
- randomVerticalDirection
- randomMinLeft
- randomMaxRight
- randomMinTop
- randomMaxBottom

**protected** method in Fish class means the method is only visible inside the Fish class and it subclasses, so RedFish as a subclass still can invoke it directly
- setCurrentImage(Image currentImage)
By providing **private** modifiers, we hide the complexity of the Fish class, so when we create a new class, extending from Fish, we can only see the public/protected and can override the required method

### Using FishFactory Class
FishFactory is a class that can be used to instantiate various kinds of Fish, we are recommending to create a fish object using FishFactory. FishFactory has a mechanism to list all fish using the file in **src/main/resources/index**

```
Red Fish,Red Fish,aliirawan.aquariumproject.model.impl.RedFish
```

The FishFactory will read the index file and load it to program
See the method

	
	private void registerFishTypesFromIndex() {
	
		BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/index")));
		
		String input;
		try {
			input = reader.readLine();
			while(input!=null) {
				String[] tokens = input.split(",");
				registerFishType(tokens[0], new FishType(tokens[1], Class.forName(tokens[2])));
				input = reader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
# Easily adding new Fish
- Create a new class for example **YellowFish.java** in package **aliirawan.aquariumproject.model.impl**
- Implements the required method, make sure you use image for example **fish-yellow.png** and **fish-yellow-right.png**
- Put those images in **src/main/resources**
- Add some line the index file


	Yellow Fish,Yellow Fish,aliirawan.aquariumproject.model.impl.YellowFish
	
# We have separated branches

## master
- Only has RedFish

## branch: with-two-fishes
- Has two fish, RedFish and YellowFish 