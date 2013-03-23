package com.nchat.server;

public class CommandCenter {
	
	public static String analyzeMessage(UserNChatThread user, String chat){
		String outputText = "null test";
		if( !chat.equals("") ){
			
			if( chat.charAt(0) == '/' ){
				outputText = executeCommand(user, chat);
			}else{
				
				outputText = chat;
				
			}
			
		}
		return outputText;
	}
	
	public static String executeCommand(UserNChatThread user, String command){
		if(command.equals("/stop")){
			user.parentServer.stopServer = true;
			return "Stopping server...";
		}else if(command.equals("/leave")){
			user.stopThread = true;
			return "";
		}else{
			return "Invalid command.";
		}
	}
	
}
