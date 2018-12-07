package II.repo;

import II.valid.ValidationException;

public interface CRUDRepository<ID,E> {
    E findOne(ID id);
    Iterable<E> findAll();
    void save(E entity) throws ValidationException;
    void delete(ID id);
    E update(E entity);
    long size();
}
