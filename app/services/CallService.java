package services;

import models.Call;

public interface CallService {
    Call makeCall(String number);
}
