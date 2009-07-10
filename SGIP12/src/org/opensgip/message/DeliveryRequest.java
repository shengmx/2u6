package org.opensgip.message;

public class DeliveryRequest extends AbstractMessage {
	public static class Body extends AbstractMessageBody {
		public String getUserNumber() {
			return userNumber;
		}
		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}
		public String getSpNumber() {
			return spNumber;
		}
		public void setSpNumber(String spNumber) {
			this.spNumber = spNumber;
		}
		public long getTpPid() {
			return tpPid;
		}
		public void setTpPid(long tpPid) {
			this.tpPid = tpPid;
		}
		public long getTpUdhi() {
			return tpUdhi;
		}
		public void setTpUdhi(long tpUdhi) {
			this.tpUdhi = tpUdhi;
		}
		public long getMessageCoding() {
			return messageCoding;
		}
		public void setMessageCoding(long messageCoding) {
			this.messageCoding = messageCoding;
		}
		public long getMessageLength() {
			return messageLength;
		}
		public void setMessageLength(long messageLength) {
			this.messageLength = messageLength;
		}
		public String getMessageContent() {
			return messageContent;
		}
		public void setMessageContent(String messageContent) {
			this.messageContent = messageContent;
		}
		public String getReserve() {
			return reserve;
		}
		public void setReserve(String reserve) {
			this.reserve = reserve;
		}
		String userNumber = "";
		String spNumber = "";
		long tpPid = -1; //TODO
		long tpUdhi = -1; //TODO
		long messageCoding = 8; 
		long messageLength = 0;
		String messageContent = "";
		String reserve = "";
	}
	
	public DeliveryRequest() {
		header = new MessageHeader();
		body = new Body();		
	}
	
	public DeliveryRequest(String userNumber,String spNumber, long messageCoding, long messageLength, String messageContent) {
		header = new MessageHeader();
		
		Body b = new Body();
		b.setUserNumber(userNumber);
		b.setSpNumber(spNumber);
		b.setMessageCoding(messageCoding);
		b.setMessageLength(messageLength);
		b.setMessageContent(messageContent);
		
		body = b;
	}
	
	public Body getBody() {
		return (Body)body;
	}	
}
