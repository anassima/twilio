package models;

import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;

public class OutgoingCallTest extends UnitTest {
    private static final String TEST_CID = "cid";
    private static final CallStatus TEST_STATUS = CallStatus.COMPLETED;
    private static final String TEST_TO = "123";
    private static final String TEST_FROM = "456";
    private OutgoingCall outgoingCall;

    @Before
    public void setUp() {
        outgoingCall = new OutgoingCall();

        outgoingCall.setCid(TEST_CID);
        outgoingCall.setStatus(TEST_STATUS);
        outgoingCall.setCallTo(TEST_TO);
        outgoingCall.setCallFrom(TEST_FROM);
    }

    @Test
    public void shouldReturnCallId() {
        assertThat(outgoingCall.getCid(), is(equalTo(TEST_CID)));
    }

    @Test
    public void shouldReturnStatus() {
        assertThat(outgoingCall.getStatus(), is(equalTo(TEST_STATUS)));
    }

    @Test
    public void shouldReturnCallTo() {
        assertThat(outgoingCall.getCallTo(), is(equalTo(TEST_TO)));
    }

    @Test
    public void shouldReturnCallFrom() {
        assertThat(outgoingCall.getCallFrom(), is(equalTo(TEST_FROM)));
    }
}
