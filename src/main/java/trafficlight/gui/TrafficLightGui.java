package trafficlight.gui;

import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.states.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrafficLightGui extends JFrame implements ActionListener, Observer {

    public static final String ACTION_COMMAND_STOP = "stop";

    public static final String NAME_OF_THE_GAME = "Traffic Light";

    private JButton buttonStop;

    private TrafficLight green = null;
    private TrafficLight yellow = null;
    private TrafficLight red = null;

    private TrafficLightCtrl trafficLightCtrl = null;

    public TrafficLightGui(TrafficLightCtrl ctrl){
        super(NAME_OF_THE_GAME);
        trafficLightCtrl = ctrl;
        initLights();
        init();
    }

    private void initLights() {
        //TODO implement a part of the pattern here
        //create the TrafficLight
        green = new TrafficLight(Color.green);
        yellow = new TrafficLight(Color.yellow);
        red = new TrafficLight(Color.red);

        green.turnOn(true);

        trafficLightCtrl.addObserver(new Observer() {
            @Override
            public void update(State state) {
                changeLights(state.getColor());
            }
        });
        //connect subject and observer
    }

    private void init() {
        getContentPane().setLayout(new GridLayout(2, 1));
        buttonStop = new JButton("Stop");
        buttonStop.setActionCommand(ACTION_COMMAND_STOP);
        buttonStop.addActionListener(this);

        JPanel p1 = new JPanel(new GridLayout(3,1));
        p1.add(red);
        p1.add(yellow);
        p1.add(green);


        JPanel p2 = new JPanel(new FlowLayout());
        p2.add(buttonStop);

        getContentPane().add(p1);
        getContentPane().add(p2);
        pack();
    }


    public void showErrorMessage(Exception e) {
        JOptionPane pane = new JOptionPane();
        JDialog dialog = pane.createDialog(this, "Traffic Light Problem");
        JOptionPane.showMessageDialog(dialog, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void actionPerformed(ActionEvent e){
        if (ACTION_COMMAND_STOP.equals(e.getActionCommand())){
            trafficLightCtrl.stop();
        }
    }

    @Override
    public void update(State state) {
        changeLights(state.getColor());
    }

    public void changeLights(String colour){
        switch(colour){
            case "green":
                green.turnOn(true);
                yellow.turnOn(false);
                red.turnOn(false);
                break;
            case "yellow":
                green.turnOn(false);
                yellow.turnOn(true);
                red.turnOn(false);
                break;
            case "red":
                green.turnOn(false);
                yellow.turnOn(false);
                red.turnOn(true);
                break;
            default: break;
        }
    }

    public boolean greenIsOn(){ return green.isOn; }
    public boolean yellowIsOn(){ return yellow.isOn; }
    public boolean redIsOn(){ return red.isOn; }
}
