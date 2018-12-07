package domain;

import repository.HasId;

import java.io.Serializable;

public abstract class Task implements HasId<String>, Serializable {

    private String taskID;
    private String descriere;

    public Task(String taskId, String descriere){
        this.taskID = taskId;
        this.descriere = descriere;
    }

    public Task(){

    }

    public String getTaskID(){
        return taskID;
    }

    public String getId() {
        return taskID;
    }

    public void setId(String taskID) {
        this.taskID = taskID;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return "Task{" +
                "taskID='" + taskID + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }
}
