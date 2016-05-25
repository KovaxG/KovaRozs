package game;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class Server implements Runnable{
	
	private ArrayList<Town> towns = new ArrayList<Town>();
	private Gson gson = new GsonBuilder().create();
	
	public static void main(String[] args) {
		Thread thread = new Thread(new Server());
		thread.start();
	}
	
	public void run() {
		
		try {
			loadData();
			
			while (true) {
				server();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadData() {
		System.out.println("Server: Loading data from file...");
	    try {
	    	BufferedReader br = new BufferedReader(new FileReader("data.txt"));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        
	        String str = sb.toString();
	        str = str.substring(1, str.length() - 3);
	        String[] token = str.split("},");
	        towns = new ArrayList<Town>();
	        
	        for (int i = 0; i < token.length; i++) {
	        	token[i] = token[i] + "}";
	        	System.out.println(token[i]);
	        	Town town = gson.fromJson(token[i], Town.class);
	        	towns.add(town); 
	        }
	        
	        System.out.println(towns.get(1).hasTownCenter());
	        br.close();
	        System.out.println("Server: Loaded data");
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void saveData() {
		try {
			FileWriter fw = new FileWriter(new File("data.txt"));
			String str = gson.toJson(towns);
			fw.write(str);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void server() throws IOException {
		
		ServerSocket s = null;
		Socket socket = null;
		
		try {
			String line = "";
			s = new ServerSocket(2000);
			System.out.println("Server: Waiting for connections");
			// When a client connects to the server s.accept() returns a
			// socket that identifies the connection
			socket = s.accept();
			System.out.println("Server: New client connected to server");
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			System.out.println("Server: IO ready for transmission \n");
			
			
			line = in.readLine();
			System.out.println("Server: Server log: " + line);
			// read from gson
			if (line.equals("GET")) {
				String username = in.readLine();
				String password = in.readLine();
				
				int index = -1;
				
				for (int i = 0; i < towns.size(); i++) {
					if (towns.get(i).username.equals(username) && towns.get(i).password.equals(password)) {
						index = i;
					}
				}
				
				if (index == -1) {
					out.println("FAILED");
				}
				else {	
					System.out.println("Server: sending Json:");
					String json = gson.toJson(towns.get(index));
					System.out.println("Server sending: " + json);
					out.println(json);
				}
			}
			else if (line.equals("SET")) {
				while (!line.equals("STOP")) {
					line = in.readLine();
					System.out.println("Server: " + line);
					line.trim();
					Town town = gson.fromJson(line, Town.class);
					
					for (int i = 0; i < towns.size(); i++) {
						if (towns.get(i).username.equals(town.username) && towns.get(i).password.equals(town.password)) {
							towns.remove(i);
							towns.add(town);
							saveData();
						}
					}
				}
			}
			System.out.println("Server: Stopping.");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			s.close();
			if (socket != null) socket.close();
		}
	}
}
