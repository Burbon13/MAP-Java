import java.util.*;
public class Main {
    public static void main(String[] args){
        Main app=new Main();
        IValidator<MessageTask> v = new MTValidator();
        Repository<String,MessageTask> repo = new MTRepository(v);
        try {
            repo.save(new MessageTask("1", "desc", "", "t1", "226", "10-10-2010 11:11:11"));
            repo.save(new MessageTask("2", "aga", "", "t2", "226", "10-10-2010 11:11:11"));
            repo.save(new MessageTask("3", "aga", "", "t3", "226", "10-10-2010 11:11:11"));
            repo.save(new MessageTask("4", "erher", "", "t4", "", "10-10-2010 11:11:11"));
        }catch (ValidationException e){
            System.out.println(e.getMessage());
        }
        System.out.println(app.filter(repo,"ag"));
    }
    public List<MessageTask> filter(Repository<String,MessageTask> r,String s){
        Iterable<MessageTask> all = r.findAll();
        List<MessageTask> values=new ArrayList<MessageTask>();
        for( MessageTask m: all){
            if(m.getDescriere().contains(s)){
                values.add(m);
            }
        }
        return values;
    }
}
