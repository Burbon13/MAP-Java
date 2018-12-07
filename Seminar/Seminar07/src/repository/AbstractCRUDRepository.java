package repository;

import validation.IValidation;
import validation.ValidationException;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCRUDRepository<ID, E extends HasId<ID>> implements CRUDRepository<ID, E> {
    Map<ID, E> entities;
    IValidation<E> validator;

    public AbstractCRUDRepository(IValidation<E> validator) {
        this.entities = new HashMap<>();
        this.validator = validator;
    }

    @Override
    public E findOne(ID id) {
        return this.entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        return this.entities.values();
    }

    @Override
    public void save(E entity) throws ValidationException {
        //this.validator.validate(entity);
        //this.entities.put(entity.getId(), entity);
        if(entity == null){
            throw new NullPointerException("Null entity");
        }
        this.validator.validate(entity);
        this.entities.putIfAbsent(entity.getId(), entity);
    }

    @Override
    public void delete(ID id) {
        this.entities.remove(id);
    }

    @Override
    public E update(E entity) {
        try {
            this.validator.validate(entity);
            if(findOne(entity.getId()) == null){
                return null;
            }
            else {
                this.entities.replace(entity.getId(), entity);
                return entity;
            }
        }
        catch (ValidationException exc){
            System.out.println("Invalid new entity.");
        }
        return null;
    }

    @Override
    public long size() {
        return this.entities.size();
    }
}
