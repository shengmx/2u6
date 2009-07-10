package org.opensgip.message.util;

import static org.junit.Assert.assertArrayEquals;

import org.opensgip.message.factory.SequenceNumberFactory;

import junit.framework.TestCase;

public class BytesOperationTest extends TestCase {

	public void testAsUnsignedInt1Bytes() {
		byte[] expected = {0x01};
		
		byte[] bytes = BytesOperation.asUnsignedInt1Bytes((short)1);
		assertEquals(1, bytes.length);
		
		assertArrayEquals(expected, bytes);
	}	
	
	public void testAsUnsignedInt2Bytes() {
		byte[] expected = {0x00, 0x01};
		
		byte[] bytes = BytesOperation.asUnsignedInt2Bytes(1);
		assertEquals(2, bytes.length);
		
		assertArrayEquals(expected, bytes);
	}	
	
	public void testAsUnsignedInt4Bytes_1() {
		byte[] expected = {0x00, 0x00, 0x00, 0x01};
		
		byte[] bytes = BytesOperation.asUnsignedInt4Bytes(1);
		assertEquals(4, bytes.length);
		
		assertArrayEquals(expected, bytes);
	}
	
	public void testAsUnsignedInt4Bytes_2() {
		byte[] expected = {0xb4-0x100, 0x11, 0x22, 0xb2-0x100};
		
		byte[] bytes = BytesOperation.asUnsignedInt4Bytes(SequenceNumberFactory.SOURCE_NODE_ID);
		assertEquals(4, bytes.length);
		
		assertArrayEquals(expected, bytes);
	}	
}
