package services;

import models.PlacedCall;

public interface CallService {
    PlacedCall makeCall(String number);

    PlacedCall makeCall(String number, String timeout);
}
