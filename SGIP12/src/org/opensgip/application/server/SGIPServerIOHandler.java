package org.opensgip.application.server;

import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.opensgip.message.BindRequest;
import org.opensgip.message.BindResponse;

public class SGIPServerIOHandler extends IoHandlerAdapter {
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println(message.getClass().getName());
		
		BindRequest request = (BindRequest)message;
		request.getBody().getLoginPassword();		
		
		BindResponse response = new BindResponse();
		session.write(response);
	}
}
