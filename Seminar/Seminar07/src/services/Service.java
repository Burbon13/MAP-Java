package services;

import domain.MessageTask;
import domain.Task;
import repository.AbstractCRUDRepository;
import validation.ValidationException;
import view.ChangeEventType;
import view.MessageTaskEvent;
import view.Observable;
import view.Observer;

import java.util.ArrayList;

public class Service implements Observable<MessageTaskEvent> {
    private AbstractCRUDRepository<String, MessageTask> repo;
    private ArrayList<Observer<MessageTaskEvent>> observers;
    public Service(AbstractCRUDRepository<String, MessageTask> repo) {
        this.repo = repo;
        this.observers = new ArrayList<>();
    }

    public Iterable<MessageTask> findAll(){
        return repo.findAll();
    }

    public void adaugare(MessageTask msg) throws ValidationException {
        repo.save(msg);
        notifyObservers(new MessageTaskEvent(null, msg, ChangeEventType.ADD));
    }

    @Override
    public void addObserver(Observer<MessageTaskEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<MessageTaskEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(MessageTaskEvent messageTaskEvent) {
        observers.forEach(obs -> obs.update(messageTaskEvent));
    }
}
