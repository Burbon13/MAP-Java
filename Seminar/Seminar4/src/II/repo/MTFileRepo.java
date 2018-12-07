package II.repo;

import II.domain.MessageTask;
import II.valid.IValidator;
import II.valid.ValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class MTFileRepo extends MTRepository {
    private String fileName;

    public MTFileRepo(IValidator<MessageTask> v, String fileName) {
        super(v);
        this.fileName = fileName;
    }

    private void load() {
        //Sau buffer writer si file writer
        try (Scanner scanner = new Scanner(new FileReader(fileName))) { //Se ocupa si de inchiderea fisierului
            while(scanner.hasNext()) {
                String buffer = scanner.nextLine();
                /*
                String.split -> String[]
                String.Tokenizer -> List<String>
                Stream.Tokenizer -> next
                 */
                String[] data = buffer.split("#");
                MessageTask mt = new MessageTask(data[0], data[1], data[2], data[3], data[4], data[5]);
                super.save(mt);
            }
        } catch (ValidationException ex) {
            System.out.println(ex.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void write(MessageTask mt) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            bufferedWriter.write(mt.toString()); //Nu e facut cum trebe ptr fisier
            bufferedWriter.newLine();
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void printFromFile() {
        try {
            Path path = Paths.get("./data/tasks.txt"); //sugereaza sa nu punem in cadrul pachetelor cu cod sursa!
            List<String> all = Files.readAllLines(path);
            System.out.println(all);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
