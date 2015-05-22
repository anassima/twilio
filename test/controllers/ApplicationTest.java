package controllers;

import jobs.CallSpamJob;
import org.junit.Before;
import org.junit.Test;
import services.CallService;

import static org.mockito.Mockito.mock;

public class ApplicationTest {
    private Application application;
    private CallService callService;
    private CallSpamJob callSpamJob;

    @Before
    public void setUp() {
        callService = mock(CallService.class);
        callSpamJob = mock(CallSpamJob.class);

        application = new Application(callService, callSpamJob);
    }

    @Test
    public void shouldRenderIndex() {
        // TODO Not yet implemented
    }

    @Test
    public void shouldReturnListOfCallsOnIndex() {
        // TODO Not yet implemented
    }

    @Test
    public void shouldCallNumber() {
        // TODO Not yet implemented
    }

    @Test
    public void shouldSaveCalledNumber() {
        // TODO Not yet implemented
    }

    @Test
    public void shouldGetCalls() {
        // TODO Not yet implemented
    }

    @Test
    public void shouldStartJob() {
        // TODO Not yet implemented
    }

    @Test
    public void shouldStopJob() {
        // TODO Not yet implemented
    }
}
