package com.nchat.server;

public class Start {
	
	public static int port = 13132;
	public static final String version = "v 1.0";
	
	public static void main(String[] args){
		
		if (args.length>=1){
			port = Integer.parseInt(args[0]);
		}
		
		System.out.println("NChat Server " + Start.version + ".");
		NChatServer chatServer = new NChatServer();
		
	}
	
}
