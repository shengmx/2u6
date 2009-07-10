package org.opensgip.message;

import java.util.ArrayList;
import java.util.List;

public class SubmitRequest extends AbstractMessage {
	public static class Body extends AbstractMessageBody {
		String spNumber = "";
		String chargeNumber = "";
		long userCount = 1;
		List<String> userNumbers = new ArrayList<String>();
		String corpId = "";
		String serviceType = "";
		long feeType = 0;//TODO
		String feeValue = "0";
		String givenValue = "0";
		long agentFlag = 0;
		long morelatetoMTFlag = -1; //TODO
		long priority = 1;
		String expireTime = "";
		String scheduleTime = "";
		long reportFlag = -1; //TODO			
		long TP_pid = -1; //TODO
		long TP_udhi = -1; //TODO
		long messageCoding = 8; //TODO
		long messageType = 0;
		long messageLength = 0;
		String messageContent = "";
		String reserve = "";
	}
	
	public SubmitRequest() {
		header = new MessageHeader();
		body = new Body();		
	}
	
	public SubmitRequest(long a) {//TODO
		header = new MessageHeader();
		
		Body b = new Body();
		//TODO
		
		body = b;
	}
	
	public Body getBody() {
		return (Body)body;
	}	
}
