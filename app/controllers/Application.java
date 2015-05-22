package controllers;

import factories.CallFactory;
import jobs.CallSpamJob;
import models.Call;
import play.mvc.*;
import services.CallService;

import java.util.List;

public class Application extends Controller {
    private static final String SERVICE_NAME_TWILIO = "twilio";
    private static final String TIMEOUT_SECONDS = "1";
    private static CallSpamJob callSpamJob = new CallSpamJob();
    private static CallService callService = CallFactory.getCallService(SERVICE_NAME_TWILIO);

    public Application(CallService callService, CallSpamJob callSpamJob) {
        this.callService = callService;
        this.callSpamJob = callSpamJob;
    }

    public static void index() {
        List<Call> calls = Call.findAll();
        render(calls);
    }

    public static void callNumber(String number) {
        Call call = (Call) callService.makeCall(number, TIMEOUT_SECONDS);
        call.save();
        render(call);
    }

    public static void getCalls() {
        List<Call> calls = callService.getCalls();
        render(calls);
    }

    public static void startJob() {
        callSpamJob.now();
    }

    public static void stopJob() {
        callSpamJob.stop();
    }
}