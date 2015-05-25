package translators;

import com.twilio.sdk.resource.instance.Call;
import models.CallStatus;
import models.PlacedOutgoingCall;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class CallTranslator {
    private static final List<AbstractMap.SimpleEntry<String, CallStatus>> STATUS_LOOKUP =
            new ArrayList<AbstractMap.SimpleEntry<String, CallStatus>>();

    static {
        STATUS_LOOKUP.add(new AbstractMap.SimpleEntry<String, CallStatus>("queued", CallStatus.QUEUED));
        STATUS_LOOKUP.add(new AbstractMap.SimpleEntry<String, CallStatus>("completed", CallStatus.COMPLETED));
        STATUS_LOOKUP.add(new AbstractMap.SimpleEntry<String, CallStatus>("ringing", CallStatus.RINGING));
        STATUS_LOOKUP.add(new AbstractMap.SimpleEntry<String, CallStatus>("in-progress", CallStatus.IN_PROGRESS));
        STATUS_LOOKUP.add(new AbstractMap.SimpleEntry<String, CallStatus>("busy", CallStatus.BUSY));
        STATUS_LOOKUP.add(new AbstractMap.SimpleEntry<String, CallStatus>("failed", CallStatus.FAILED));
        STATUS_LOOKUP.add(new AbstractMap.SimpleEntry<String, CallStatus>("no-answer", CallStatus.NO_ANSWER));
        STATUS_LOOKUP.add(new AbstractMap.SimpleEntry<String, CallStatus>("canceled", CallStatus.CANCELED));
    }

    public static PlacedOutgoingCall translate(Call twilioCall) {
        PlacedOutgoingCall placedCall = new PlacedOutgoingCall();

        addBaseProperties(placedCall, twilioCall);
        addAdditionalProperties(placedCall, twilioCall);

        return placedCall;
    }

    private static void addBaseProperties(
            PlacedOutgoingCall placedCall, Call twilioCall) {

        placedCall.setCid(twilioCall.getSid());
        placedCall.setStatus(convertStatus(twilioCall.getStatus()));
        placedCall.setCallTo(twilioCall.getTo());
        placedCall.setCallFrom(twilioCall.getFrom());
    }

    private static void addAdditionalProperties(
            PlacedOutgoingCall placedCall, Call twilioCall) {

        if (hasAdditionalProperties(twilioCall)) {
            placedCall.setCreated(twilioCall.getDateCreated());
            placedCall.setUpdated(twilioCall.getDateUpdated());
            placedCall.setStart(twilioCall.getStartTime());
            placedCall.setEnd(twilioCall.getEndTime());
            placedCall.setDuration(Integer.parseInt(twilioCall.getDuration()));
        }
    }

    private static boolean hasAdditionalProperties(Call twilioCall) {
        return twilioCall.getDuration() != null;
    }

    private static CallStatus convertStatus(String status) {
        if (status != null) {
            for (AbstractMap.SimpleEntry<String, CallStatus> entry : STATUS_LOOKUP) {
                if (status.equalsIgnoreCase(entry.getKey())) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }
}
