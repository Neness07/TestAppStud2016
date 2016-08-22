package markeliny.ernesto.testappstud2016.view;

import java.util.EventObject;
import java.util.List;

import markeliny.ernesto.testappstud2016.model.Rockstar;

/**
 * This represents just an event like an Event on SWING.
 *
 * Created by Neness on 21/08/2016.
 */
public class RockstarsChangedEvent extends EventObject{

    /*
     *
     */
    private List<Rockstar> newListRockstar;

    /**
     * CONSTRUCTOR
     * @param source the object which is the source of this event. In our case, It will always be
     *               Rockstars class
     *
     * @param lRs the new list after the change
     */
    public RockstarsChangedEvent(Object source, List<Rockstar> lRs) {
        super(source);
        this.newListRockstar = lRs;
    }


    public List<Rockstar> getNewListRockstar(){
        return this.newListRockstar;
    }
}
