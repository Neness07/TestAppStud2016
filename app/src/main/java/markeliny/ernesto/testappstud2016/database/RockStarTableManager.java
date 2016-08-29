package markeliny.ernesto.testappstud2016.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.util.UtilitiesSingleton;

/**
 * This class is used to connect to the rock star table and alows to read, write, update or delete
 * rock stars
 *
 */
public class RockStarTableManager implements IRockstarTableManager{

    private static final String URL_BASE_IMAGE = "http://54.72.181.8/yolo/";

    private MySQLiteDatabaseManager mSQLite;
    private SQLiteDatabase mTable;

    /**
     * CONSTRUCTOR
     * @param dbM the {@link MySQLiteDatabaseManager} that manages this table manager
     */
    public RockStarTableManager(MySQLiteDatabaseManager dbM){
        mSQLite = dbM;
    }

    @Override
    public void open(){
        mTable = mSQLite.getWritableDatabase();
    }

    @Override
    public void close(){
        mTable.close();
    }

    @Override
    public List<Rockstar> getRockstarList() throws IOException {
        Cursor cursor;
        //deleteARockStar(null);
        if (((cursor = selectAllRockStars()).getCount()) == 0){
            return null;
        }
        List<Rockstar> rockstarList = new ArrayList<>();
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            rockstarList.add(cursorToRockStar(cursor));
            cursor.moveToNext();
        }
        return rockstarList;
    }

    @Override
    public void insertOrUpdateAll(List<Rockstar> list) {
        for (Rockstar r: list){
            if (selectIdRockstar(r) == -1){
                insertARockStar(r);
            } else {
                updateARockStar(r);
            }
        }
    }

   @Override
    public long insertARockStar(Rockstar aRockStar){
        long id = -1;
        ContentValues value;
        id = selectIdRockstar(aRockStar);
        if(id == -1){ //The rock star does not already exist
            value = new ContentValues();
            value.put(MySQLiteDatabaseManager.FIRST_NAME_ROCKSTAR,aRockStar.getFirstName());
            value.put(MySQLiteDatabaseManager.LAST_NAME_ROCKSTAR,aRockStar.getLastName());
            value.put(MySQLiteDatabaseManager.STATUS_ROCKSTAR,aRockStar.getStatus());
            value.put(MySQLiteDatabaseManager.HISFACE_ROCKSTAR,aRockStar.getHisFace());
            if (aRockStar.isBookmark()){
                value.put(MySQLiteDatabaseManager.IS_BOOKMARK_ROCKSTAR,1);
            } else {
                value.put(MySQLiteDatabaseManager.IS_BOOKMARK_ROCKSTAR,0);
            }
            id = mTable.insert(MySQLiteDatabaseManager.TABLE_NAME_ROCKSTAR,null,value);
        }
       return id;
    }

    @Override
    public int deleteARockStar(Rockstar aRockStar){
        if (aRockStar == null){
            return mTable.delete(MySQLiteDatabaseManager.TABLE_NAME_ROCKSTAR,null,null);
        }
        long id = -1;
        id = selectIdRockstar(aRockStar);
        if (id == -1){
            return 0;
        }
        return mTable.delete(MySQLiteDatabaseManager.TABLE_NAME_ROCKSTAR,
                MySQLiteDatabaseManager.ID_ROCKSTAR + " = ? ",new String[]{String.valueOf(id)});
    }

    @Override
    public int updateARockStar(Rockstar aRockStar){
        long id = -1;
        ContentValues value;
        id = selectIdRockstar(aRockStar);
        if (id != -1){
            value = new ContentValues();
            value.put(MySQLiteDatabaseManager.STATUS_ROCKSTAR,aRockStar.getStatus());
            value.put(MySQLiteDatabaseManager.HISFACE_ROCKSTAR,aRockStar.getHisFace());
            if (aRockStar.isBookmark()){
                value.put(MySQLiteDatabaseManager.IS_BOOKMARK_ROCKSTAR,1);
            } else {
                value.put(MySQLiteDatabaseManager.IS_BOOKMARK_ROCKSTAR,0);
            }
            return mTable.update(MySQLiteDatabaseManager.TABLE_NAME_ROCKSTAR,value,
                    MySQLiteDatabaseManager.ID_ROCKSTAR + " = ? ",new String[]{String.valueOf(id)});
        }
        return 0;
    }

    @Override
    public long selectIdRockstar(Rockstar aRockStar) {
        long identifier = -1;
        Cursor cursor = mTable.rawQuery("SELECT " + MySQLiteDatabaseManager.ID_ROCKSTAR +
                " FROM " + MySQLiteDatabaseManager.TABLE_NAME_ROCKSTAR +
                " WHERE " + MySQLiteDatabaseManager.FIRST_NAME_ROCKSTAR + " LIKE ? AND "
                          + MySQLiteDatabaseManager.LAST_NAME_ROCKSTAR + " LIKE ? ",
                new String[]{aRockStar.getFirstName(),aRockStar.getLastName()});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            identifier = cursor.getInt(MySQLiteDatabaseManager.INDEX_COLONNE_ID_ROCKSTAR);
        }
        return identifier;
    }

    /*
     *
     *
     */
    private Cursor selectAllRockStars(){
        return mTable.rawQuery("SELECT * FROM " + MySQLiteDatabaseManager.TABLE_NAME_ROCKSTAR, null);
    }

    private Rockstar cursorToRockStar(Cursor c) throws IOException {
        Rockstar r;
        String firstName = c.getString(MySQLiteDatabaseManager.INDEX_COLONNE_FIRST_NAME_ROCKSTAR);
        String lastname = c.getString(MySQLiteDatabaseManager.INDEX_COLONNE_LAST_NAME_ROCKSTAR);
        String status = c.getString(MySQLiteDatabaseManager.INDEX_COLONNE_STATUS_ROCKSTAR);
        String hisface = c.getString(MySQLiteDatabaseManager.INDEX_COLONNE_HISFACE_ROCKSTAR);
        int isBookmark = c.getInt(MySQLiteDatabaseManager.INDEX_COLONNE_IS_BOOKMARK_ROCKSTAR);
        r = new Rockstar(firstName,lastname,status,hisface);
        r.setBookmark(isBookmark == 1);
        r.setPhoto(UtilitiesSingleton.downloadImage(new URL(URL_BASE_IMAGE+hisface)));
        return r;
    }
}
