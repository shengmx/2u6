package org.opensgip.message;

public class TraceResponse extends AbstractMessage {
	public static class Body extends AbstractMessageBody {
		long count = 0;
		long result = 0;
		String nodeId = "";
		String receiveTime = "";
		String sendTime = "";
		String reserve = "";
		
		public long getCount() {
			return count;
		}
		public void setCount(long count) {
			this.count = count;
		}
		public long getResult() {
			return result;
		}
		public void setResult(long result) {
			this.result = result;
		}
		public String getNodeId() {
			return nodeId;
		}
		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}
		public String getReceiveTime() {
			return receiveTime;
		}
		public void setReceiveTime(String receiveTime) {
			this.receiveTime = receiveTime;
		}
		public String getSendTime() {
			return sendTime;
		}
		public void setSendTime(String sendTime) {
			this.sendTime = sendTime;
		}
		public String getReserve() {
			return reserve;
		}
		public void setReserve(String reserve) {
			this.reserve = reserve;
		}
	}
	
	public TraceResponse() {
		header = new MessageHeader();
		body = new Body();		
	}
	
	public TraceResponse(long count, long result, String nodeId, String receiveTime, String sendTime, String reserve) {
		header = new MessageHeader();
		
		Body b = new Body();
		b.setCount(count);
		b.setResult(result);
		b.setNodeId(nodeId);
		b.setReceiveTime(receiveTime);
		b.setSendTime(sendTime);
		b.setReserve(reserve);
		
		body = b;
	}
	
	public Body getBody() {
		return (Body)body;
	}	
}
