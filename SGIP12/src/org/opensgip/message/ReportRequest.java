package org.opensgip.message;

public class ReportRequest extends AbstractMessage {
	public static class Body extends AbstractMessageBody {
		long submitSequenceNumber = 0;
		long reportType = 0;
		String userNumber = "";
		long state = 0;
		long errorCode = 0;
		String reserve = "";		
		
		public long getSubmitSequenceNumber() {
			return submitSequenceNumber;
		}
		public void setSubmitSequenceNumber(long submitSequenceNumber) {
			this.submitSequenceNumber = submitSequenceNumber;
		}
		public long getReportType() {
			return reportType;
		}
		public void setReportType(long reportType) {
			this.reportType = reportType;
		}
		public String getUserNumber() {
			return userNumber;
		}
		public void setUserNumber(String userNumber) {
			this.userNumber = userNumber;
		}
		public long getState() {
			return state;
		}
		public void setState(long state) {
			this.state = state;
		}
		public long getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(long errorCode) {
			this.errorCode = errorCode;
		}
		public String getReserve() {
			return reserve;
		}
		public void setReserve(String reserve) {
			this.reserve = reserve;
		}
	}
	
	public ReportRequest() {
		header = new MessageHeader();
		body = new Body();		
	}
	
	public ReportRequest(long submitSequenceNumber, long reportType, String userNumber, long state, long errorCode, String reserve) {
		header = new MessageHeader();
		
		Body b = new Body();
		b.setSubmitSequenceNumber(submitSequenceNumber);
		b.setReportType(reportType);
		b.setUserNumber(userNumber);
		b.setState(state);
		b.setErrorCode(errorCode);
		b.setReserve(reserve);

		body = b;
	}
	
	public Body getBody() {
		return (Body)body;
	}	
	
}
