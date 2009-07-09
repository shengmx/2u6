package org.opensgip.application.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.opensgip.message.factory.BindCodecFactory;

public class IPAIServer {
	public static final int PORT = 9901;
	
	public static void main(String[] args) throws IOException {
		SGIPServerIOHandler handler = new SGIPServerIOHandler();
        SocketAcceptor acceptor = new SocketAcceptor();
        acceptor.getFilterChain().addLast("protocol", new ProtocolCodecFilter(new BindCodecFactory(false)));
        acceptor.bind(new InetSocketAddress(PORT), handler);
        System.out.println("server is listenig at port " + PORT);
	}
}
