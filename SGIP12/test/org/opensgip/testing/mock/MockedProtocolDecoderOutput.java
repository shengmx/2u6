package org.opensgip.testing.mock;

import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MockedProtocolDecoderOutput implements ProtocolDecoderOutput {
	private Object object = null;

	public void flush() {
	}

	public void write(Object object) {
		this.object = object;
	}
	
	public Object getObject() {
		return object;
	}

}
