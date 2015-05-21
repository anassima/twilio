package translators;

import models.Call;
import models.CallStatus;

public class CallTranslator {
    private static final String STATUS_SUCCESS = "completed";

    public static Call translate(com.twilio.sdk.resource.instance.Call twilioCall) {
        Call call = new Call();

        call.setCid(twilioCall.getSid());
        call.setStatus(convertStatus(twilioCall.getStatus()));

        return call;
    }

    private static CallStatus convertStatus(String status) {
        if (STATUS_SUCCESS.equalsIgnoreCase(status)) {
            return CallStatus.SUCCESS;
        }

        return null;
    }
}
