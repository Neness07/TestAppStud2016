package markeliny.ernesto.testappstud2016.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import markeliny.ernesto.testappstud2016.model.MyProfile;

/**
 *
 */
public class ProfileTableManager implements IProfileTableManager{

    private SQLiteDatabase mTable;
    private MySQLiteDatabaseManager mSQLite; // The Database file

    public ProfileTableManager(MySQLiteDatabaseManager mSQLite) {
        this.mSQLite = mSQLite;
    }


    @Override
    public void open() {
        mTable = mSQLite.getWritableDatabase();
    }

    @Override
    public void close() {
        mTable.close();
    }

    @Override
    public long insert(MyProfile profile) {
        ContentValues value = new ContentValues();
        value.put(MySQLiteDatabaseManager.ID_PROFILE,profile.getId());
        value.put(MySQLiteDatabaseManager.FIRST_NAME_PROFILE,profile.getFirstName());
        value.put(MySQLiteDatabaseManager.LAST_NAME_PROFILE,profile.getName());
        value.put(MySQLiteDatabaseManager.IMAGE_PATH_PROFILE,profile.getPathImage());
        return mTable.insertWithOnConflict(MySQLiteDatabaseManager.CREATE_TABLE_PROFILE,null,value,
                SQLiteDatabase.CONFLICT_IGNORE);
    }

    @Override
    public int update(MyProfile profile) {
        ContentValues value = new ContentValues();
        value.put(MySQLiteDatabaseManager.IMAGE_PATH_PROFILE,profile.getPathImage());
        return mTable.update(MySQLiteDatabaseManager.TABLE_NAME_PROFILE,value,
                MySQLiteDatabaseManager.ID_PROFILE+" = ?",new String[]{String.valueOf(profile.getId())});
    }

    @Override
    public int delete(MyProfile profile) {
        return mTable.delete(MySQLiteDatabaseManager.TABLE_NAME_PROFILE,null,null);
    }

    @Override
    public MyProfile getProfile(long id) {
        Cursor cursor = mTable.rawQuery("SELECT * FROM " +
                MySQLiteDatabaseManager.TABLE_NAME_PROFILE + " WHERE " +
                MySQLiteDatabaseManager.ID_PROFILE + " = " + String.valueOf(id),null);
        MyProfile profile = null;
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            long ID = cursor.getLong(MySQLiteDatabaseManager.INDEX_COLONNE_ID_ROCKSTAR);
            String name = cursor.getString(MySQLiteDatabaseManager.INDEX_COLONNE_LAST_NAME_PROFILE);
            String fn = cursor.getString(MySQLiteDatabaseManager.INDEX_COLONNE_FIRST_NAME_PROFILE);
            String imPath =cursor.getString(MySQLiteDatabaseManager.INDEX_COLONNE_IMAGE_PATH_PROFILE);
            profile = new MyProfile(ID,name,fn);
            profile.setPathImage(imPath);
        }
        return profile;
    }
}
