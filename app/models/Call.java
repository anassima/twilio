package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Call extends Model {
    private String cid;
    private CallStatus status;
    private String to;
    private String from;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
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
