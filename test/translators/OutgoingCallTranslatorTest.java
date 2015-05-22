package translators;

import com.twilio.sdk.resource.instance.Call;
import models.OutgoingCall;
import models.CallStatus;
import models.PlacedOutgoingCall;
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

public class OutgoingCallTranslatorTest extends UnitTest {
    private static final String TEST_CID = "cid";
    private static final CallStatus TEST_STATUS_COMPLETED = CallStatus.COMPLETED;
    private static final String TEST_STATUS = "completed";
    private static final String TEST_TO = "to";
    private static final String TEST_FROM = "from";
    private static final Date TEST_CREATED = new Date();
    private static final Date TEST_UPDATED = new Date(new Date().getTime() + 1);
    private static final String TEST_START = "Thu, 21 May 2015 22:30:00 +0000";
    private static final String TEST_END = "Thu, 21 May 2015 22:40:00 +0000";
    private static final String TEST_DURATION = "10";
    private static DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z");
    private OutgoingCall outgoingCall;
    private Call twilioCall;

    @Before
    public void setUp() {
        twilioCall = mock(Call.class);

        when(twilioCall.getSid()).thenReturn(TEST_CID);
        when(twilioCall.getStatus()).thenReturn(TEST_STATUS);
        when(twilioCall.getTo()).thenReturn(TEST_TO);
        when(twilioCall.getFrom()).thenReturn(TEST_FROM);

        outgoingCall = CallTranslator.translate(twilioCall);
    }

    @Test
    public void shouldTranslateCid() {
        assertThat(outgoingCall.getCid(), is(equalTo(TEST_CID)));
    }

    @Test
    public void shouldTranslateStatusCompleted() {
        assertThat(outgoingCall.getStatus(), is(equalTo(TEST_STATUS_COMPLETED)));
    }

    @Test
    public void shouldTranslateTo() {
        assertThat(outgoingCall.getCallTo(), is(equalTo(TEST_TO)));
    }

    @Test
    public void shouldTranslateFrom() {
        assertThat(outgoingCall.getCallFrom(), is(equalTo(TEST_FROM)));
    }

    @Test
    public void shouldTranslatePlacedCallCreated() {
        PlacedOutgoingCall placedCall = placedCallSetUp();
        assertThat(placedCall.getCreated(), is(equalTo(new DateTime(TEST_CREATED))));
    }

    @Test
    public void shouldTranslatePlacedCallUpdated() {
        PlacedOutgoingCall placedCall = placedCallSetUp();
        assertThat(placedCall.getUpdated(), is(equalTo(new DateTime(TEST_UPDATED))));
    }

    @Test
    public void shouldTranslatePlacedCallStart() {
        PlacedOutgoingCall placedCall = placedCallSetUp();
        assertThat(placedCall.getStart(), is(equalTo(DATE_FORMAT.parseDateTime(TEST_START))));
    }

    @Test
    public void shouldTranslatePlacedCallEnd() {
        PlacedOutgoingCall placedCall = placedCallSetUp();
        assertThat(placedCall.getEnd(), is(equalTo(DATE_FORMAT.parseDateTime(TEST_END))));
    }

    @Test
    public void shouldTranslatePlacedCallDuration() {
        PlacedOutgoingCall placedCall = placedCallSetUp();
        assertThat(placedCall.getDuration().intValue(), is(equalTo(Integer.parseInt(TEST_DURATION))));
    }

    private PlacedOutgoingCall placedCallSetUp() {
        when(twilioCall.getDateCreated()).thenReturn(TEST_CREATED);
        when(twilioCall.getDateUpdated()).thenReturn(TEST_UPDATED);
        when(twilioCall.getStartTime()).thenReturn(TEST_START);
        when(twilioCall.getEndTime()).thenReturn(TEST_END);
        when(twilioCall.getDuration()).thenReturn(TEST_DURATION);
        return CallTranslator.translate(twilioCall);
    }
}
