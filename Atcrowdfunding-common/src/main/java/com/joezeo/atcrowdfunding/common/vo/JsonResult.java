package com.joezeo.atcrowdfunding.common.vo;


public class JsonResult{

    private boolean success;
    private String message;
    private Object data;

    public JsonResult(Exception e){
        this.message = e.getMessage();
        this.success = false;
    }

    public JsonResult(String message){
        this.message = message;
        this.success = true;
    }

    public JsonResult(Object data){
        this.data = data;
        this.success = true;
    }

    public JsonResult() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
