package models;

import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;

public class PlacedOutgoingCallTest extends UnitTest {
    private static final Date TEST_CREATED = new Date();
    private static final Date TEST_UPDATED = new Date(new Date().getTime() + 1);
    private static final Date TEST_START = new Date(new Date().getTime() + 2);
    private static final Date TEST_END = new Date(new Date().getTime() + 3);
    private static final int TEST_DURATION = new Integer(10);
    private PlacedOutgoingCall placedCall;

    @Before
    public void setUp() {
        placedCall = new PlacedOutgoingCall();

        placedCall.setCreated(TEST_CREATED);
        placedCall.setUpdated(TEST_UPDATED);
        placedCall.setStart(TEST_START);
        placedCall.setEnd(TEST_END);
        placedCall.setDuration(TEST_DURATION);
    }

    @Test
    public void shouldReturnCreated() {
        assertThat(placedCall.getCreated(), is(equalTo(TEST_CREATED)));
    }

    @Test
    public void shouldReturnUpdated() {
        assertThat(placedCall.getUpdated(), is(equalTo(TEST_UPDATED)));
    }

    @Test
    public void shouldReturnStart() {
        assertThat(placedCall.getStart(), is(equalTo(TEST_START)));
    }

    @Test
    public void shouldReturnEnd() {
        assertThat(placedCall.getEnd(), is(equalTo(TEST_END)));
    }

    @Test
    public void shouldReturnDuration() {
        assertThat(placedCall.getDuration(), is(equalTo(TEST_DURATION)));
    }
}
