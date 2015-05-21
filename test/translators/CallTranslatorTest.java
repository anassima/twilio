package translators;

import models.Call;
import models.CallStatus;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallTranslatorTest extends UnitTest {
    private static final String TEST_ID = "id";
    private static final CallStatus TEST_STATUS_SUCCESS = CallStatus.SUCCESS;
    private static final String TEST_STATUS = "completed";
    private Call call;

    @Before
    public void setUp() {
        com.twilio.sdk.resource.instance.Call twilioCall = mock(com.twilio.sdk.resource.instance.Call.class);

        when(twilioCall.getSid()).thenReturn(TEST_ID);
        when(twilioCall.getStatus()).thenReturn(TEST_STATUS);

        call = CallTranslator.translate(twilioCall);
    }

    @Test
    public void shouldTranslateId() {
        assertThat(call.getId(), is(equalTo(TEST_ID)));
    }

    @Test
    public void shouldTranslateStatusCompleted() {
        assertThat(call.getStatus(), is(equalTo(TEST_STATUS_SUCCESS)));
    }
}
