package markeliny.ernesto.testappstud2016.control;

import android.graphics.Bitmap;

import markeliny.ernesto.testappstud2016.view.ProfileView;

/**
 * This is the controlle of the profile fragment.
 * It controls all the interactions with the profile's picture, the full name input,
 * the control of floating action button and the check menu.
 *
 * Created by Neness on 27/08/2016.
 */
public interface ProfileController {

    public Bitmap takeAPicture();

    public void validate(ProfileView aProfileView);


}
