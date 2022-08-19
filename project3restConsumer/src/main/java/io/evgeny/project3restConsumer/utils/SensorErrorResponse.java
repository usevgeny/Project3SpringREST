package io.evgeny.project3restConsumer.utils;

public class SensorErrorResponse {
    private String message;
    private long timestamp;

    public SensorErrorResponse(String message, long timestamp) {
        super();
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SensorErrorResponse [message=" + message + ", timestamp=" + timestamp + "]";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
