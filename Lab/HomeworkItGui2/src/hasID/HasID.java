package hasID;


public interface HasID<ID> {

    /** Getter for id
     * @return the id of the entity
     */
    ID getID();

    /** Setter for id
     * @param id the id of the entity to be set
     */
    void setID(ID id);
}
