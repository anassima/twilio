package services;

import models.Call;

public class TwilioCallService implements CallService {
    public Call makeCall(String number) {
        return new Call();
    }
}
