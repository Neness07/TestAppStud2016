package markeliny.ernesto.testappstud2016.control;

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
     * This method should download a new list from the server and update the model
     * So bear in mind that this method must be called in other thread than UI thread.
     */
    public void refresh();

    /**
     * Register a fragment as a view in this controller
     * @param fv
     */
    public void registerView(FragmentView fv);
}
