package game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import graphics.Screen;
import graphics.Sprite;
import input.Mouse;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static final String title = "Town";
	
	public JFrame frame;
	
	private Thread thread;
	private static final int width = Sprite.Background.WIDTH; 
	private static final int height = Sprite.Background.HEIGHT;
	private static final int scale = 2;
	public static boolean running = false;
	
	private BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] bufferPixels = ((DataBufferInt)buffer.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	private Mouse mouse;
	
	private Town town;
	
	public Game() {
		frame = new JFrame();
		
		this.setPreferredSize(new Dimension(width * scale, height * scale));
		
		screen = new Screen(buffer.getWidth() , buffer.getHeight(), town);
		mouse = new Mouse();
		
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		
		frame.add(this);
		frame.pack();
	}
	
	public void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			System.err.println("Could not join threads!");
		}
	}
	
	public void update() {
		if (mouse.pressed) {
			if (mouse.x > 400) {
				// build townCenter
				if (!town.hasTownCenter()) {
					town.buildTownCenter();
					System.out.println("Town Center Built!");
					sendData();
				}
			}
			else {
				// build warehouse
				if (!town.hasWareHouse()) {
					town.buildWareHouse();
					System.out.println("WareHouse Built!");
					sendData();
				}
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		screen.render(); // Stuff might go here
		
		for (int i = 0; i < buffer.getWidth() * buffer.getHeight(); i++) {
			bufferPixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(90, 100, 110));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.drawImage(buffer, 0, 0, this.getWidth(), this.getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	public void run() {
		
		//<==================================================================================>
				Thread thread = new Thread(new Server());
				thread.start();
				
				
				
		
		boolean succes = false;
		while (!succes) {
			succes = true;
			try {
				String input = JOptionPane.showInputDialog("Please input username and password separated by a space");
				String[] token = input.split(" ");
				succes = getData(token[0], token[1]);
			}
			catch(Exception e) {
				succes = false;
			}
		}
		screen.town = town;
		
		long lastTime = System.nanoTime();
		long now = System.nanoTime();
		long delta = 0;
		long timer = 0;
		double fps = 60; // Frames Per Second
		int updateCounter = 0;
		int renderCounter = 0;
		
		this.requestFocus();
		
		while(running) {
			now = System.nanoTime();
			delta += now - lastTime;
			lastTime = now;
			
			
			
			if (delta >= 1000000000 / fps) {
				delta -= 1000000000 / fps;
				timer++;
				update();
				updateCounter++;
			}
			render();
			renderCounter++;
			 
			if (timer >= fps) {
				timer = 0;
				frame.setTitle(title + " | " + updateCounter + " Updates " + renderCounter + " Frames");
				updateCounter = 0;
				renderCounter = 0;
			}
		}
		stop();
	}
	
	public boolean getData(String userName, String pass) {
		String msg = "";
		Socket socket = null;
		System.out.println("Game: Getting Data \n");
		try {
			socket = new Socket("localhost", 2000);
			System.out.println("Game: Socket created \n");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			System.out.println("Game: IO ready for transmission \n");
			
			Gson g = new GsonBuilder().create();
			//g.toJson(town, System.out);

			//g.toJson(town, out);
			out.println("GET");
			out.println(userName);
			out.println(pass);
			// Wait for response from server
			String str = in.readLine(); // Get Town Json from database
			System.out.println("Game received: " + str);
			town = g.fromJson(str, Town.class);
			System.out.println("Game: Town Center and WareHouse: " + town.hasTownCenter() + " " + town.hasWareHouse());
			
			out.println("STOP");
			in.close();
			out.close();
			socket.close();
			System.out.println("Game: Succes, local data updated.");
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendData() {
		String msg = "";
		Socket socket = null;
		System.out.println("Game: Sending Data \n");
		try {
			socket = new Socket("localhost", 2000);
			System.out.println("Game: Socket created \n");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			System.out.println("Game: IO ready for transmission \n");
			
			Gson g = new GsonBuilder().create();
			String json = g.toJson(town);
			
			out.println("SET");
			out.println(json);
			out.println("STOP");
			
			in.close();
			out.close();
			socket.close();
			System.out.println("Game: Succes, server data updated.");
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title + " | Collecting Data...");
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}
}