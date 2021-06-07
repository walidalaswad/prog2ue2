package trafficlight.ctrl;

import trafficlight.gui.Observer;
import trafficlight.gui.Subscriber;
import trafficlight.gui.TrafficLightGui;
import trafficlight.states.State;

import java.util.ArrayList;
import java.util.List;

public class TrafficLightCtrl implements Subscriber {

    private List<Observer> observerList = new ArrayList<>();

    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private final TrafficLightGui gui;

    private boolean doRun = true;

    private static TrafficLightCtrl instance = null; //SINGLETON PATTERN: Class variable to store instance

    public TrafficLightCtrl() { //SINGLETON PATTERN: private constructor
        super();
        initStates();
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
        //TODO useful to update the current state
        update();
    }

    public static TrafficLightCtrl getInstance(){ //SINGLETON PATTERN: Access method to retrieve instance
        if(instance==null) instance = new TrafficLightCtrl();
        return instance;
    }


    private void initStates() {
        greenState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                update();
                return yellowState;
            }
            @Override
            public String getColor() {
                return "green";
            }
        };

        redState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                update();
                return yellowState;
            }
            @Override
            public String getColor() {
                return "red";
            }
        };

        yellowState = new State() {
            @Override
            public State getNextState() {
                if (previousState.equals(greenState)) {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    update();
                    return redState;
                }else {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    update();
                    return greenState;
                }
            }
            @Override
            public String getColor() {
                return "yellow";
            }
        };
        currentState = greenState;
        previousState = yellowState;
    }

    public State getGreenState() {
        return greenState;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public void run()  {
        int intervall = 1500;
        while (doRun) {
            try {
                Thread.sleep(intervall);
                nextState();
            } catch (InterruptedException e) {
                gui.showErrorMessage(e);
            }
        }
        System.out.println("Stopped");
        System.exit(0);
    }

    public void nextState() {
        currentState = currentState.getNextState();
    }

    public void stop() {
        doRun = false;
    }

    @Override
    public void addObserver(Observer observer){
        this.observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer){
        this.observerList.remove(observer);
    }

    //sends controller state to GUI
    @Override
    public void update() {
        notifyObservers();
    }

    public void notifyObservers(){
        for(Observer observer : this.observerList) {
            observer.update(currentState);
        }
    }
}