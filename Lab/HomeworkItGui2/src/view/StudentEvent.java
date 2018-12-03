package view;

import domain.Student;

public class StudentEvent implements Event {
    private Student oldData;
    private Student newData;
    private ChangeEventType type;

    public StudentEvent(Student oldData, Student newData, ChangeEventType type) {
        this.oldData = oldData;
        this.newData = newData;
        this.type = type;
    }

    public Student getOldData() {
        return oldData;
    }

    public void setOldData(Student oldData) {
        this.oldData = oldData;
    }

    public Student getNewData() {
        return newData;
    }

    public void setNewData(Student newData) {
        this.newData = newData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public void setType(ChangeEventType type) {
        this.type = type;
    }
}
