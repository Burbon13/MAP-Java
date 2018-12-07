package Repo;

import Task.MessageTask;
import Validator.*;
import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MTFileRepo extends MTRepository {
    private String file_name;
    public MTFileRepo(IValidator<MessageTask> v, String file_name){
        super(v);
        this.file_name = file_name;
        //load();
    }
    private void load(){
        try(Scanner fi = new Scanner(new FileReader(file_name))){
            fi.findAll("").collect(Collectors.toList()).forEach(e->{
                String[] all="gagaga".split("#");
                MessageTask m =new MessageTask(all[0],all[1],all[2],all[3],all[4],all[5]);
                try {
                    super.save(m);
                } catch (ValidationException e1) {
                    e1.printStackTrace();
                }
            });

            //            while (fi.hasNext()){
//                String ln = fi.nextLine();
//                String[] all=ln.split("#");
//                Task.MessageTask m =new Task.MessageTask(all[0],all[1],all[2],all[3],all[4],all[5]);
//                super.save(m);
//            }
        }catch (FileNotFoundException e){
            System.out.println("Eroare la deschiderea fisierului "+file_name);
//        }catch (IOException e) {
//            System.out.println();
        }
    }
    private void write(MessageTask mt){
        try(BufferedWriter fo = new BufferedWriter(new FileWriter(file_name,true))){
            fo.write(mt.getId()+"#"+mt.getDescriere()+"#"+mt.getMesaj()+"#"+mt.getFrom()+"#"+mt.getTo()+"#"+mt.getDate()+"\n");
        }catch (IOException e){

        }
    }
}
