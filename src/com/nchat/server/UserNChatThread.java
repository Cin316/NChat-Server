package com.nchat.server;

import java.net.Socket;

public class UserNChatThread extends Thread{
	
	NChatServer parentServer;
	Socket userSocket;
	User user;
	
	public UserNChatThread(Socket u, NChatServer p){
		parentServer = p;
		userSocket = u;
		user = new User( userSocket.getInetAddress().toString() );
	}
	
}
