package com.coding.lambda;

import java.util.List;

public class Response {
    private String greetings;
    private List<String> data;

    public Response() {
    }

    public Response(String greetings, List<String> data) {
        this.greetings = greetings;
        this.data = data;
    }

    public String getGreetings() {
        return greetings;
    }

    public void setGreetings(String greetings) {
        this.greetings = greetings;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

}
