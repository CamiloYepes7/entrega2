package app.model;

import app.dto.PartnerDto;
import app.dto.PersonDto;
import java.util.Date;

public class Invoice {
    private long id;
    private Person personId;
    private Partner partnerId;
    private Date creationDate;
    private double amount;
    private boolean status;

    public long getId() {
        return id;
    }

    public Person getPersonId() {
        return personId;
    }

    public Partner getPartnerId() {
        return partnerId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public double getAmount() {
        return amount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPersonId(PersonDto personId1) {
        this.personId = personId;
    }

    public void setPartnerId(PartnerDto partnerId1) {
        this.partnerId = partnerId;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}