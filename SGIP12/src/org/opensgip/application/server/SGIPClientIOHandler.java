package org.opensgip.application.server;

import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.opensgip.message.BindResponse;

public class SGIPClientIOHandler extends IoHandlerAdapter {
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println(message.getClass().getName());
		
		BindResponse request = (BindResponse)message;
		request.getBody().getResult();		
		
		BindResponse response = new BindResponse();
		session.write(response);
	}
}
