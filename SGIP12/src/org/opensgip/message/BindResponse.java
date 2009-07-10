package org.opensgip.message;

import static org.opensgip.message.util.BytesOperation.asFixedLengthISO88591Bytes;
import static org.opensgip.message.util.BytesOperation.asSequence12Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedInt1Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedInt4Bytes;
import static org.opensgip.message.util.BytesOperation.fromFixedLengthISO88591Bytes;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class BindResponse extends AbstractMessage {
	public static class Encoder implements ProtocolEncoder {
		public void encode(IoSession session, Object message,
				ProtocolEncoderOutput out) throws Exception {			
			BindResponse bindResponse = (BindResponse)message;	
			
			ByteBuffer buffer = ByteBuffer.allocate(MessageHeader.HEADER_BYTES_SIZE+Body.BODY_BYTES_SIZE, false);
			
			//encode header, 20 bytes
			buffer.put(asUnsignedInt4Bytes(bindResponse.getHeader().getMessageLength()));
			buffer.put(asUnsignedInt4Bytes(bindResponse.getHeader().getCommandId()));
			buffer.put(asSequence12Bytes(bindResponse.getHeader().getSequenceNumber()));
			
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
	
	public static class Decoder extends CumulativeProtocolDecoder {

		@Override
		protected boolean doDecode(IoSession session, ByteBuffer in,
				ProtocolDecoderOutput out) throws Exception {
			if (in.remaining()>=MessageHeader.HEADER_BYTES_SIZE+Body.BODY_BYTES_SIZE) {
				int start = in.position();
				
				MessageHeader header = MessageHeader.tryDecodeHeader(SGIP_BIND_RESP, in);
				if (header==null) {
					in.position(start);
					return false;
				}
				
				Body body = tryDecodeBody(in);
				if (body==null) {
					in.position(start);
					return false;
				}
				
				BindResponse bindRequest = new BindResponse(header, body);
				
				out.write(bindRequest);				
				return true;
			} else {
				return false;
			}
		}
		
		private Body tryDecodeBody(ByteBuffer in) throws Exception {
			int start = in.position();
			
			if (in.remaining()>=Body.BODY_BYTES_SIZE) {
				try {
					Body b = new Body();							
					short r = in.getUnsigned();				
					String reserve = fromFixedLengthISO88591Bytes(in, 8);
					
					b.setResult(r);
					b.setReserve(reserve);
					
					return b;
				} catch(Exception ex) {
					in.position(start);
					return null;
				}
			} else {
				return null;
			}
		}		
		
	}	
	
	public static class Body extends SimpleResponseBody {
		static int BODY_BYTES_SIZE = 9;
		
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
	
	public BindResponse(MessageHeader header, Body body) {
		this.header = header;
		this.body = body;
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
