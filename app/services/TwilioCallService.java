package services;

import builders.TwilioBuilder;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.list.CallList;
import models.OutgoingCall;
import models.PlacedOutgoingCall;
import translators.CallTranslator;
import play.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    public List<OutgoingCall> getCalls() {
        List<OutgoingCall> outgoingCalls = new ArrayList<OutgoingCall>();
        Map<String, String> params = new HashMap<String, String>();

        CallList twilioCalls = client.getAccount().getCalls(params);

        if (twilioCalls != null) {
            for (Call call : twilioCalls) {
                outgoingCalls.add(CallTranslator.translate(call));
            }
        }

        return outgoingCalls;
    }

    public PlacedOutgoingCall makeCall(String number) {
        return makeCall(number, null);
    }

    public PlacedOutgoingCall makeCall(String number, String timeout) {
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

    private PlacedOutgoingCall makeOutGoingCall(Map<String, String> params) throws TwilioRestException {
        CallFactory callFactory = client.getAccount().getCallFactory();
        Call call = callFactory.create(params);

        return CallTranslator.translate(call);
    }
}
