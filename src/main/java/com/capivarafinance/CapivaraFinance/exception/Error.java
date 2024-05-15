package com.capivarafinance.CapivaraFinance.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class Error {

    private String msg;
    private Date timestamp;
    private HttpStatus status;

    public Error(String msg, Date timestamp, HttpStatus status) {
        this.msg = msg;
        this.timestamp = timestamp;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
