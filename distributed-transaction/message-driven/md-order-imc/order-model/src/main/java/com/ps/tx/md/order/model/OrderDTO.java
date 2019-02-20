package com.ps.tx.md.order.model;

public class OrderDTO {
    private Long id;
    private String uuid;
    private Long customerId;
    private String title;
    private Long ticketNumber;
    private String detail;
    private int amount;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", customerId=" + customerId +
                ", title='" + title + '\'' +
                ", ticketNumber=" + ticketNumber +
                ", detail='" + detail + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
