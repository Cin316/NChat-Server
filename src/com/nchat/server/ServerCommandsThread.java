package com.nchat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerCommandsThread extends Thread {
	
	NChatServer parentServer;
	int outputLine;
	BufferedReader input;
	
	public ServerCommandsThread(NChatServer p){
		parentServer = p;
		input = new BufferedReader (new InputStreamReader (System.in));
	}
	
	public void run(){
		
		while(true){
			printMessages();
			getInput();
		}
		
	}
	
	private void printMessages(){
		
		for(int k=0; k < parentServer.log.size()-outputLine; k++){
			System.out.println(parentServer.log.get(outputLine));
			outputLine++;
		}
		
	}
	
	private void getInput(){
		
		String text = "";
		
		try {
			if(input.ready()){
				text = input.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(text!=""){
			processInput(text);
		}
		
	}
	
	private void processInput(String s){
		
	}
	
}
