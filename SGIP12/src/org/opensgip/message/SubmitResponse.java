package org.opensgip.message;

public class SubmitResponse extends AbstractMessage {
	public static class Body extends SimpleResponseBody {
		public Body() {
			super();
		}
		public Body(short result, String reserve) {
			super(result,reserve);
		}
	}

	public SubmitResponse() {
		header = new MessageHeader();
		body = new Body();
	}
	
	public SubmitResponse(short result, String reserve) {
		header = new MessageHeader();
		
		Body b = new Body(result, reserve);		
		body = b;
	}
	
	public Body getBody() {
		return (Body)body;
	}
}
