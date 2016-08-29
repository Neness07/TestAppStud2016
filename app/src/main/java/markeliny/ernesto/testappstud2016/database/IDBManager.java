package markeliny.ernesto.testappstud2016.database;

import java.io.IOException;
import java.util.List;

import markeliny.ernesto.testappstud2016.model.MyProfile;
import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * This Database maneger manages two tables: rockstar table and profile table
 */
public interface IDBManager {

    /**
     * open the rock stars table
     */
    public void openRockstarsTable();

    /**
     * open the profile table
     */
    public void openProfileTable();

    /**
     * close the rock stars table
     */
    public void closeRockstarsTable();

    /**
     * close the profile table
     */
    public void closeProfileTable();

    /**
     * insert the given profile into the database
     * @param profile the profile to insert
     * @return the row ID of the newly created profile
     */
    public long insertIntoProfileTable(MyProfile profile);

    /**
     * updtae the given profile
     * @param profile the profile to update
     * @return the number of the row updated
     */
    public int updateProfileTable(MyProfile profile);

    /**
     * delete the given profile from the database
     * @param profile the profile to delete
     * @return the number of the row affected
     */
    public int deleteFromProfileTable(MyProfile profile);

    /**
     * Return the profile matching to the given identifier
     * @param id the id of the profile
     * @return the profile matching to the given identifier or null if there is not
     */
    public MyProfile getProfile(long id);

    /**
     *  Give the list of the rock stars
     * @return The list of the rock stars contained in this table or null if the table is empty
     * or an error has occured during the process.
     * @throws IOException A failed or an interrupted operation has occurred during the process
     */
    public List<Rockstar> getRockstarList() throws IOException;

    /**
     * Insert the given rock stars if they are not yet in the database, update them otherwise
     * @param list the rock star list to insert or to update
     * @throws IOException An I/O error has occurred during the task execution
     */
    public void insertOrUpdateRockstars(List<Rockstar> list);

    /**
     * Call this method to insert a row into the database
     * @param aRockStar the rock star to be inserted into the the database
     * @return the row ID of the newly inserted row or -1 if an error occurred or the rock star
     * exists already
     *
     * Note: The database cannot contain two rock stars who have the same firstname and the same
     * lastname.
     */
    public long insertIntoRockStarsTable(Rockstar aRockStar);

    /**
     *
     * @param aRockStar the rock star to delete from the database or null to delete all rows
     * @return
     */
    public int deleteFromRockstarsTable(Rockstar aRockStar);

    /**
     * Update a rock star.
     * Only the status, hisface or isBookmark fields can be updated.
     * @param aRockStar a rock star to be updated
     * @return the number of row affected
     */
    public int updateRockstarsTable(Rockstar aRockStar);
}
