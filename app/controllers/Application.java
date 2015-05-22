package controllers;

import factories.CallFactory;
import models.Call;
import play.mvc.*;
import services.CallService;

import java.util.List;

public class Application extends Controller {
    private static final String SERVICE_NAME_TWILIO = "twilio";

    public static void index() {
        List<Call> calls = Call.findAll();
        render(calls);
    }

    public static void callNumber(String number) {
        CallService callService = CallFactory.getCallService(SERVICE_NAME_TWILIO);
        Call call = (Call) callService.makeCall(number, "1");
        call.save();
        render(call);
    }
}