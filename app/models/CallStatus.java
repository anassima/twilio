package models;

import javax.persistence.Entity;

public enum CallStatus {
    QUEUED,
    RINGING,
    IN_PROGRESS,
    BUSY,
    FAILED,
    NO_ANSWER,
    CANCELED,
    COMPLETED
}
