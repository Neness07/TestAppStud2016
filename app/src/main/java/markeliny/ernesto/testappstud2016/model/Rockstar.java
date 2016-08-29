package markeliny.ernesto.testappstud2016.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.codehaus.jackson.annotate.JsonProperty;


/**
 *
 * Created by Neness on 21/08/2016.
 *
 * This class represents a Rockstar.
 * Each Rockstar have a full name, a status and a picture.
 */
public class Rockstar {

    private String firstName;

    private String lastName;

    private String status;

    private String hisFace;

    private Bitmap photo = null;

    private boolean bookmark;

    /**
     *Construcor
     * @param firstName the firstname of this Rockstar
     * @param lastName the lastname of this Rockstar
     * @param status the status of this Rockstar
     * @param hisFace the file name of the photo of this rock star
     */
    public Rockstar(@NonNull@JsonProperty("firstname")String firstName,
                    @NonNull@JsonProperty("lastname")String lastName,
                    @JsonProperty("status")String status,
                    @JsonProperty("hisface")String hisFace)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.hisFace = hisFace;
        this.setPhoto(null);
        this.bookmark = false;
    }

    /**
     *
     * @return the first name of this rock star
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @return the last name of this rock star
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return the current status of this roch star if it's set, null
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @return the picture's file name of this rock star
     */
    public String getHisFace() {
        return hisFace;
    }

    /**
     *
     * @return the picture of this Rockstar it is set
     */
    public Bitmap getPhoto() {
        return photo;
    }

    /**
     * It allows to change the picture of this rock star if the is one, null otherwise
     * @param photo
     */
    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    /**
     * Test if this rockstar belongs to the bookmark list of the user
     * @return true if this rock star is a favorite, false otherwise
     */
    public boolean isBookmark(){
        return bookmark;
    }

    /**
     *
     * @param b true -> the rockstar will be add to the bookmark list,
     *          false -> the rocksatr will be remove from it.
     */
    public void setBookmark(boolean b) {
        bookmark = b;
    }

    @Override
    public String toString(){
        return firstName + " " + lastName.toUpperCase();
    }

    /**
     * To change the status of this rock star
     * @param status the new status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Rockstar){
            Rockstar r = (Rockstar) o;
            return this.firstName.equals(r.getFirstName()) && this.lastName.equals(r.getLastName());
        }
        return false;
    }
}
