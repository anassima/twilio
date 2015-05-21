package models;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;

public class PlacedCallTest extends UnitTest {
    private static final DateTime TEST_CREATED = new DateTime();
    private static final DateTime TEST_UPDATED = new DateTime().plusDays(1);
    private static final DateTime TEST_START = new DateTime().plusDays(2);
    private static final DateTime TEST_END = new DateTime().plusDays(3);
    private static final int TEST_DURATION = 10;
    private PlacedCall placedCall;

    @Before
    public void setUp() {
        placedCall = new PlacedCall();

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
