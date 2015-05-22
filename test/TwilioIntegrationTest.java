import factories.CallFactory;
import models.OutgoingCall;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;
import services.CallService;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;

public class TwilioIntegrationTest extends UnitTest {
    private static final String SERVICE_NAME_TWILIO = "twilio";
    private CallService callService;

    @Before
    public void setUp() {
        callService = CallFactory.getCallService(SERVICE_NAME_TWILIO);
    }

    @Test
    public void shouldMakeValidCall() {
        OutgoingCall outgoingCall = (OutgoingCall) callService.makeCall("9173799794", "1");
        assertThat(outgoingCall, is(instanceOf(OutgoingCall.class)));
    }

    @Test
    public void shouldGetAllCalls() {
        List<OutgoingCall> outgoingCalls = callService.getCalls();
        assertThat(outgoingCalls.size(), is(greaterThan(0))); // Assumption we have live data
    }
}