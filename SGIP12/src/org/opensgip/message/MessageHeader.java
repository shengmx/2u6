package org.opensgip.message;
import static org.opensgip.message.util.BytesOperation.from12BytesToSequence;

import org.apache.mina.common.ByteBuffer;

public class MessageHeader {
	public static int HEADER_BYTES_SIZE = 20;
	
	public static class Sequence {
		private long nodeId;
		private long timestamp;
		private long count;
		
		public Sequence() {
			count = 0;
		}
		
		public Sequence(long nodeId, long timestamp, long count) {
			this.nodeId = nodeId;
			this.timestamp = timestamp;
			this.count = count;
		}
		
		public long getNodeId() {
			return nodeId;
		}
		public void setNodeId(long nodeId) {
			this.nodeId = nodeId;
		}
		public long getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}
		public long getCount() {
			return count;
		}
		public void setCount(long count) {
			this.count = count;
		}
	}
	
	private long messageLength = 0;   //4 bytes unsigned int
	private long commandId = 0;       //4 bytes unsigned int
	private Sequence sequenceNumber = new Sequence();  //12 bytes unsigned int
		
	public long getMessageLength() {
		return messageLength;
	}
	public void setMessageLength(long messageLength) {
		this.messageLength = messageLength;
	}
	public long getCommandId() {
		return commandId;
	}
	public void setCommandId(long commandId) {
		this.commandId = commandId;
	}
	public Sequence getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(Sequence sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	public static MessageHeader tryDecodeHeader(long expectedCommandId, ByteBuffer in) {
		long messageLength = in.getUnsignedInt();
		long commandId = in.getUnsignedInt();
		if (commandId!=expectedCommandId) {
			return null;
		}
		byte[] seq12Bytes = new byte[12];
		in.get(seq12Bytes);
		Sequence seq = from12BytesToSequence(seq12Bytes);
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setMessageLength(messageLength);
		messageHeader.setCommandId(commandId);
		messageHeader.setSequenceNumber(seq);
		
		return messageHeader;
	}	
}
