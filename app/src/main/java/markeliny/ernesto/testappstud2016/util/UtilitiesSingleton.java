package markeliny.ernesto.testappstud2016.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import markeliny.ernesto.testappstud2016.database.IDBManager;
import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * Created by Neness on 21/08/2016.
 *
 * This Class looks after the data dowloading, jason parsing and provides a Rockstars Model(
 * a simple list) that can be used inside of the others activities for example.
 *
 * SINGLETON
 */
public class UtilitiesSingleton {

    private static final String URL_BASE_IMAGE = "http://54.72.181.8/yolo/";

    private static UtilitiesSingleton instance = null;

    private static URL mURLJSONSource = null;

    private static IDBManager mDBManager = null;

    private List<Rockstar> rockstarsList = null;


    /*
     * CONSTRUCTOR
     * @throws IOException
     */
    private UtilitiesSingleton() {

    }

    /*
     *
     * @param url
     * @return
     * @throws IOException
     */
    private static InputStream OpenHttpConnection(URL url) throws IOException {
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.setDoOutput(true);
        httpConn.connect();
        return httpConn.getInputStream();
    }

    /**
     *
     * @param urlJSON
     * @param dbm
     */
    public static synchronized void initializeInstance(URL urlJSON, IDBManager dbm){
        if (instance == null){
            instance = new UtilitiesSingleton();
            mDBManager = dbm;
            mURLJSONSource = urlJSON;
        }
    }

    /**
     *
     * @return
     */
    public synchronized static UtilitiesSingleton getInstance(){
        if (instance == null){
            throw new IllegalStateException(UtilitiesSingleton.class.getSimpleName()+
                    "is not initialized, call initializeInstance(...) method first");
        }
        return instance;
    }

    /**
     * Transform the given {@link URL} to an {@link InputStream} and decode that into a bitmap.
     * if the the url is null or cannot be transformed into an input steram, the function returns
     * null.
     *
     *  Do not call this from UI thread
     * @param url the url where to find the image
     * @return a bitmap or null
     * @throws IOException A failed or an interrupted operation has occurred during the process.
     */
    public static Bitmap downloadImage(URL url) throws IOException {
        Bitmap bitmap = null;
        InputStream iS = null;
        if (url != null) {
            iS = OpenHttpConnection(url);
            bitmap = BitmapFactory.decodeStream(iS);
            iS.close();
        }
        return bitmap;
    }

    /**
     *
     * @return
     */
    public IDBManager getmDBManager() {
        return mDBManager;
    }

    /**
     * Download the JSON file from an URL.
     *
     * Do not call this from UI thread
     * @throws IOException A failed or an interrupted operation has occurred during the process.
     */
    public void downloadFromJSON() throws IOException {
        ObjectMapper mObjectMapper;
        InputStream iS;
        mObjectMapper = new ObjectMapper();
        iS = OpenHttpConnection(mURLJSONSource);
        HashMap<String, ArrayList<Rockstar>> map;
        map = mObjectMapper.readValue(iS,new TypeReference<HashMap<String,ArrayList<Rockstar>>>() {});
        rockstarsList = map.get("contacts");
        for (Rockstar r: rockstarsList) {
            r.setPhoto(downloadImage(new URL(URL_BASE_IMAGE+r.getHisFace())));
        }
        iS.close();
    }

    /**
     * Download the rock star list from the database.
     *
     * Note: Do not call this method from a UI thread
     * @throws IOException
     */
    public void downloadFromDatabase() throws IOException {
        mDBManager.openRockstarsTable();
        rockstarsList = mDBManager.getRockstarList();
        mDBManager.closeRockstarsTable();
    }

    /**
     * Give the rock star list contained in this object.
     *
     * Note: One of the downloads methods must be called before.
     * @return The list of the rock stars or null.
     */
    public List<Rockstar> getRockStarsList(){
        return rockstarsList;
    }
}
