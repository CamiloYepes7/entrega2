package app.model;

import app.dto.PartnerDto;
import app.dto.UserDto;

public class Guest {
    private long id;
    private User userId;
    private Partner partnerId;
    private boolean status;

    public Guest() {
    }

    public long getId() {
        return id;
    }

    public User getUserId() {
        return userId;
    }

    public Partner getPartnerId() {
        return partnerId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(UserDto userId1) {
        this.userId = userId;
    }

    public void setPartnerId(PartnerDto partnerId1) {
        this.partnerId = partnerId;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}