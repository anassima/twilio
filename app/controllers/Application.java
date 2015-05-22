package controllers;

import factories.CallFactory;
import jobs.CallSpamJob;
import models.Call;
import play.mvc.*;
import services.CallService;

import java.util.List;

public class Application extends Controller {
    private static final String SERVICE_NAME_TWILIO = "twilio";
    private static final CallSpamJob spamJob = new CallSpamJob();;
    private static final String TIMEOUT_SECONDS = "1";

    public static void index() {
        List<Call> calls = Call.findAll();
        render(calls);
    }

    public static void callNumber(String number) {
        CallService callService = CallFactory.getCallService(SERVICE_NAME_TWILIO);
        Call call = (Call) callService.makeCall(number, TIMEOUT_SECONDS);
        call.save();
        render(call);
    }

    public static void startJob() {
        spamJob.now();
    }

    public static void stopJob() {
        spamJob.stop();
    }
}