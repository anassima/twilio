import models.Call;
import models.CallStatus;
import org.junit.*;
import play.test.*;
import services.TwilioCallService;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class TwilioIntegrationTest extends UnitTest {
    private TwilioCallService callService;

    @Before
    public void setUp() {
        callService = new TwilioCallService();
    }

    @Test
    public void shouldPlaceSuccessfulOutgoingCall() {
        Call call = callService.makeCall("123");
        assertThat(call, is(notNullValue()));
    }
}