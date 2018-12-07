package II.valid;

public interface IValidator<E> {
    void validate(E entity) throws ValidationException;
}
