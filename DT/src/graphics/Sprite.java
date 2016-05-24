package graphics;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Sprite {
	
	public final int HEIGHT;
	public final int WIDTH;
	public int[] pixels;
	public String path;
	
	public static Sprite Background = new Sprite(600, 400, "/Town_Background.png");
	public static Sprite TownCenter = new Sprite(64, 64, "/Town_TownCenter.png");
	public static Sprite WareHouse = new Sprite(64, 64, "/Town_WareHouse.png");
	public static Sprite ConstructionSite = new Sprite(64, 64, "/Town_ConstructionSite.png");
	
	public Sprite(int width, int height, String path) {
		HEIGHT = height;
		WIDTH = width;
		this.path = path;
		pixels = new int[HEIGHT * WIDTH];
		load();
	}
	
	private void load() {
		BufferedImage image;
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream(path)); 
			int width = image.getWidth();
			int height = image.getHeight();
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (Exception e) {
			System.err.println("Error while reading SpriteSheet!" + e.getMessage());
		}
	}
}