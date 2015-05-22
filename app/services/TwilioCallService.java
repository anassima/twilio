package services;

import builders.TwilioBuilder;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import models.PlacedCall;
import translators.CallTranslator;
import play.Logger;

import java.util.Map;

public class TwilioCallService implements CallService {
    public static final String ACCOUNT_SID = "AC9ad35971981a487b60b7dc434f6fbbb8";
    public static final String AUTH_TOKEN = "e75eaac9e7f77ed7b5f7e754cef57986";
    private TwilioRestClient client;

    public TwilioCallService() {
        this(new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN));
    }

    public TwilioCallService(TwilioRestClient client) {
        this.client = client;
    }

    public PlacedCall makeCall(String number) {
        return makeCall(number, null);
    }

    public PlacedCall makeCall(String number, String timeout) {
        Map<String, String> params = buildRequestParams(number, timeout);

        try {
            return makeOutGoingCall(params);
        }
        catch (TwilioRestException e) {
            Logger.warn("Could not make outgoing call to number: " + number, e);
        }

        return null;
    }

    private Map<String, String> buildRequestParams(String number, String timeout) {
        if (timeout != null) {
            return TwilioBuilder.buildOutgoingCallParams(
                    number, "+1" + number, "https://www.twilio.com/docs/errors/21205", timeout);
        }
        else {
            return TwilioBuilder.buildOutgoingCallParams(
                    number, "+1" + number, "https://www.twilio.com/docs/errors/21205");
        }
    }

    private PlacedCall makeOutGoingCall(Map<String, String> params) throws TwilioRestException {
        CallFactory callFactory = client.getAccount().getCallFactory();
        com.twilio.sdk.resource.instance.Call call = callFactory.create(params);

        return CallTranslator.translate(call);
    }
}
