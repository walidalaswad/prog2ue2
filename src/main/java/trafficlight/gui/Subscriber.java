package trafficlight.gui;

public interface Subscriber {
    <T extends Observer> void addObserver(T t);
    <T extends Observer> void removeObserver(T t);
    void update();
}
