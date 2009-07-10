package org.opensgip.message;


public class AbstractMessage {
	public static long SGIP_BIND				= 0x1L;
	public static long SGIP_BIND_RESP 			= 0x80000001L;
	
	protected MessageHeader header = null;
	protected AbstractMessageBody body = null;

	public MessageHeader getHeader() {
		return header;
	}
	
	public AbstractMessageBody getMessageBody() {
		return body;
	}
}
