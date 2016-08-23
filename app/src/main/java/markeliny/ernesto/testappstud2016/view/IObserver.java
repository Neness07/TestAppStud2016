package markeliny.ernesto.testappstud2016.view;

import java.io.Serializable;

/**
 * This interface represents a view or observer.
 * All of activities, fragments or widget, etc... that want to be uptaded when the rockstar list
 * changes, have to implement this interface.
 *
 * Created by Neness on 21/08/2016.
 */
public interface IObserver{

    /**
     *
     * @param evt
     */
    public void rockstarListHasChanged(RockstarsChangedEvent evt);
}
