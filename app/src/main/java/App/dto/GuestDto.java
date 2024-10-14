package app.dto;

import app.model.Partner;
import app.model.User;

public class GuestDto {
    private long id;
    private UserDto userId;
    private PartnerDto partnerId;
    private boolean status;

    public GuestDto() {}

    public long getId() {
        return id;
    }

    public UserDto getUserId() {
        return userId;
    }

    public PartnerDto getPartnerId() {
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

    public void setPartnerId(Partner partnerId1) {
        this.partnerId = partnerId;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setStatus(String activo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

   
    
    
}
