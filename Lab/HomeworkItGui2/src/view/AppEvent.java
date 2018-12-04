package view;

import domain.Student;

public class AppEvent implements Event {
    private DataEvent oldData;
    private DataEvent newData;
    private EventClass eventClass;
    private ChangeEventType type;

    public AppEvent(DataEvent oldData, DataEvent newData, EventClass eventClass, ChangeEventType type) {
        this.oldData = oldData;
        this.newData = newData;
        this.type = type;
        this.eventClass = eventClass;
    }

    public DataEvent getOldData() {
        return oldData;
    }

    public void setOldData(DataEvent oldData) {
        this.oldData = oldData;
    }

    public DataEvent getNewData() {
        return newData;
    }

    public void setNewData(DataEvent newData) {
        this.newData = newData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public void setType(ChangeEventType type) {
        this.type = type;
    }

    public EventClass getEventClass() {
        return eventClass;
    }

    public void setEventClass(EventClass eventClass) {
        this.eventClass = eventClass;
    }
}
