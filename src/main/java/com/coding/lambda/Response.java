package com.coding.lambda;

import java.util.List;

public class Response {
    private String greetings;
    private List<String> data;
    private long totalTimeInMilliSeconds;

    public Response() {
    }

    public Response(String greetings, List<String> data, long totalTimeInMs) {
        this.greetings = greetings;
        this.data = data;
        this.totalTimeInMilliSeconds = totalTimeInMs;
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

    public long getTotalTimeInMilliSeconds() {
        return totalTimeInMilliSeconds;
    }

    public void setTotalTimeInMilliSeconds(long totalTimeInMilliSeconds) {
        this.totalTimeInMilliSeconds = totalTimeInMilliSeconds;
    }

}
