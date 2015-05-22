package translators;

import javafx.util.Pair;
import models.CallStatus;
import models.PlacedCall;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class CallTranslator {
    private static final List<Pair<String, CallStatus>> STATUS_LOOKUP = new ArrayList<Pair<String, CallStatus>>();
    private static DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss Z");

    static {
        STATUS_LOOKUP.add(new Pair("queued", CallStatus.QUEUED));
        STATUS_LOOKUP.add(new Pair("completed", CallStatus.COMPLETED));
        STATUS_LOOKUP.add(new Pair("ringing", CallStatus.RINGING));
        STATUS_LOOKUP.add(new Pair("in-progress", CallStatus.IN_PROGRESS));
        STATUS_LOOKUP.add(new Pair("busy", CallStatus.BUSY));
        STATUS_LOOKUP.add(new Pair("failed", CallStatus.FAILED));
        STATUS_LOOKUP.add(new Pair("no-answer", CallStatus.NO_ANSWER));
        STATUS_LOOKUP.add(new Pair("canceled", CallStatus.CANCELED));
    }

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
        placedCall.setCallTo(twilioCall.getTo());
        placedCall.setCallFrom(twilioCall.getFrom());
    }

    private static void addAdditionalProperties(
            PlacedCall placedCall, com.twilio.sdk.resource.instance.Call twilioCall) {

        if (hasAdditionalProperties(twilioCall)) {
            placedCall.setCreated(new DateTime(twilioCall.getDateCreated()));
            placedCall.setUpdated(new DateTime(twilioCall.getDateUpdated()));
            placedCall.setStart(DATE_FORMAT.parseDateTime("" + twilioCall.getStartTime()));
            placedCall.setEnd(DATE_FORMAT.parseDateTime("" + twilioCall.getEndTime()));
            placedCall.setDuration(Integer.parseInt(twilioCall.getDuration()));
        }
    }

    private static boolean hasAdditionalProperties(com.twilio.sdk.resource.instance.Call twilioCall) {
        return twilioCall.getDuration() != null;
    }

    private static CallStatus convertStatus(String status) {
        for (Pair<String, CallStatus> pair : STATUS_LOOKUP) {
            if (status.equalsIgnoreCase(pair.getKey())) {
                return pair.getValue();
            }
        }

        return null;
    }
}
