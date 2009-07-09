package org.opensgip.message;

import static org.opensgip.message.util.BytesOperation.from3BytesToBigInteger;

import java.math.BigInteger;

import org.apache.mina.common.ByteBuffer;

public class MessageHeader {
	private long messageLength = 0;   //4 bytes unsigned int
	private long commandId = 0;       //4 bytes unsigned int
	private BigInteger sequenceNumber = new BigInteger("0");  //12 bytes unsigned int
		
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
	public BigInteger getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(BigInteger sequenceNumber) {
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
		BigInteger seq = from3BytesToBigInteger(seq12Bytes);
		
		MessageHeader messageHeader = new MessageHeader();
		messageHeader.setMessageLength(messageLength);
		messageHeader.setCommandId(commandId);
		messageHeader.setSequenceNumber(seq);
		
		return messageHeader;
	}	
}
