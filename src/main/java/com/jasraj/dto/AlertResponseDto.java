package com.jasraj.dto;

import java.io.Serializable;

public class AlertResponseDto  implements Serializable {
    private String responseMsg;


    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
