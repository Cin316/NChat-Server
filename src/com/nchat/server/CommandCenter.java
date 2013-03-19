package com.nchat.server;

public class CommandCenter {
	
	public static String analyzeMessage(UserNChatThread user, String chat){
		String outputText = "";
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
		return "Command Output.";
	}
	
}
