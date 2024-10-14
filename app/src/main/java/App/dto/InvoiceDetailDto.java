package app.dto;

import app.model.Invoice;

public class InvoiceDetailDto {
    private long id;
    private InvoiceDto invoiceId;
    private int item;
    private String description;
    private double amount;

    public InvoiceDetailDto() {}

    public long getId() {
        return id;
    }

    public InvoiceDto getInvoiceId() {
        return invoiceId;
    }

    public int getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public void setId(long id1) {
        this.id = id;
    }

    public void setInvoiceId(Invoice invoiceId1) {
        this.invoiceId = invoiceId;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void getInvoiceDetailAmountDto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setItemNumber(int countInvoiceDetails) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}