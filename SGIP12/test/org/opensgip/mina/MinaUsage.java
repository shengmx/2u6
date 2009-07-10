package org.opensgip.mina;


import junit.framework.TestCase;

import java.nio.ByteBuffer;

public class MinaUsage extends TestCase {
	public void testAllocateSize() throws Exception {
		ByteBuffer bf = ByteBuffer.allocate(10);
		bf.rewind();
		assertEquals(10, bf.remaining());
	}
}
