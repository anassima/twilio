package services;

import models.Call;
import models.PlacedCall;

import java.util.List;

public interface CallService {
    List<Call> getCalls();

    PlacedCall makeCall(String number);

    PlacedCall makeCall(String number, String timeout);
}
