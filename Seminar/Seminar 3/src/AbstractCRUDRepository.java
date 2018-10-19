import java.util.*;
public abstract class AbstractCRUDRepository<ID , E extends HasId<ID>> implements Repository<ID,E> {
    Map<ID,E> entityes;
    IValidator<E> validator;
    public AbstractCRUDRepository(IValidator v){
        entityes=new HashMap<ID,E>();
        validator=v;
    }
    @Override
    public E findOne(ID id){
        return entityes.get(id);
    }

    @Override
    public Iterable<E> findAll(){
        return entityes.values();
    }
    @Override
    public void save(E entity) throws ValidationException{
        validator.validate(entity);
        entityes.put(entity.getId(),entity);
    }
    @Override
    public void delete(ID id){
        entityes.remove(id);
    }
    @Override
    public E update(E entity){
        try {
            validator.validate(entity);
            if (findOne(entity.getId()) == null) {
                return null;
            } else {
                return entityes.replace(entity.getId(), entity);
            }
        }catch(ValidationException e){
            return null;
        }
    }
    @Override
    public long size(){
        return entityes.size();
    }

}
