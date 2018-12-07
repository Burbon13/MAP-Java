package domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class MessageTask extends Task implements Serializable{

    private transient String mesaj;
    private String to;
    private String from;
    private String date;

    public MessageTask(String taskId, String descriere, String mesaj, String to, String from, String date) {
        super(taskId, descriere);
        this.mesaj = mesaj;
        this.to = to;
        this.from = from;
        this.date = date;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
//        this.date = LocalDateTime.parse(date, formatter);
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString() + "MessageTask{" +
                "mesaj='" + mesaj + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public void execute() {
        System.out.println("Mesaj: " + mesaj + "\nData: " + date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageTask that = (MessageTask) o;
        return Objects.equals(mesaj, that.mesaj) &&
                Objects.equals(to, that.to) &&
                Objects.equals(from, that.from) &&
                Objects.equals(date, that.date) &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDescriere(), that.getDescriere());
    }

    @Override
    public int hashCode() {
        //return Objects.hash(getId(), getDescriere(), mesaj, to, from, date);
        return super.hashCode();
    }
}
