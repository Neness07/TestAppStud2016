package markeliny.ernesto.testappstud2016.model;

import android.graphics.Bitmap;

/**
 *
 */
public class MyProfile {

    private long mId;

    private String mName;

    private  String mFirstName;

    private String pathImage;

    private Bitmap mPhoto;

    public MyProfile(long id, String mName, String mFirstName) {
        mId = id;
        this.mName = mName;
        this.mFirstName = mFirstName;
    }

    public void setPhoto(Bitmap mPhoto) {
        this.mPhoto = mPhoto;
    }

    public long getId() {
        return mId;
    }

    public String getName() {

        return mName;
    }

    public Bitmap getPhoto() {
        return mPhoto;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    @Override
    public String toString() {
        return mName.toUpperCase() + " " + mFirstName;
    }
}
