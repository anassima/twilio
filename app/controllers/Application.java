package controllers;

import factories.CallFactory;
import jobs.CallSpamJob;
import models.OutgoingCall;
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
        List<OutgoingCall> outgoingCalls = OutgoingCall.findAll();
        render(outgoingCalls);
    }

    public static void callNumber(String number) {
        OutgoingCall outgoingCall = (OutgoingCall) callService.makeCall(number, TIMEOUT_SECONDS);
        outgoingCall.save();
        render(outgoingCall);
    }

    public static void getCalls() {
        List<OutgoingCall> outgoingCalls = callService.getCalls();

        for (OutgoingCall outgoingCall : outgoingCalls) {
            outgoingCall.save();
        }

        render(outgoingCalls);
    }

    public static void startJob() {
        callSpamJob.now();
    }

    public static void stopJob() {
        callSpamJob.stop();
    }
}