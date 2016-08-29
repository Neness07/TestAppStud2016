package markeliny.ernesto.testappstud2016.database;

import markeliny.ernesto.testappstud2016.model.MyProfile;

/**
 *
 */
public interface IProfileTableManager {
    /**
     * Open table profile in read/write mode
     */
    public void open();

    /**
     * Close table profile
     */
    public void close();

    /**
     * insert the given profile into the database
     * @param profile the profile to insert
     * @return the row ID of the newly created profile
     */
    public long insert(MyProfile profile);

    /**
     * updtae the given profile
     * @param profile the profile to update
     * @return the number of the row updated
     */
    public int update(MyProfile profile);

    /**
     * delete the given profile from the database
     * @param profile the profile to delete
     * @return the number of the row affected
     */
    public int delete(MyProfile profile);

    /**
     * Return the profile matching to the given identifier.
     * It assumes that there is no or only one rock star matching to this identifier, else
     * the first found is returned
     * @param id the id of the profile
     * @return the profile matching to the given identifier or null if there is not
     */
    public MyProfile getProfile(long id);
}
