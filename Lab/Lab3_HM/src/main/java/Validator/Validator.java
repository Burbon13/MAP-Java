package Validator;

public interface Validator<E> {
    void validare(E entity) throws ValidationException;
}
