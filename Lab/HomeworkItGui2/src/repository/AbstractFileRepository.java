package repository;

import hasID.HasID;
import repository.exception.FileRepositoryException;
import validator.Validator;
import validator.exception.ValidationException;

import java.io.*;
import java.util.stream.IntStream;

public abstract class AbstractFileRepository<ID, E extends Serializable & HasID<ID>> extends AbstractRepository<ID, E> {
    private String fileName;

    public AbstractFileRepository(Validator<E> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
        loadFromFile();
    }

    @Override
    public E save(E entity) throws ValidationException {
        E result = super.save(entity);
        if(result == null)
            saveAllToFile();
        return result;
    }

    @Override
    public E delete(ID id) {
        E result = super.delete(id);
        if(result != null)
            saveAllToFile();
        return result;
    }

    @Override
    public E update(E entity) {
        E result = super.update(entity);
       if(result == null)
            saveAllToFile();
        return result;
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(){
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./data/" + fileName))) {
            int len = inputStream.readInt();
            IntStream.range(0, len).forEach(i -> {
                try {
                    super.save((E) inputStream.readObject());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            });
        } catch (IOException ex) {
            throw new FileRepositoryException(ex);
        }
    }

    private void saveAllToFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("./data/" + fileName));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeInt(findAll().size());

            findAll().forEach(entity -> {
                try {
                    objectOutputStream.writeObject(entity);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }
}
