package org.opensgip.testing.mock;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.WriteFuture;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MockedProtocolEncoderOutput implements ProtocolEncoderOutput {
	private ByteBuffer output = null;

	public WriteFuture flush() {		
		return null;
	}

	public void mergeAll() {
	}

	public void write(ByteBuffer output) {
		this.output = output;		
	}
	
	public ByteBuffer getOutput() {
		return output;
	}

}
