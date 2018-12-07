package Repo;

import Task.MessageTask;
import Validator.IValidator;
import Validator.ValidationException;

import java.io.*;
import java.util.Map;

public class SerRepo extends MTRepository {
    private String filename;

    public SerRepo(IValidator<MessageTask> v, String filename)  {
        super(v);
        this.filename = filename;
        loadDataFromFile();
    }
    @Override
    public void save(MessageTask entity) throws ValidationException {
        super.save(entity);
        writeToFile();
    }
    public void loadDataFromFile(){
        try(ObjectInputStream ois = new ObjectInputStream (new FileInputStream(new File(filename))))
        {
            super.entityes = (Map<String, MessageTask>) ois.readObject();

            System.out.println("Numar de elemente in repository: "+ois.readObject());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile()
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename))) ) {
            oos.writeObject(entityes);
            oos.writeObject(entityes.size());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
