package org.opensgip.message;

import static org.opensgip.message.util.BytesOperation.asFixedLengthISO88591Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedBigInteger12Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedInt1Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedInt4Bytes;
import static org.opensgip.message.util.BytesOperation.fromFixedLengthISO88591Bytes;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;


public class BindRequest extends AbstractMessage {
	public static long SGIP_BIND = 0x1;
	
	public static class Encoder implements ProtocolEncoder {
		public void encode(IoSession session, Object message,
				ProtocolEncoderOutput out) throws Exception {
			
			BindRequest bindRequest = (BindRequest)message;	
			
			ByteBuffer buffer = ByteBuffer.allocate(20+41, false);
			
			//encode header, 20 bytes
			buffer.put(asUnsignedInt4Bytes(bindRequest.getHeader().getMessageLength()));
			buffer.put(asUnsignedInt4Bytes(bindRequest.getHeader().getCommandId()));
			buffer.put(asUnsignedBigInteger12Bytes(bindRequest.getHeader().getSequenceNumber()));
			
			//encode body, 41 bytes
			buffer.put(asUnsignedInt1Bytes(bindRequest.getBody().getLoginType()));
			buffer.put(asFixedLengthISO88591Bytes(bindRequest.getBody().getLoginName(), 16));
			buffer.put(asFixedLengthISO88591Bytes(bindRequest.getBody().getLoginPassword(), 16));
			buffer.put(asFixedLengthISO88591Bytes(bindRequest.getBody().getReserve(), 8));
			
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
			if (in.remaining()>=20+41) {
				int start = in.position();
				
				MessageHeader header = MessageHeader.tryDecodeHeader(SGIP_BIND, in);
				if (header==null) {
					in.position(start);
					return false;
				}
				
				Body body = tryDecodeBody(in);
				if (body==null) {
					in.position(start);
					return false;
				}
				
				BindRequest bindRequest = new BindRequest(header, body);
				
				out.write(bindRequest);				
				return true;
			} else {
				return false;
			}
		}

		private Body tryDecodeBody(ByteBuffer in) throws Exception {
			int start = in.position();
			
			if (in.remaining()>=41) {
				try {
					Body b = new Body();							
					short loginType = in.getUnsigned();				
					String loginName = fromFixedLengthISO88591Bytes(in, 16);
					String loginPassword = fromFixedLengthISO88591Bytes(in, 16);
					String reserve = fromFixedLengthISO88591Bytes(in, 8);
					
					b.setLoginType(loginType);
					b.setLoginName(loginName);
					b.setLoginPassword(loginPassword);
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
	
	public static class Body extends AbstractMessageBody {
		short loginType = 0;
		String loginName = "";
		String loginPassword = "";
		String reserve = "";			
		
		public short getLoginType() {
			return loginType;
		}
		public void setLoginType(short loginType) {
			this.loginType = loginType;
		}
		public String getLoginName() {
			return loginName;
		}
		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}
		public String getLoginPassword() {
			return loginPassword;
		}
		public void setLoginPassword(String loginPassword) {
			this.loginPassword = loginPassword;
		}
		public String getReserve() {
			return reserve;
		}
		public void setReserve(String reserve) {
			this.reserve = reserve;
		}	
	}
	
	public BindRequest() {
		header = new MessageHeader();
		body = new Body();		
	}
	
	public BindRequest(MessageHeader header, Body body) {
		this.header = header;
		this.body = body;
	}
	
	public BindRequest(short loginType,String loginName, String loginPassword, String reserve) {
		header = new MessageHeader();
		
		Body b = new Body();
		b.setLoginType(loginType);
		b.setLoginName(loginName);
		b.setLoginPassword(loginPassword);		
		b.setReserve(reserve);
		
		body = b;
	}
	
	public Body getBody() {
		return (Body)body;
	}
}
