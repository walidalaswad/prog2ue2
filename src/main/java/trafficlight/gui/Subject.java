package trafficlight.gui;

public interface Subject {
    <T extends Observer> void addObserver(T t);
    <T extends Observer> void removeObserver(T t);
    void notifyObservers();
}
