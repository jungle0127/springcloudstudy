package com.ps.infrastructure.mq.msg.model;

public class PseudoMessage {
    private Long id;
    private String data;
    private String hint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public String toString() {
        return "PseudoMessage{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", hint='" + hint + '\'' +
                '}';
    }
}
