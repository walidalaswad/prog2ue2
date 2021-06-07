package trafficlight.states;

//TODO implement a part of the pattern here

import trafficlight.gui.Observer;
import trafficlight.gui.Subject;
import trafficlight.gui.TrafficLight;

import java.util.ArrayList;
import java.util.List;

public abstract class State implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    public abstract State getNextState();

    public abstract String getColor();

    @Override
    public void addObserver(Observer observer){
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer){
        this.observerList.remove(observer);
    }

    @Override
    public void notifyObservers(){
        for(Observer observer : this.observerList) {
            observer.update();
        }
    }
}