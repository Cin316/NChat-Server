package com.nchat.server;

import java.net.Socket;
import java.io.*;

public class UserNChatThread extends Thread{
	
	NChatServer parentServer;
	Socket userSocket;
	User user;
	boolean stopThread = false;
	PrintWriter output;
	BufferedReader clientInput;
	int outputLine = 0;
	
	public UserNChatThread(Socket u, NChatServer p){
		
		parentServer = p;
		userSocket = u;
		user = new User( userSocket.getInetAddress().toString() );
		parentServer.connectedUsers.add(user);
		parentServer.connectedSockets.add(userSocket);
		
	}
	
	public void run(){
		
		//Try to establish write connection with client.
		try{
			output = new PrintWriter(userSocket.getOutputStream(), true);
		}catch(IOException e){
			System.out.println("Error writing to client: " + user.getName() + "(" + user.getIP() + ").");
			e.printStackTrace();
		}
		
		//Try to establish read connection with client.
		try{
			clientInput = new BufferedReader( new InputStreamReader(userSocket.getInputStream()) );
		}catch(IOException e){
			System.out.println("Error reading from client: " + user.getName() + "(" + user.getIP() + ").");
			e.printStackTrace();
		}
		
		//Say that user has joined.
		parentServer.log.add("User " + user.getName() + " has joined the chat!");
		
		
		while(stopThread = false){
			
			String a = getClientInput();
			analyzeInput(a);
			sendMessages();
			
		}
		
		
		//Say that user is leaving.
		parentServer.log.add("User " + user.getName() + " has left.");
		
		//Try to disconnect input and output streams.
		try{
			clientInput.close();
			output.close();
		}catch(IOException e){
			System.out.println("Error closing input and output streams for client: " + user.getName() + "(" + user.getIP() + ").");
			e.printStackTrace();
		}
		
		//Try to disconnect input and output streams.
		try{
			userSocket.close();
		}catch(IOException e){
			System.out.println("Error disconnecting client " + "(" + user.getIP() + ").");
			e.printStackTrace();
		}
	}

	protected String getClientInput(){
		
		String input = "";
		
		//Try to read input from client.
		try{
			
			if(clientInput.ready()){
				input = clientInput.readLine();
			}
			
		} catch (IOException e) {
			System.out.println("Error reading from client " + user.getName() + "(" + user.getIP() + ").");
			e.printStackTrace();
		}
		
		return input;
		
	}
	
	protected void analyzeInput(String s){
		
		String text = CommandCenter.analyzeMessage(this, s);
		parentServer.log.add(text);
		
	}
	
	protected void sendMessages(){
		
		//Run through every new message in the log.
		for(int k=0; k < parentServer.log.size()-outputLine; k++){
			
			sendMessage( parentServer.log.get(outputLine) );
			outputLine++;
			
		}
		
	}
	
	protected void sendMessage(String s){
		
		//Try to send message to client.
		try{
			output.println(s);
		}catch(Exception e){
			System.out.println("Error writing to client " + user.getName() + "(" + user.getIP() + ").");
			e.printStackTrace();
		}
		
	}
	
}
