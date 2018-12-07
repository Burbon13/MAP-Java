package II.domain;

import II.HasId;

public abstract class Task implements HasId<String> {
    private String taskID;
    private String descriere;
    public Task(String t,String d){
        taskID=t;
        descriere=d;
    }

    public String getDescriere() {
        return descriere;
    }

    @Override
    public String getId() {
        return taskID;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
    @Override
    public void setId(String taskID) {

        this.taskID = taskID;
    }

    @Override
    public String toString() {
        return "Task: "+taskID+"   descriere: "+descriere;
    }

    public abstract void execute();

}

