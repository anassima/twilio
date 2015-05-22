package translators;

import models.Call;
import models.CallStatus;
import models.PlacedCall;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Date;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallTranslatorTest extends UnitTest {
    private static final String TEST_CID = "cid";
    private static final CallStatus TEST_STATUS_SUCCESS = CallStatus.SUCCESS;
    private static final String TEST_STATUS = "completed";
    private static final String TEST_TO = "to";
    private static final String TEST_FROM = "from";
    private static final Date TEST_CREATED = new Date();
    private static final Date TEST_UPDATED = new Date(new Date().getTime() + 1);
    private static final String TEST_START = "Thu, 21 May 2015 22:30:00 +0000";
    private static final String TEST_END = "Thu, 21 May 2015 22:40:00 +0000";
    private static final String TEST_DURATION = "10";
    private static DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z");
    private Call call;
    private com.twilio.sdk.resource.instance.Call twilioCall;

    @Before
    public void setUp() {
        twilioCall = mock(com.twilio.sdk.resource.instance.Call.class);

        when(twilioCall.getSid()).thenReturn(TEST_CID);
        when(twilioCall.getStatus()).thenReturn(TEST_STATUS);
        when(twilioCall.getTo()).thenReturn(TEST_TO);
        when(twilioCall.getFrom()).thenReturn(TEST_FROM);

        call = CallTranslator.translate(twilioCall);
    }

    @Test
    public void shouldTranslateCid() {
        assertThat(call.getCid(), is(equalTo(TEST_CID)));
    }

    @Test
    public void shouldTranslateStatusCompleted() {
        assertThat(call.getStatus(), is(equalTo(TEST_STATUS_SUCCESS)));
    }

    @Test
    public void shouldTranslateTo() {
        assertThat(call.getTo(), is(equalTo(TEST_TO)));
    }

    @Test
    public void shouldTranslateFrom() {
        assertThat(call.getFrom(), is(equalTo(TEST_FROM)));
    }

    @Test
    public void shouldTranslatePlacedCallCreated() {
        PlacedCall placedCall = placedCallSetUp();
        assertThat(placedCall.getCreated(), is(equalTo(new DateTime(TEST_CREATED))));
    }

    @Test
    public void shouldTranslatePlacedCallUpdated() {
        PlacedCall placedCall = placedCallSetUp();
        assertThat(placedCall.getUpdated(), is(equalTo(new DateTime(TEST_UPDATED))));
    }

    @Test
    public void shouldTranslatePlacedCallStart() {
        PlacedCall placedCall = placedCallSetUp();
        assertThat(placedCall.getStart(), is(equalTo(DATE_FORMAT.parseDateTime(TEST_START))));
    }

    @Test
    public void shouldTranslatePlacedCallEnd() {
        PlacedCall placedCall = placedCallSetUp();
        assertThat(placedCall.getEnd(), is(equalTo(DATE_FORMAT.parseDateTime(TEST_END))));
    }

    @Test
    public void shouldTranslatePlacedCallDuration() {
        PlacedCall placedCall = placedCallSetUp();
        assertThat(placedCall.getDuration(), is(equalTo(Integer.parseInt(TEST_DURATION))));
    }

    private PlacedCall placedCallSetUp() {
        when(twilioCall.getDateCreated()).thenReturn(TEST_CREATED);
        when(twilioCall.getDateUpdated()).thenReturn(TEST_UPDATED);
        when(twilioCall.getStartTime()).thenReturn(TEST_START);
        when(twilioCall.getEndTime()).thenReturn(TEST_END);
        when(twilioCall.getDuration()).thenReturn(TEST_DURATION);
        return CallTranslator.translate(twilioCall);
    }
}
