package models;

import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;

public class CallTest extends UnitTest {
    private static final String TEST_ID = "id";
    private static final CallStatus TEST_STATUS = CallStatus.SUCCESS;
    private static final String TEST_TO = "123";
    private static final String TEST_FROM = "456";
    private Call call;

    @Before
    public void setUp() {
        call = new Call();

        call.setId(TEST_ID);
        call.setStatus(TEST_STATUS);
        call.setTo(TEST_TO);
        call.setFrom(TEST_FROM);
    }

    @Test
    public void shouldReturnId() {
        assertThat(call.getId(), is(equalTo(TEST_ID)));
    }

    @Test
    public void shouldReturnStatus() {
        assertThat(call.getStatus(), is(equalTo(TEST_STATUS)));
    }

    @Test
    public void shouldReturnTo() {
        assertThat(call.getTo(), is(equalTo(TEST_TO)));
    }

    @Test
    public void shouldReturnFrom() {
        assertThat(call.getFrom(), is(equalTo(TEST_FROM)));
    }
}
