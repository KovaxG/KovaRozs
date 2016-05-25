package graphics;

import java.awt.Color;

import game.Town;

public class Screen {
	private final int WIDTH;
	private final int HEIGHT;
	public int[] pixels;
	
	public Town town;
	
	private int magicPink = 0xffff00ff;
	
	public Screen(int width, int height, Town t) {
		this.WIDTH = width;
		this.HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		town = t;
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 100;
		}
	}
	
	public void render() {
		for (int i = 0; i < Sprite.Background.WIDTH; i++) {
			for (int j = 0; j < Sprite.Background.HEIGHT; j++) {
				pixels[i + j * WIDTH] = Sprite.Background.pixels[i + j * Sprite.Background.WIDTH];
			}
		}
		
		Sprite townCenter;
		if (town.hasTownCenter()) townCenter = Sprite.TownCenter;
		else townCenter = Sprite.ConstructionSite;
		
		int townCenterXOffset = (WIDTH - townCenter.WIDTH) / 2;
		int townCenterYOffset = (HEIGHT - townCenter.HEIGHT) / 2;
		for (int i = 0; i < 0 + townCenter.WIDTH; i++) {
			for (int j = 0; j < 0 + townCenter.HEIGHT; j++) {
				int pixel = townCenter.pixels[i + j * townCenter.WIDTH];
				if (pixel != magicPink) pixels[(i + townCenterXOffset)  + (j + townCenterYOffset) * WIDTH] = pixel;
			}
		}
		
		Sprite wareHouse;
		if (town.hasWareHouse()) wareHouse = Sprite.WareHouse;
		else wareHouse = Sprite.ConstructionSite;
		
		int wareHouseXOffset = (WIDTH - wareHouse.WIDTH) / 2 - 150;
		int wareHouseYOffset = (HEIGHT - wareHouse.HEIGHT) / 2;
		for (int i = 0; i < 0 + wareHouse.WIDTH; i++) {
			for (int j = 0; j < 0 + wareHouse.HEIGHT; j++) {
				int pixel = wareHouse.pixels[i + j * wareHouse.WIDTH];
				if (pixel != magicPink) pixels[(i + wareHouseXOffset)  + (j + wareHouseYOffset) * WIDTH] = pixel;
			}
		}
	}
}
