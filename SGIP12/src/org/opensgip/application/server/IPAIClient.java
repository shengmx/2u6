package org.opensgip.application.server;

import java.net.InetSocketAddress;

import org.apache.mina.common.ConnectFuture;
import org.apache.mina.common.IoSession;
import org.apache.mina.common.RuntimeIOException;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.SocketConnector;
import org.opensgip.message.BindRequest;
import org.opensgip.message.factory.BindCodecFactory;

public class IPAIClient {
	public static final int CONNECT_TIMEOUT = 3000;

	private String host;
	private int port;
	private SocketConnector connector;
	private IoSession session;

	public IPAIClient(String host, int port) {
		this.host = host;
		this.port = port;
		connector = new SocketConnector();
		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new BindCodecFactory(true)));
	}

	public void connect() {
		SGIPClientIOHandler handler = new SGIPClientIOHandler();
		ConnectFuture connectFuture = connector.connect(new InetSocketAddress(
				host, port), handler);
		connectFuture.join(CONNECT_TIMEOUT);
		try {
			session = connectFuture.getSession();
		} catch (RuntimeIOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		if (session != null) {
			session.close().join(CONNECT_TIMEOUT);
			session = null;
		}
	}

	public void sendRequest(BindRequest bindRequest) {
		session.write(bindRequest);
	}

	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {

	}
}
