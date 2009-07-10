package org.opensgip.message;

import static org.junit.Assert.assertArrayEquals;
import static org.opensgip.message.util.BytesOperation.asFixedLengthISO88591Bytes;
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
		bindRequest.getBody().setLoginType((short)1);
		bindRequest.getBody().setLoginName("abc");
		bindRequest.getBody().setLoginPassword("123");		
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
		assertEquals(BindRequest.SGIP_BIND, bf.getUnsignedInt()); 						//type
		assertEquals(SequenceNumberFactory.SOURCE_NODE_ID, bf.getUnsignedInt()); 		//sequence-nodeid
		assertEquals(0710110245L,bf.getUnsignedInt()); 									//timestamp
		assertEquals(1,bf.getUnsignedInt()); 											//count
		
		assertEquals((short)1, bf.getUnsigned());                        				//login type
		
		byte[] actualNameBytes = new byte[16];
		bf.get(actualNameBytes);		
		byte[] expectedNameBytes = asFixedLengthISO88591Bytes("abc", 16);
		assertArrayEquals(expectedNameBytes, actualNameBytes);                 			//login username
		
		byte[] actualPasswordBytes = new byte[16];
		bf.get(actualPasswordBytes);		
		byte[] expectedPasswordBytes = asFixedLengthISO88591Bytes("123", 16);
		assertArrayEquals(expectedPasswordBytes, actualPasswordBytes);                 	//login password		
		
		
		byte[] actualReserveBytes = new byte[8];
		bf.get(actualReserveBytes);		
		byte[] expectedReserveBytes = asFixedLengthISO88591Bytes("", 8);
		assertArrayEquals(expectedReserveBytes, actualReserveBytes);                 	//reserve		
				
	}

}
