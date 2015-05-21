package models;

public class Call {
    private String id;
    private CallStatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CallStatus getStatus() {
        return CallStatus.SUCCESS;
    }

    public void setStatus(CallStatus status) {
        this.status = status;
    }
}
