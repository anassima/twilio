package services;

import models.Call;

public interface CallService {
    Call makeCall(String number);

    Call makeCall(String number, String timeout);
}
