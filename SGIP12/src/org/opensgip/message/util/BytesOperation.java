package org.opensgip.message.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.apache.mina.common.ByteBuffer;


public class BytesOperation {
	public static BigInteger ZERO = new BigInteger("0");
	
	public static byte[] asUnsignedInt1Bytes(short value) {
		byte[] result = new byte[1];
		ByteBuffer bb = ByteBuffer.allocate(2);
		bb.putShort(value);
		bb.get(result, 1, 1);
		return result;
	}
	
	public static byte[] asUnsignedInt2Bytes(int value) {
		byte[] result = new byte[2];
		ByteBuffer bb = ByteBuffer.allocate(4);
		bb.putInt(value);
		bb.get(result, 2, 2);
		return result;
	}	
	
	public static byte[] asUnsignedInt4Bytes(long value) {
		byte[] result = new byte[4];
		ByteBuffer bb = ByteBuffer.allocate(8);
		bb.putLong(value);
		bb.get(result, 4, 4);
		return result;
	}
	
	public static byte[] asUnsignedBigInteger12Bytes(BigInteger bint) {
		if (bint.compareTo(ZERO)<0) throw new IllegalArgumentException("Negative Input " + bint + " is not allowd. ");
		
		byte[] result = new byte[12];
		for(int i=0;i<result.length;i++) result[i] = 0;
		
		byte[] ba = bint.toByteArray();
		System.arraycopy(ba, 0, result, result.length - ba.length, ba.length);
				
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
	
	public static BigInteger from3BytesToBigInteger(byte[] bytes) {
		return new BigInteger(bytes);
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
