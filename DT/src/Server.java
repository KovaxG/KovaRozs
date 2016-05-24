import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import game.Town;

import java.sql.*;

public class Server implements Runnable{
	
	public void run() {
		
		try {
			
			
			while (true) {
				server();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void server() throws IOException {
		
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
				Gson gson = new GsonBuilder().create();
				System.out.println("Server: sending Json:");
				String json = gson.toJson(new Town(false, false));
				System.out.println("Server sending: " + json);
				out.println(json);
			}
			else if (line.equals("SET")) {
				Gson gson = new GsonBuilder().create();
				while (!line.equals("STOP")) {
					line = in.readLine();
					System.out.println("Server: " + line);
					line.trim();
					Town town = gson.fromJson(line, Town.class);
					
					
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
