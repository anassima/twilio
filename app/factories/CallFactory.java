package factories;

import services.CallService;
import services.TwilioCallService;

public class CallFactory {
    private static final String SERVICE_NAME_TWILIO = "twilio";

    public static CallService getCallService(String name) {
        if (SERVICE_NAME_TWILIO.equalsIgnoreCase(name)) {
            return new TwilioCallService();
        }

        return null;
    }
}
