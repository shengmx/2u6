package org.opensgip.message.factory;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.opensgip.message.BindRequest;


public class BindCodecFactory implements ProtocolCodecFactory {
	private ProtocolEncoder encoder = null;
	private ProtocolDecoder decoder = null;
	
	public BindCodecFactory(boolean client) {
		if (client) {
			encoder = new BindRequest.Encoder();
			//decoder = new BindResponse.Decoder();
		} else {
			//encoder = new BindResponse.Encoder();
			decoder = new BindRequest.Decoder();
		}
	}	
	
	public ProtocolDecoder getDecoder() throws Exception {
		return decoder;
	}
	public ProtocolEncoder getEncoder() throws Exception {
		return encoder;
	}	
}
