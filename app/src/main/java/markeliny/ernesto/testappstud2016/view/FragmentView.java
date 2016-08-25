package markeliny.ernesto.testappstud2016.view;

import java.util.List;

import markeliny.ernesto.testappstud2016.control.IActivityController;
import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * Created by Neness on 24/08/2016.
 */
public interface FragmentView {
    /**
     *
     * @param rockstars
     */
    public void updateFragmentView(List<Rockstar> rockstars);

    /**
     *
     * @return the controller(activity) of this fragment
     */
    public IActivityController getController();
}
