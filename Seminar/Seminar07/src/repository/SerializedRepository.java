package repository;

import domain.MessageTask;
import validation.IValidation;
import validation.ValidationException;

import java.io.*;
import java.util.Map;

public class SerializedRepository extends MessageTaskRepository {
    private String file_name;
    public SerializedRepository(IValidation<MessageTask> validator, String file_name) {
        super(validator);
        this.file_name = file_name;
        this.load_data();
    }

    private void load_data(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(this.file_name)))) {
            super.entities = (Map<String, MessageTask>) ois.readObject();
            System.out.println(ois.readObject()); // numarul de elemente din entities
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(MessageTask mt){
        try {
            super.save(mt);
            saveToFile();
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(this.file_name)))){
            oos.writeObject(entities);
            oos.writeObject(entities.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
