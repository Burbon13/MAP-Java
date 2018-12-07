package II.domain;

import II.HasId;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageTask extends Task {
    private String mesaj,from,to;
    LocalDateTime date;

    public MessageTask(String taskID,String descriere,String mesaj,String from,String to,String date){
        super(taskID,descriere);
        this.mesaj = mesaj;
        this.from = from;
        this.to = to;
        this.date= LocalDateTime.parse(date,DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    @Override
    public void execute() {
        System.out.println(mesaj+" "+date);
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+"mesaj: "+mesaj+" from: "+from+" to: "+to+" date: "+date.toString();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getMesaj() {
        return mesaj;
    }

    public String getTo() {
        return to;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public void setTo(String to) {
        this.to = to;
    }
}

