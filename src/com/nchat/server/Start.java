package com.nchat.server;

public class Start {
	
	public static final int port = 13131;
	public static final String version = "v 1.0";
	
	public static void main(String[] args){
		
		System.out.println("NChat Server " + Start.version + ".");
		NChatServer chatServer = new NChatServer();
		
	}
	
}
