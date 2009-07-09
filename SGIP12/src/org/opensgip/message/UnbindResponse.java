package org.opensgip.message;

public class UnbindResponse extends AbstractMessage {
	public UnbindResponse() {
		header = new MessageHeader();
		body = null;		
	}
}
