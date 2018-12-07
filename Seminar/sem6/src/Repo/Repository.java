package Repo;
import Validator.ValidationException;
public interface Repository<ID,E> {
    E findOne(ID id);
    Iterable<E> findAll();
    void save(E entity) throws ValidationException;
    void delete(ID id);
    E update(E entity);
    long size();
}
