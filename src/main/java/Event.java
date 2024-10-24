public class Event {
    private String customerId;
    private String workloadId;
    private long timestamp;
    private String eventType;

    public Event(String customerId, String workloadId, long timestamp, String eventType) {
        this.customerId = customerId;
        this.workloadId = workloadId;
        this.timestamp = timestamp;
        this.eventType = eventType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getWorkloadId() {
        return workloadId;
    }

    public void setWorkloadId(String workloadId) {
        this.workloadId = workloadId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "customerId='" + customerId + '\'' +
                ", workloadId='" + workloadId + '\'' +
                ", timestamp=" + timestamp +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
