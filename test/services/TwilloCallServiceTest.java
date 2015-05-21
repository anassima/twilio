package services;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Account;
import models.Call;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TwilloCallServiceTest extends UnitTest {
    private static final String TEST_NUMBER = "123";
    private TwilioCallService callService;
    private CallFactory mockCallFactory;
    private com.twilio.sdk.resource.instance.Call mockCall;

    @Before
    public void setUp() throws TwilioRestException {
        TwilioRestClient mockClient = mock(TwilioRestClient.class);
        Account mockAccount = mock(Account.class);
        mockCallFactory = mock(CallFactory.class);
        mockCall = mock(com.twilio.sdk.resource.instance.Call.class);

        when(mockClient.getAccount()).thenReturn(mockAccount);
        when(mockAccount.getCallFactory()).thenReturn(mockCallFactory);
        when(mockCallFactory.create(anyMap())).thenReturn(mockCall);
        when(mockCall.getSid()).thenReturn("123");

        callService = new TwilioCallService(mockClient);
    }

    @Test
    public void shouldReturnCallObject() {
        assertThat(callService.makeCall(TEST_NUMBER), is(instanceOf(Call.class)));
    }

    @Test
    public void shouldReturnNullOnException() throws TwilioRestException {
        when(mockCallFactory.create(anyMap())).thenThrow(TwilioRestException.class);
        assertThat(callService.makeCall(TEST_NUMBER), is(nullValue()));
    }
}
