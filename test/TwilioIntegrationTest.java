import factories.CallFactory;
import models.Call;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;
import services.CallService;

import static org.hamcrest.CoreMatchers.instanceOf;
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
        Call call = callService.makeCall("9173799794");
        assertThat(call, is(instanceOf(Call.class)));
    }
}