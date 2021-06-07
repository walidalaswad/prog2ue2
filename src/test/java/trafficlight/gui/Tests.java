/*package trafficlight.gui;

import org.junit.jupiter.api.Test;
import trafficlight.ctrl.TrafficLightCtrl;
import trafficlight.states.State;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestUpdateFunction {

    State greenState;
    State yellowState;
    State redState;

    TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
    TrafficLightGui gui = new TrafficLightGui(ctrl);

    @Test
    public void testGreen(){
        greenState = new State() {
            @Override
            public State getNextState() {
                return yellowState;
            }
            @Override
            public String getColor() {
                return "green";
            }
        };
        gui.changeLights(greenState.getColor());
        assertTrue(gui.greenIsOn());
        assertFalse(gui.yellowIsOn());
        assertFalse(gui.redIsOn());
    }

    @Test
    public void testYellow(){
        yellowState = new State() {
            @Override
            public State getNextState() {
                return redState;
            }
            @Override
            public String getColor() {
                return "yellow";
            }
        };
        gui.changeLights(yellowState.getColor());
        assertTrue(gui.yellowIsOn());
        assertFalse(gui.greenIsOn());
        assertFalse(gui.redIsOn());
    }

    @Test
    public void testRed(){
        redState = new State() {
            @Override
            public State getNextState() {
                return yellowState;
            }
            @Override
            public String getColor() {
                return "red";
            }
        };
        gui.changeLights(redState.getColor());
        assertTrue(gui.redIsOn());
        assertFalse(gui.yellowIsOn());
        assertFalse(gui.greenIsOn());
    }
}
*/