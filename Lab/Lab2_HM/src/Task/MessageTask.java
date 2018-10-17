package Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageTask extends Task {
    private String message, from, to;
    private LocalDateTime date;

    public MessageTask(String taskID, String description, String message, String from, String to, String date) {
        super(taskID, description);
        this.message = message;
        this.from = from;
        this.to = to;
        this.date = LocalDateTime.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public void execute() {
        System.out.println(super.toString() + "| From: " + from + "| To: " + to + "| Message: " + message + "| (" + date +")");
    }
}
