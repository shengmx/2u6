package org.opensgip.message;


public class AbstractMessage {
	protected MessageHeader header = null;
	protected AbstractMessageBody body = null;

	public MessageHeader getHeader() {
		return header;
	}
	
	public AbstractMessageBody getMessageBody() {
		return body;
	}
}
