package view;

import domain.MessageTask;

import java.util.PrimitiveIterator;

public class MessageTaskEvent implements Event {
    public MessageTask getOldData() {
        return oldData;
    }

    public void setOldData(MessageTask oldData) {
        this.oldData = oldData;
    }

    public MessageTask getData() {
        return data;
    }

    public void setData(MessageTask data) {
        this.data = data;
    }

    public ChangeEventType getType() {
        return type;
    }

    public void setType(ChangeEventType type) {
        this.type = type;
    }

    public MessageTaskEvent(MessageTask oldData, MessageTask data, ChangeEventType type) {
        this.oldData = oldData;
        this.data = data;
        this.type = type;
    }

    private MessageTask oldData;
    private MessageTask data;
    private ChangeEventType type;


}
