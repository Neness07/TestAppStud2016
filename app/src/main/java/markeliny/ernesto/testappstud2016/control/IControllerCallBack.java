package markeliny.ernesto.testappstud2016.control;

import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * Created by Neness on 23/08/2016.
 */
public interface IControllerCallBack {

    /**
     * The given rock star will be add to or remove from the bookmarks
     * @param rockstar
     * @param status true the rock star will be add, false otherwise
     */
    public void onRockStarSelected(Rockstar rockstar, boolean status);
}
