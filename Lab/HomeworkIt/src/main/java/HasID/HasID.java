package HasID;


public interface HasID<ID> {
    ID getID();
    void setID(ID id) throws IllegalArgumentException;
}
