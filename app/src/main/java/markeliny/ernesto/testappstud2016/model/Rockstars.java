package markeliny.ernesto.testappstud2016.model;

import java.util.ArrayList;
import java.util.List;

import markeliny.ernesto.testappstud2016.view.ModelObserver;
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
    private List<ModelObserver> observers;

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
    public void addAnObserver(ModelObserver obs){
        observers.add(obs);
    }

    /**
     * remove an observer from the observer list
     * @param obs
     */
    public void removeAnObserver(ModelObserver obs){
        observers.remove(obs);
    }

    /**
     *
     * @param r
     */
    public void addToBookmarks(Rockstar r){
        if (r != null){
            r.setBookmark(true);
        }
    }

    /**
     *
     * @param r
     */
    public void removeFromBookmarks(Rockstar r){
        if (r != null){
            r.setBookmark(false);
        }
    }
    /*
     * updates the observers
     */
    private void notifyAllObservers(){
        for (ModelObserver o: observers) {
            o.rockstarsChanged(new RockstarsChangedEvent(this, rockstars));
        }
    }

    public boolean contains(Rockstar aRockStar) {
        return rockstars.contains(aRockStar);
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Rockstars){
            Rockstars others = (Rockstars) o;
            return others.getRockstars().size() == rockstars.size() &&
                    rockstars.containsAll(others.getRockstars());
        }
        return false;
    }
}
