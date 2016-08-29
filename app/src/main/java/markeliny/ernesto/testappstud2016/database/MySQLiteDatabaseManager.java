package markeliny.ernesto.testappstud2016.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.util.List;

import markeliny.ernesto.testappstud2016.model.MyProfile;
import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 *
 */
public class MySQLiteDatabaseManager extends SQLiteOpenHelper implements IDBManager{
    /*
     * DATABASE IFORMATIONS
     */
    private final static int DATABASE_VERSION = 2;
    private final static String DATABASE_NAME = "database.db";

    /**
     * ROCK STAR TABLE INFORMATIONS
     */
    public static final int INDEX_COLONNE_ID_ROCKSTAR = 0;
    public static final int INDEX_COLONNE_FIRST_NAME_ROCKSTAR = 1;
    public static final int INDEX_COLONNE_LAST_NAME_ROCKSTAR = 2;
    public static final int INDEX_COLONNE_STATUS_ROCKSTAR = 3;
    public static final int INDEX_COLONNE_HISFACE_ROCKSTAR = 4;
    public static final int INDEX_COLONNE_IS_BOOKMARK_ROCKSTAR = 5;
    public static final String TABLE_NAME_ROCKSTAR = "ROCKSTAR";
    public static final String ID_ROCKSTAR = "ID_ROCKSTAR";
    public static final String FIRST_NAME_ROCKSTAR = "FIRSTNAME";
    public static final String LAST_NAME_ROCKSTAR = "LASTNAME";
    public static final String STATUS_ROCKSTAR = "STATUS";
    public static final String HISFACE_ROCKSTAR = "HISFACE";
    public static final String IS_BOOKMARK_ROCKSTAR = "IS_BOOKMARK";
    public static  final String CREATE_TABLE_ROCKSTAR = "CREATE TABLE " + TABLE_NAME_ROCKSTAR +
            "(" +
            " " + ID_ROCKSTAR + " INTEGER primary key AUTOINCREMENT," +
            " " + FIRST_NAME_ROCKSTAR + " TEXT NOT NULL, " +
            " " + LAST_NAME_ROCKSTAR + " TEXT NOT NULL, " +
            " " + STATUS_ROCKSTAR + " TEXT, " +
            " " + HISFACE_ROCKSTAR + " TEXT, " +
            " " + IS_BOOKMARK_ROCKSTAR + " INTEGER CHECK (IS_BOOKMARK = 0 OR IS_BOOKMARK = 1), " +
            " " + "CONSTRAINT uni_fn_ln UNIQUE(" + FIRST_NAME_ROCKSTAR + "," + LAST_NAME_ROCKSTAR + ")" +
            ");";

    /**
     * PROFILE TABLE INFORMATION
     */
    public static final int INDEX_COLONNE_ID_PROFILE = 0;
    public static final int INDEX_COLONNE_FIRST_NAME_PROFILE = 1;
    public static final int INDEX_COLONNE_LAST_NAME_PROFILE = 2;
    public static final int INDEX_COLONNE_IMAGE_PATH_PROFILE = 3;
    public static final String TABLE_NAME_PROFILE = "PROFILE";
    public static final String ID_PROFILE = "ID_PROFILE";
    public static final String FIRST_NAME_PROFILE = "FIRSTNAME";
    public static final String LAST_NAME_PROFILE = "LASTNAME";
    public static final String IMAGE_PATH_PROFILE = "IMAGE";
    public static  final String CREATE_TABLE_PROFILE = "CREATE TABLE " + TABLE_NAME_PROFILE +
            "(" +
            " " + ID_PROFILE + " INTEGER primary key AUTOINCREMENT," +
            " " + FIRST_NAME_PROFILE + " TEXT NOT NULL, " +
            " " + LAST_NAME_PROFILE + " TEXT NOT NULL, " +
            " " + IMAGE_PATH_PROFILE + " TEXT, " +
            " " + "CONSTRAINT uni_fn_ln UNIQUE(" + FIRST_NAME_PROFILE + "," + LAST_NAME_PROFILE + ")" +
            ");";


    private IRockstarTableManager mRockStarTableManager;

    private IProfileTableManager mProfileTableManager;

    public MySQLiteDatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mRockStarTableManager = new RockStarTableManager(this);
        mProfileTableManager = new ProfileTableManager(this);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ROCKSTAR); //Create the rock star table
        sqLiteDatabase.execSQL(CREATE_TABLE_PROFILE); //Creat the profile table
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ROCKSTAR);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PROFILE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void openRockstarsTable() {
        mRockStarTableManager.open();
    }

    @Override
    public void openProfileTable() {
        mProfileTableManager.open();
    }

    @Override
    public void closeRockstarsTable() {
        mRockStarTableManager.close();
    }

    @Override
    public void closeProfileTable() {
        mProfileTableManager.close();
    }

    @Override
    public long insertIntoProfileTable(MyProfile profile) {
        return mProfileTableManager.insert(profile);
    }

    @Override
    public int updateProfileTable(MyProfile profile) {
        return mProfileTableManager.update(profile);
    }

    @Override
    public int deleteFromProfileTable(MyProfile profile) {
        return mProfileTableManager.delete(profile);
    }

    @Override
    public MyProfile getProfile(long id) {
        return mProfileTableManager.getProfile(id);
    }

    @Override
    public List<Rockstar> getRockstarList() throws IOException {
        return mRockStarTableManager.getRockstarList();
    }

    @Override
    public void insertOrUpdateRockstars(List<Rockstar> list) {
        mRockStarTableManager.insertOrUpdateAll(list);
    }

    @Override
    public long insertIntoRockStarsTable(Rockstar aRockStar) {
        return mRockStarTableManager.insertARockStar(aRockStar);
    }

    @Override
    public int deleteFromRockstarsTable(Rockstar aRockStar) {
        return mRockStarTableManager.deleteARockStar(aRockStar);
    }

    @Override
    public int updateRockstarsTable(Rockstar aRockStar) {
        return mRockStarTableManager.updateARockStar(aRockStar);
    }
}
