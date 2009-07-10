package org.opensgip.message;

public class UserRptRequest extends AbstractMessage {
	public static class Body extends AbstractMessageBody {
		String spNumber = "";
		String userNumber = "";
		long userCondition = 0;
		String reserve = "";
		
		public String getSpNumber() {
			return spNumber;
		}
		public void setSpNumber(String spNumber) {
			this.spNumber = spNumber;
		}
		public String getUserNumber() {
			return userNumber;
		}
		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}
		public long getUserCondition() {
			return userCondition;
		}
		public void setUserCondition(long userCondition) {
			this.userCondition = userCondition;
		}
		public String getReserve() {
			return reserve;
		}
		public void setReserve(String reserve) {
			this.reserve = reserve;
		}
	}
	
	public UserRptRequest() {
		header = new MessageHeader();
		body = new Body();		
	}
	
	public UserRptRequest(String spNumber, String userNumber, long userCondition, String reserve) {
		header = new MessageHeader();
		
		Body b = new Body();
		b.setSpNumber(spNumber);
		b.setUserNumber(userNumber);
		b.setUserCondition(userCondition);
		b.setReserve(reserve);
		
		body = b;
	}
	
	public Body getBody() {
		return (Body)body;
	}	
	
}
