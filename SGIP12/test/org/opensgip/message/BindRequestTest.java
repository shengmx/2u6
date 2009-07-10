package org.opensgip.message;

import junit.framework.TestCase;

import org.apache.mina.common.ByteBuffer;
import org.opensgip.message.MessageHeader.Sequence;
import org.opensgip.message.factory.SequenceNumberFactory;
import org.opensgip.testing.mock.MockedProtocolEncoderOutput;

public class BindRequestTest extends TestCase {

	public void testEncode() {
		Sequence sequence = new Sequence(SequenceNumberFactory.SOURCE_NODE_ID, 0710110245L, 1);
		BindRequest bindRequest = new BindRequest();
		bindRequest.getHeader().setSequenceNumber(sequence);
		bindRequest.getBody().setLoginName("abc");
		bindRequest.getBody().setLoginPassword("123");
		bindRequest.getBody().setLoginType((short)1);
		bindRequest.getBody().setReserve("");
		
		MockedProtocolEncoderOutput out = new MockedProtocolEncoderOutput();
		
		BindRequest.Encoder encoder = new BindRequest.Encoder();
		
		try {
			encoder.encode(null, bindRequest, out);
		} catch (Exception e) {			
			fail();
		}
		
		ByteBuffer bf = out.getOutput();
		assertEquals(MessageHeader.HEADER_BYTES_SIZE+BindRequest.Body.BODY_BYTES_SIZE, bf.getUnsignedInt()); //size
		assertEquals(BindRequest.SGIP_BIND, bf.getUnsignedInt()); //type
		assertEquals(SequenceNumberFactory.SOURCE_NODE_ID, bf.getUnsignedInt()); //sequence-nodeid
	}

}
