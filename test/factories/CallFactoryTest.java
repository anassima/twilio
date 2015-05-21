package factories;

import org.junit.Test;
import play.test.UnitTest;
import services.TwilioCallService;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;

public class CallFactoryTest extends UnitTest {
    private static final String NAME_TWILIO = "twilio";
    private static final String NAME_INVALID = "invalid";

    @Test
    public void shouldReturnNullIfNamedServiceNotFound() {
        assertThat(CallFactory.getCallService(NAME_INVALID), is(nullValue()));
    }

    @Test
    public void shouldGetTwilioCallService() {
        assertThat(CallFactory.getCallService(NAME_TWILIO), is(instanceOf(TwilioCallService.class)));
    }
}
