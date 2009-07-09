package org.opensgip.message;

public class SimpleResponseBody extends AbstractMessageBody {
	short result = -1;
	String reserve = "";	
	
	public SimpleResponseBody() {		
	}
	
	public SimpleResponseBody(short result, String reserve) {
		this.result = result;
		this.reserve = reserve;
	}
	
	public short getResult() {
		return result;
	}
	public void setResult(short result) {
		this.result = result;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}	
}
