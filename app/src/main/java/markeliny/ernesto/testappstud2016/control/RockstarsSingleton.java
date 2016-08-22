package markeliny.ernesto.testappstud2016.control;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * Created by Neness on 21/08/2016.
 *
 * This Class looks after the the data dowloading, jason parsing and provides a Rockstars Model(
 * a simple list) that
 * can be used inside of the others activities for example.
 *
 * SINGLETON
 */
public class RockstarsSingleton {

    private static final String URL_JSON = "http://54.72.181.8/yolo/contacts.json";

    private static final String URL_IM = "http://54.72.181.8/yolo/";

    private static RockstarsSingleton instance = null;

    private List<Rockstar> rockstarsList = null;

    /*
     * CONSTRUCTOR
     * @throws IOException
     */
    private RockstarsSingleton() {
    }

    /*
     *
     * @param url
     * @return
     * @throws IOException
     */
    private InputStream OpenHttpConnection(URL url) throws IOException {
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.setDoOutput(true);
        httpConn.connect();
        return httpConn.getInputStream();
    }

    /*
     *
     * @param url
     * @param options
     * @return
     * @throws IOException
     */
    private Bitmap loadBitmap(URL url, BitmapFactory.Options options) throws IOException {
        Bitmap bitmap = null;
        InputStream iS = null;
        iS = OpenHttpConnection(url);
        bitmap = BitmapFactory.decodeStream(iS, null, options);
        iS.close();
        return bitmap;
    }

    /**
     * This method should not be called within UI thread
     * @return a new rock star list
     */
    public void download() throws IOException {
        ObjectMapper mObjectMapper;
        mObjectMapper = new ObjectMapper();
        InputStream iS = OpenHttpConnection(new URL(URL_JSON));
        HashMap<String, ArrayList<Rockstar>> map;
        map = mObjectMapper.readValue(iS,new TypeReference<HashMap<String,ArrayList<Rockstar>>>() {});
        rockstarsList = map.get("contacts");
        iS.close();
        BitmapFactory.Options bmOptions;
        bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize = 1;
        for (Rockstar r: rockstarsList){
            r.setPhoto(loadBitmap(new URL(URL_IM + r.getHisFace()), bmOptions));
        }
    }

    /**
     *
     * @return The list of the rock stars if download method has been called before calling this
     * method, null otherwise.
     */
    public List<Rockstar> getRockStarsList(){
        return rockstarsList;
    }

    /**
     *
     * @return the sinle instance of this class.
     * @throws IOException
     */
    public synchronized static RockstarsSingleton getInstance(){
        if (instance == null){
            instance = new RockstarsSingleton();
        }
        return instance;
    }
}
