package trafficlight.ctrl;

import trafficlight.gui.TrafficLightGui;
import trafficlight.states.State;


public class TrafficLightCtrl {

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

                this.notifyObservers();
                yellowState.notifyObservers();

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

                this.notifyObservers();
                yellowState.notifyObservers();

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

                    this.notifyObservers();
                    redState.notifyObservers();

                    return redState;
                }else {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one

                    this.notifyObservers();
                    greenState.notifyObservers();

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
}