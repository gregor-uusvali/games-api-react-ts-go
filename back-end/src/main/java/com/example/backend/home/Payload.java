package com.example.backend.home;

public class Payload {
    private final String status;
    private final String message;
    private final String version;

    public Payload(String status, String message, String version) {
        this.status = status;
        this.message = message;
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getVersion() {
        return version;
    }
}