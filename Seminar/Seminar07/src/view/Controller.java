package view;

import domain.MessageTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import services.Service;
import validation.ValidationException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Controller implements Observer<MessageTaskEvent>  {
    private Service service;
    private ObservableList<MessageTask> observableList;

    public Controller(Service service) {
        this.service = service;
        this.service.addObserver(this);
        observableList = FXCollections.observableList(StreamSupport.stream(service.findAll().spliterator(), false).collect(Collectors.toList()));
    }

    @Override
    public void update(MessageTaskEvent messageTaskEvent) {
        if(messageTaskEvent.getType() == ChangeEventType.ADD){
            observableList.add(messageTaskEvent.getData());
        }
    }

    public ObservableList getList(){
        return observableList;
    }

    public void addTask(String id, String descriere, String message) throws ValidationException {
        MessageTask messageTask = new MessageTask(id, descriere, message, "me", "you", "2008-10-10");
        service.adaugare(messageTask);
    }
}
