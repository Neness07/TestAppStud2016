package markeliny.ernesto.testappstud2016.database;

import java.io.IOException;
import java.util.List;

import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 *
 */
public interface IRockstarTableManager {
    /**
     * open the rock star table in read/write mode
     */
    public void open();

    /**
     * close the rock star table
     */
    public void close();

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
    public void insertOrUpdateAll(List<Rockstar> list);

    /**
     * Call this method to insert a row into the database
     * @param aRockStar the rock star to be inserted into the the database
     * @return the row ID of the newly inserted row or -1 if an error occurred or the rock star
     * exists already
     *
     * Note: The database cannot contain two rock stars who have the same firstname and the same
     * lastname.
     */
    public long insertARockStar(Rockstar aRockStar);

    /**
     *
     * @param aRockStar the rock star to delete from the database or null to delete all rows
     * @return
     */
    public int deleteARockStar(Rockstar aRockStar);

    /**
     * Update a rock star.
     * Only the status, hisface or isBookmark fields can be updated.
     * @param aRockStar a rock star to be updated
     * @return the number of row affected
     */
    public int updateARockStar(Rockstar aRockStar);

    /**
     * Select the ID of the given rock star if exists
     * It assumes that there is no or only one rock star matching to this identifier, else
     * the first found is returned
     * @param aRockStar a rock star
     * @return the ID of the given rock star or -1 not exists or an error has occurred
     */
    public long selectIdRockstar(Rockstar aRockStar);
}
