package services;

import models.OutgoingCall;
import models.PlacedOutgoingCall;

import java.util.List;

public interface CallService {
    List<OutgoingCall> getCalls();

    PlacedOutgoingCall makeCall(String number);

    PlacedOutgoingCall makeCall(String number, String timeout);
}
