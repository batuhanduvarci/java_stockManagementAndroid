package com.example.agent48.termproject.Object;

/**
 * Created by Agent47 on 31.07.2017.
 */

public class Msg {

    private Integer success;
    private String message;


    public Msg(){

    }

    public Msg(Integer success, String password){

        super();
        this.success = success;
        this.message = message;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
