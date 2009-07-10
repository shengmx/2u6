package org.opensgip.message;


public class TraceRequest extends AbstractMessage {
	public static class Body extends AbstractMessageBody {
		long submitSequenceNumber = 0;
		String userNumber = "";
		String reserve = "";		
		
		public long getSubmitSequenceNumber() {
			return submitSequenceNumber;
		}
		public void setSubmitSequenceNumber(long submitSequenceNumber) {
			this.submitSequenceNumber = submitSequenceNumber;
		}
		public String getUserNumber() {
			return userNumber;
		}
		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}
		public String getReserve() {
			return reserve;
		}
		public void setReserve(String reserve) {
			this.reserve = reserve;
		}
	}
	
	public TraceRequest() {
		header = new MessageHeader();
		body = new Body();		
	}
	
	public TraceRequest(long submitSequenceNumber,String userNumber, String reserve) {
		header = new MessageHeader();
		
		Body b = new Body();
		b.setSubmitSequenceNumber(submitSequenceNumber);
		b.setUserNumber(userNumber);
		b.setReserve(reserve);
		
		body = b;
	}
	
	public Body getBody() {
		return (Body)body;
	}
}
