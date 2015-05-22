package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "calls")
public class Call extends Model {
    private String cid;
    private CallStatus status;
    private String callTo;
    private String callFrom;

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

    public String getCallTo() {
        return callTo;
    }

    public void setCallTo(String callTo) {
        this.callTo = callTo;
    }

    public String getCallFrom() {
        return callFrom;
    }

    public void setCallFrom(String callFrom) {
        this.callFrom = callFrom;
    }
}
