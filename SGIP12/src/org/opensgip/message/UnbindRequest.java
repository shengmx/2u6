package org.opensgip.message;

public class UnbindRequest extends AbstractMessage {
	public UnbindRequest() {
		header = new MessageHeader();
		body = null;		
	}
}
