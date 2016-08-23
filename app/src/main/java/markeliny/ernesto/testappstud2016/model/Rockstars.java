package markeliny.ernesto.testappstud2016.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.view.IObserver;
import markeliny.ernesto.testappstud2016.view.RockstarsChangedEvent;

/**
 * Created by Neness on 21/08/2016.
 *
 * This Class represents the main model of the MVC.
 */
public class Rockstars{

    /*
     * The list of the rockstar
     */
    private List<Rockstar> rockstars;

    /*
     * List of the view which will be updated when this model changes
     */
    private List<IObserver> observers;

    /*
     *
     * @param rockstars
     */
    public Rockstars(List<Rockstar> rockstars) {
        this.rockstars = rockstars;
        this.observers = new ArrayList<>();
    }

    /**
     * GETTER
     * @return the list of the rockstars containing in this model
     */
    public List<Rockstar> getRockstars() {
        return rockstars;
    }

    /**
     * SETTER
     * This allows to set the list of the rockstar
     * @param rockstars the rockstar list which will be set.
     */
    public void setRockstars(List<Rockstar> rockstars) {
        this.rockstars = rockstars;
        notifyAllObservers();
    }

    /**
     * Add an observer to the observer list
     * @param obs
     */
    public void addAnObserver(IObserver obs){
        observers.add(obs);
    }

    /**
     * remove an observer from the observer list
     * @param obs
     */
    public void removeAnObserver(IObserver obs){
        observers.remove(obs);
    }

    /**
     *
     * @param r
     */
    public void addToBookmarks(Rockstar r){
        if (r != null){
            r.setBookmark(true);
            notifyAllObservers();
        }
    }

    /**
     *
     * @param r
     */
    public void removeFromBookmarks(Rockstar r){
        if (r != null){
            r.setBookmark(false);
            notifyAllObservers();
        }
    }
    /*
     * updates the observers
     */
    private void notifyAllObservers(){
        for (IObserver o: observers) {
            o.rockstarListHasChanged(new RockstarsChangedEvent(this, rockstars));
        }
    }
}
