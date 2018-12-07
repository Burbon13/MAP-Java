package repository;

import domain.MessageTask;
import validation.IValidation;
import validation.ValidationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class MessageTaskFileRepository extends MessageTaskRepository {

    protected String fisier;

    public MessageTaskFileRepository(IValidation<MessageTask> validator, String fileName) {
        super(validator);
        fisier = fileName;
        loadFromFile();
    }

    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fisier))) {
            br.lines().collect(Collectors.toList()).forEach(line -> {
                String[] str = line.split("#");
                MessageTask mt = new MessageTask(str[0], str[1], str[2], str[3], str[4], str[5]);
                try {
                    super.save(mt);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException f) {
            f.printStackTrace();
        }
    }

    private void saveToFile(MessageTask mt) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fisier, true))) {
            bw.write(mt.getId() + "#" + mt.getDescriere() + "#" + mt.getMesaj() + "#" + mt.getTo() + "#" + mt.getFrom() + "#" + mt.getDate());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(MessageTask entity) throws ValidationException {
        super.save(entity);
        saveToFile(entity);
    }

    private void loadFromFile2() {
        try (Scanner br = new Scanner(new FileReader(fisier))) {
            //br.findAll().collect(Collectors.toList())
            String line;
            while (br.hasNext()) {
                line = br.nextLine();
                StringTokenizer st = new StringTokenizer(line, "#");
                MessageTask mt = new MessageTask(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
                super.save(mt);
            }
        } catch (IOException f) {
            f.printStackTrace();
        } catch (ValidationException v) {
            System.out.println(v.getMessage());
        }
    }


    public void loadFromFile3() {
        Path br = Paths.get("./dataInfo/tasks.txt");

        try {
            String line;
            List<String> ls = Files.readAllLines(br);
            System.out.println(ls);

        } catch (IOException f) {
            f.printStackTrace();
        }
    }
}
