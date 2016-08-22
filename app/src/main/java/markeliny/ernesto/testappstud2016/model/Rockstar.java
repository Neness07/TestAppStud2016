package markeliny.ernesto.testappstud2016.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 *
 * Created by Neness on 21/08/2016.
 *
 * This class represents a Rockstar.
 * Each Rockstar have a full name, a status and a picture.
 */
public class Rockstar implements Parcelable {

    private String firstName;

    private String lastName;

    private String status;

    private String hisFace;

    private Bitmap photo;

    private int bookmark;

    /**
     *Construcor
     * @param firstName the firstname of this Rockstar
     * @param lastName the lastname of this Rockstar
     * @param status the status of this Rockstar
     * @param hisFace the file name of the photo of this rock star
     */
    public Rockstar(@JsonProperty("firstname")String firstName,
                    @JsonProperty("lastname")String lastName,
                    @JsonProperty("status")String status,
                    @JsonProperty("hisface")String hisFace)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.hisFace = hisFace;
        this.setPhoto(null);
        this.bookmark = 0;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @return
     */
    public String getHisFace() {
        return hisFace;
    }

    /**
     *
     * @return the picture of this Rockstar
     */
    public Bitmap getPhoto() {
        return photo;
    }

    /**
     * It allows to change the picture
     * @param photo
     */
    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    /**
     * Test if this rockstar belongs to the bookmark list of the user
     * @return
     */
    public boolean isBookmark(){
        return bookmark == 1;
    }

    /**
     *
     * @param b true -> the rockstar will be add to the bookmark list,
     *          false -> the rocksatr will be remove from it.
     */
    public void setBookmark(boolean b) {
        if (b == true)
            bookmark = 1;
        else
            bookmark = 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int flags) {
        destination.writeString(this.firstName);
        destination.writeString(this.lastName);
        destination.writeString(this.status);
        destination.writeParcelable(this.photo,flags);
        destination.writeInt(bookmark);
    }

    /**
     *
     */
    public static final Parcelable.Creator<Rockstar> CREATOR = new Parcelable.Creator<Rockstar>() {
        @Override
        public Rockstar createFromParcel(Parcel source) {
            return new Rockstar(source);
        }

        @Override
        public Rockstar[] newArray(int size) {
            return new Rockstar[size];
        }
    };

    private Rockstar(Parcel src) {
        this.firstName = src.readString();
        this.lastName = src.readString();
        this.status = src.readString();
        this.photo = (Bitmap)src.readParcelable(Bitmap.class.getClassLoader());
        this.bookmark = src.readInt();
    }
}
