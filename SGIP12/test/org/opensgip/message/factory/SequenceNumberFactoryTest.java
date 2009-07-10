package org.opensgip.message.factory;

import junit.framework.TestCase;

import org.opensgip.message.MessageHeader.Sequence;

public class SequenceNumberFactoryTest extends TestCase{
	public void testNextSequenceNumber() {
		Sequence seq = SequenceNumberFactory.nextSequenceNumber();
		assertNotNull(seq);
		
		assertEquals(SequenceNumberFactory.SOURCE_NODE_ID, seq.getNodeId());			
	}
}
