package com.coding.lambda;

public class Response {
    private String greetings;
    private String data;

    public Response() {
    }

    public Response(String greetings, String data) {
        this.greetings = greetings;
        this.data = data;
    }

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
