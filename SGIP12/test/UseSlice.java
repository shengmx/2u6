import java.nio.ByteBuffer;

import junit.framework.TestCase;


public class UseSlice extends TestCase {
	public void testSlice() throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(10);
		buffer.put((byte)0x31);
		buffer.put((byte)0x32);
		buffer.put((byte)0x33);
		buffer.put((byte)0x34);
		buffer.put((byte)0x35);
		buffer.put((byte)0x36);
		buffer.put((byte)0x37);
		buffer.put((byte)0x38);
		buffer.put((byte)0x39);
		buffer.put((byte)0x30);
		
		buffer.position(2);
		buffer.limit(8);
		
		ByteBuffer newBuffer = buffer.slice();
		
		assertTrue(newBuffer.hasRemaining());
		assertEquals(0x33,newBuffer.get());	
		
		assertTrue(newBuffer.hasRemaining());
		assertEquals(0x34,newBuffer.get());	
		
		assertTrue(newBuffer.hasRemaining());
		assertEquals(0x35,newBuffer.get());	
		
		assertTrue(newBuffer.hasRemaining());
		assertEquals(0x36,newBuffer.get());	
		
		assertTrue(newBuffer.hasRemaining());
		assertEquals(0x37,newBuffer.get());	
		
		assertTrue(newBuffer.hasRemaining());
		assertEquals(0x38,newBuffer.get());	
		
		assertFalse(newBuffer.hasRemaining());			
		
		
		
		
		
	}
}
