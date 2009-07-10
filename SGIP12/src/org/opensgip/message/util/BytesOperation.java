package org.opensgip.message.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.apache.mina.common.ByteBuffer;
import org.opensgip.message.MessageHeader.Sequence;


public class BytesOperation {
	public static BigInteger ZERO = new BigInteger("0");
	
	public static byte[] asUnsignedInt1Bytes(short value) {
		byte[] result = new byte[1];
		ByteBuffer bb = ByteBuffer.allocate(2);
		bb.putShort(value);
		bb.position(1);
		bb.get(result);
		return result;
	}
	
	public static byte[] asUnsignedInt2Bytes(int value) {
		byte[] result = new byte[2];
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.putInt(value);
		bb.position(2);
		bb.get(result);
		return result;
	}	
	
	public static byte[] asUnsignedInt4Bytes(long value) {
		byte[] result = new byte[4];
		ByteBuffer bb = ByteBuffer.allocate(8);
		bb.putLong(value);
		bb.position(4);
		bb.get(result);
		return result;
	}
	
	public static byte[] asSequence12Bytes(Sequence seq) {		
		ByteBuffer bf = ByteBuffer.allocate(12);
		
		bf.put(asUnsignedInt4Bytes(seq.getNodeId()));
		bf.put(asUnsignedInt4Bytes(seq.getTimestamp()));
		bf.put(asUnsignedInt4Bytes(seq.getCount()));		
		
		byte[] result = new byte[12];
		
		bf.rewind();
		bf.get(result);
		
		return result;
	}	
	
	/**
	 * Encoding content into ISO-8859-1 encoded string. If content length is less than input 
	 * argument, byte \0 is padded. If content length is more than input argument, only length size 
	 * content keeps remained.
	 * 
	 * @param content
	 * @param length
	 * @return
	 */
	public static byte[] asFixedLengthISO88591Bytes(String content, int length) {
		String str = content;
		if (content.length()>length) {
			str = content.substring(0, length);
		}
		
		byte[] result = new byte[length];
		for(int i=0;i<result.length;i++) {
			result[i] = 0;
		}
		try {
			byte[] cbytes = str.getBytes("ISO-8859-1");
			System.arraycopy(cbytes, 0, result, 0, cbytes.length);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
		return result;
	}
	
	public static Sequence from12BytesToSequence(byte[] bytes) {	
		if (bytes.length!=12) throw new  IllegalArgumentException("input bytes length must be 12");
		
		Sequence seq = new Sequence();
		
		ByteBuffer bf = ByteBuffer.allocate(bytes.length);		
		bf.put(bytes);
		
		bf.rewind();
		long nodeId = bf.getUnsignedInt();
		long timestamp = bf.getUnsignedInt();
		long count = bf.getUnsignedInt();
		seq.setNodeId(nodeId);
		seq.setTimestamp(timestamp);
		seq.setCount(count);
		
		return seq;
	}
	
	
	public static String fromFixedLengthISO88591Bytes(ByteBuffer in, int length) throws Exception {
		byte[] lengthBytes = new byte[length];
		in.get(lengthBytes);
		String result = "";
		try {
			result = new String(lengthBytes, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			throw new Exception("UnsupportedEncoding for bytes.");
		}
		return result;
	}
	

}
