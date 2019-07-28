package com.bassiuz.meubel;

import java.util.Date;

public class PingResponse {

    private Date currentDate = new Date();
    private Boolean live = true;
    private String message = "Het is je boy ping.";

    public PingResponse()
    {
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
