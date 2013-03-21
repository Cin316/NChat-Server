package com.nchat.server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class NChatServer {
	
	ServerSocket connectionSocket;
	boolean stopServer = false;
	ArrayList<String> log = new ArrayList<String>(2);
	ArrayList<User> connectedUsers = new ArrayList<User>(1);
	ArrayList<Socket> connectedSockets = new ArrayList<Socket>(1);
	
	public NChatServer(){
		
		//Try to start connection on server port (Start.port).
		System.out.println("Trying to connect to port " + Start.port + "...");
		try{
			connectionSocket = new ServerSocket(Start.port);
		}catch(IOException e){
			System.out.println("Error connecting to port " + Start.port + "!");
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Server started!");
		this.startServer();
		
		//Accept new connections.
		while(stopServer==false){
			try {
				new UserNChatThread(connectionSocket.accept(), this).start();
				System.out.println("New user connected!");
			} catch (IOException e) {
				System.out.println("A user failed to connect.");
				e.printStackTrace();
			}
		}
		
		//Attempt to stop the server.
		System.out.println("Stopping server...");
		try {
			connectionSocket.close();
		} catch (IOException e) {
			System.out.println("Error closing server port!");
			e.printStackTrace();
		}
		
		this.stopServer();
		
	}

	protected void startServer(){
		
	}
	
	protected void stopServer(){
		
	}
	
}
