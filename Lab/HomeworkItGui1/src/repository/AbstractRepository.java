package repository;

import hasID.HasID;
import validator.exception.ValidationException;
import validator.Validator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * CRUD operations abstract repository
 * @param <ID> type E must have an attribute of type ID
 * @param <E> type of entities saved in repository
 */
public abstract class AbstractRepository<ID, E extends HasID<ID>> implements CrudRepository<ID, E> {
    private Map<ID,E> entities;
    private Validator<E> validator;

    /**
     * Constructor
     * @param validator must implement Validator interface
     */
    public AbstractRepository(Validator<E> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public E findOne(ID id) {
        if(id == null)
            throw new IllegalArgumentException("id cannot be null");
        return entities.get(id);
    }

    @Override
    public Collection<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) throws ValidationException {
        if(entity == null)
            throw new IllegalArgumentException("entity cannot be null");
        validator.validate(entity); //Throws ValidationException

        E exists = entities.get(entity.getID());

        if(exists == null) {
            entities.put(entity.getID(),entity);
            return null;
        }
        return exists;

    }

    @Override
    public E delete(ID id) {
        if(id == null)
            throw new IllegalArgumentException("id cannot be null");
        return entities.remove(id);
    }

    @Override
    public E update(E entity) {
        if(entity == null)
            throw new IllegalArgumentException("entity cannot be null");
        validator.validate(entity);

        E exists = entities.get(entity.getID());

        if(exists == null)
            return entity;

        entities.put(entity.getID(),entity);
        return null;
    }
}
