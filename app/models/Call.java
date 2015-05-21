package models;

public class Call {
    private String id;
    private CallStatus status;
    private String to;
    private String from;

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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
