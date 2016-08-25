package markeliny.ernesto.testappstud2016.control;

import android.widget.ImageView;

import java.util.List;

import markeliny.ernesto.testappstud2016.model.Rockstar;
import markeliny.ernesto.testappstud2016.view.FragmentView;

/**
 * Created by Neness on 23/08/2016.
 */
public interface IActivityController {

    /**
     * Give the list of the rock star contained in the model which is instantiate in this controller
     * (Activity)
     * @return
     */
    public List<Rockstar> getRockStarListFromModel();

    /**
     * Obtain a new list from the singleton
     */
    public void refresh();

    /**
     * Register a fragment as a view in this controller
     * @param fv the fragment view to register
     */
    public void registerView(FragmentView fv);

    /**
     * Specify the action to do when the user clicks on the rockstar's image
     * @param aRockStar aRockStar The rock star on which the user clicked on his image
     */
    void rockStarImageClicked(Rockstar aRockStar);

    /**
     * Specify the action to do when the user clicks on the rockstar's check box
     * @param aRockStar aRockStar The rock star on which the user clicked on his check box
     */
    void rockStarCheckBoxCliked(Rockstar aRockStar);

    /**
     * Specify the action to do when the user clicks on the rockstar's status
     * @param aRockStar The rock star on which the user clicked on his status
     */
    void rockStarStatusClicked(Rockstar aRockStar);

    /**
     * Delete a rock star from the book marks
     * @param aRockStar the rock star to delete
     */
    void deleteFromBookMarks(Rockstar aRockStar);
}
