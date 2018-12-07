package validator;

import validator.exception.ValidationException;

public interface Validator<E> {
    void validate(E entity) throws ValidationException;
}
