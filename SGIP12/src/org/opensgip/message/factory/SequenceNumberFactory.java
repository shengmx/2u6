package org.opensgip.message.factory;

import static org.opensgip.message.util.BytesOperation.asUnsignedInt4Bytes;
import static org.opensgip.message.util.BytesOperation.from12BytesToSequence;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.opensgip.message.MessageHeader.Sequence;

public class SequenceNumberFactory {
	public static long SOURCE_NODE_ID = 3021021874L;
	
	public final static int MAX_COUNT = 1000000;	
	public static int count = 0;
	public static java.text.SimpleDateFormat formater = new SimpleDateFormat("MMddHHmmss");
	
	
	private static byte[] nextSequenceBytes() {
		ByteBuffer result = ByteBuffer.allocate(12);
		result.put(asUnsignedInt4Bytes(SOURCE_NODE_ID));

		Date now = new Date();
		Long timestamp = Long.parseLong(formater.format(now));
		result.put(asUnsignedInt4Bytes(timestamp.longValue()));
		
		result.put(asUnsignedInt4Bytes(count++));
		if (count>=MAX_COUNT) count = 0;
		
		return result.array();		
	}
	
	synchronized public static Sequence nextSequenceNumber() {
		byte[] bytes = nextSequenceBytes();
		return from12BytesToSequence(bytes);		
	}
}
