package translators;

import models.CallStatus;
import models.PlacedCall;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CallTranslator {
    private static final String STATUS_SUCCESS = "completed";
    private static DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z");

    public static PlacedCall translate(com.twilio.sdk.resource.instance.Call twilioCall) {
        PlacedCall placedCall = new PlacedCall();

        addBaseProperties(placedCall, twilioCall);
        addAdditionalProperties(placedCall, twilioCall);

        return placedCall;
    }

    private static void addBaseProperties(
            PlacedCall placedCall, com.twilio.sdk.resource.instance.Call twilioCall) {

        placedCall.setCid(twilioCall.getSid());
        placedCall.setStatus(convertStatus(twilioCall.getStatus()));
        placedCall.setTo(twilioCall.getTo());
        placedCall.setFrom(twilioCall.getFrom());
    }

    private static void addAdditionalProperties(
            PlacedCall placedCall, com.twilio.sdk.resource.instance.Call twilioCall) {

        if (hasAdditionalProperties(twilioCall)) {
            placedCall.setCreated(new DateTime(twilioCall.getDateCreated()));
            placedCall.setUpdated(new DateTime(twilioCall.getDateUpdated()));
            placedCall.setStart(DATE_FORMAT.parseDateTime(twilioCall.getStartTime()));
            placedCall.setEnd(DATE_FORMAT.parseDateTime(twilioCall.getEndTime()));
            placedCall.setDuration(Integer.parseInt(twilioCall.getDuration()));
        }
    }

    private static boolean hasAdditionalProperties(com.twilio.sdk.resource.instance.Call twilioCall) {
        return twilioCall.getDuration() != null;
    }

    private static CallStatus convertStatus(String status) {
        if (STATUS_SUCCESS.equalsIgnoreCase(status)) {
            return CallStatus.SUCCESS;
        }

        return null;
    }
}
