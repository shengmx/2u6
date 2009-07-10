package org.opensgip.message;

import static org.opensgip.message.util.BytesOperation.asFixedLengthISO88591Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedInt1Bytes;
import static org.opensgip.message.util.BytesOperation.asUnsignedInt4Bytes;

import junit.framework.TestCase;
import org.apache.mina.common.ByteBuffer;
import org.opensgip.message.factory.SequenceNumberFactory;
import org.opensgip.testing.mock.MockedProtocolDecoderOutput;

public class BindResponseTest extends TestCase {

	public void testDecode() {
		int size = MessageHeader.HEADER_BYTES_SIZE + BindResponse.Body.BODY_BYTES_SIZE;
		java.nio.ByteBuffer bf = java.nio.ByteBuffer.allocate(size);			
				
		bf.put(asUnsignedInt4Bytes(MessageHeader.HEADER_BYTES_SIZE + BindResponse.Body.BODY_BYTES_SIZE)); //msg length
		bf.put(asUnsignedInt4Bytes(BindResponse.SGIP_BIND_RESP));										  //cmd id
		bf.put(asUnsignedInt4Bytes(SequenceNumberFactory.SOURCE_NODE_ID));								  //seq-nodeid
		bf.put(asUnsignedInt4Bytes(07101334));															  //seq-timestamp
		bf.put(asUnsignedInt4Bytes(1));																	  //seq-count
		bf.put(asUnsignedInt1Bytes((short)0));															  //result
		bf.put(asFixedLengthISO88591Bytes("rv", 8));													  //revert
		bf.rewind();
		
		assertEquals(size, bf.remaining());
		
		ByteBuffer in = ByteBuffer.allocate(size);
		in.put(bf);
		in.flip();
		
		MockedProtocolDecoderOutput out = new MockedProtocolDecoderOutput();		
		BindResponse.Decoder decoder = new BindResponse.Decoder();
		try {
			decoder.doDecode(null, in, out);
		} catch (Exception e) {
			fail();
		}
		
		BindResponse bindResponse = (BindResponse)out.getObject();
		assertEquals(size, 
				bindResponse.getHeader().getMessageLength());
		assertEquals(BindResponse.SGIP_BIND_RESP, 
				bindResponse.getHeader().getCommandId());
		assertEquals(SequenceNumberFactory.SOURCE_NODE_ID, 
				bindResponse.getHeader().getSequenceNumber().getNodeId());
		assertEquals(07101334L, 
				bindResponse.getHeader().getSequenceNumber().getTimestamp());
		assertEquals(1, 
				bindResponse.getHeader().getSequenceNumber().getCount());
		
		assertEquals(0, bindResponse.getBody().getResult());
		assertEquals("rv", bindResponse.getBody().getReserve().trim());
		
	}

}
