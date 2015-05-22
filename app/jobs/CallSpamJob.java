package jobs;

import factories.CallFactory;
import models.Call;
import play.Logger;
import play.jobs.Job;
import services.CallService;

public class CallSpamJob extends Job {
    private static final String SERVICE_NAME_TWILIO = "twilio";
    private static final int MAX_NUM_ITERATIONS = 1000;
    private static final int MAX_SLEEP_MILLISECONDS = 30000;
    private static final String[] TEST_NUMBERS = {"9173799794", "9178414441"};
    private static final String TIMEOUT_SECONDS = "1";
    private boolean isRunning;

    public void doJob() throws InterruptedException {
        Logger.info("Starting job...");
        isRunning = true;
        int i = 0;

        while (isRunning && i < MAX_NUM_ITERATIONS) {
            Thread.sleep(getRandomNumber(MAX_SLEEP_MILLISECONDS));
            String number = TEST_NUMBERS[getRandomNumber(2)];
            Logger.info("Calling number: " + number);

            callNumber(number);

            i++;
        }
    }

    private void callNumber(String number) {
        CallService callService = CallFactory.getCallService(SERVICE_NAME_TWILIO);
        Call call = (Call) callService.makeCall(number, TIMEOUT_SECONDS);
        call.save();
    }

    public void stop() {
        Logger.info("Stopping job...");
        isRunning = false;
    }

    private int getRandomNumber(int ceiling) {
        return (int)(Math.random() * ceiling);
    }
}