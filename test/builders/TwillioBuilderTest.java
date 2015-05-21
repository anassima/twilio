package builders;

import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;

public class TwillioBuilderTest extends UnitTest {
    private static final String TEST_TO = "to";
    private static final String TEST_FROM = "from";
    private static final String TEST_URL = "url";
    private static final String TEST_METHOD = "GET";
    private static final String TEST_RECORD = "false";
    private Map<String, String> params;

    @Before
    public void setUp() {
        params = TwilioBuilder.buildOutgoingCallParams(TEST_TO, TEST_FROM, TEST_URL);
    }

    @Test
    public void shouldContainToKey() {
        assertThat(params.get("To"), is(equalTo(TEST_TO)));
    }

    @Test
    public void shouldContainFromKey() {
        assertThat(params.get("From"), is(equalTo(TEST_FROM)));
    }

    @Test
    public void shouldContainUrlKey() {
        assertThat(params.get("Url"), is(equalTo(TEST_URL)));
    }

    @Test
    public void shouldContainMethodKey() {
        assertThat(params.get("Method"), is(equalTo(TEST_METHOD)));
    }

    @Test
    public void shouldContainFallbackMethodKey() {
        assertThat(params.get("FallbackMethod"), is(equalTo(TEST_METHOD)));
    }

    @Test
    public void shouldContainStatusCallbackMethodKey() {
        assertThat(params.get("StatusCallbackMethod"), is(equalTo(TEST_METHOD)));
    }

    @Test
    public void shouldContainRecordKey() {
        assertThat(params.get("Record"), is(equalTo(TEST_RECORD)));
    }
}
