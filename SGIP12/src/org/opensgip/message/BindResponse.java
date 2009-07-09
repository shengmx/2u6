package org.opensgip.message;

import static org.opensgip.message.util.BytesOperation.asFixedLengthISO88591Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedBigInteger12Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedInt1Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedInt4Bytes;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class BindResponse extends AbstractMessage {
	public static class Encoder implements ProtocolEncoder {
		public void encode(IoSession session, Object message,
				ProtocolEncoderOutput out) throws Exception {			
			BindResponse bindResponse = (BindResponse)message;	
			
			ByteBuffer buffer = ByteBuffer.allocate(20+9, false);
			
			//encode header, 20 bytes
			buffer.put(asUnsignedInt4Bytes(bindResponse.getHeader().getMessageLength()));
			buffer.put(asUnsignedInt4Bytes(bindResponse.getHeader().getCommandId()));
			buffer.put(asUnsignedBigInteger12Bytes(bindResponse.getHeader().getSequenceNumber()));
			
			//encode body, 9 bytes
			buffer.put(asUnsignedInt1Bytes(bindResponse.getBody().getResult()));
			buffer.put(asFixedLengthISO88591Bytes(bindResponse.getBody().getReserve(), 8));
			
			buffer.flip();
			out.write(buffer);
		}
		
		public void dispose(IoSession session) throws Exception {
			//nothing to dispose		
		}			
	}
	
	
	public class Body extends SimpleResponseBody {
		public Body() {
			super();
		}
		public Body(short result, String reserve) {
			super(result,reserve);
		}
	}

	public BindResponse() {
		header = new MessageHeader();
		body = new Body();
	}
	
	public BindResponse(short result, String reserve) {
		header = new MessageHeader();
		
		Body b = new Body(result, reserve);
		
		body = b;
	}
	
	public Body getBody() {
		return (Body)body;
	}	
}
