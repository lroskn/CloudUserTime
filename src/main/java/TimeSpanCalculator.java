import java.util.List;

public class TimeSpanCalculator {
    public static long calculateTimeSpent(List<Event> events) {
        long startTime = 0;
        long stopTime = 0;

        for (Event event : events) {
            if ("start".equals(event.getEventType())) {
                startTime = event.getTimestamp();
            } else if ("stop".equals(event.getEventType())) {
                stopTime = event.getTimestamp();
            }
        }

        // Ensure that both start and stop times are found
        if (startTime != 0 && stopTime != 0) {
            return stopTime - startTime; // return time spent in milliseconds
        } else {
            throw new IllegalArgumentException("Both start and stop events must be present.");
        }
    }
}