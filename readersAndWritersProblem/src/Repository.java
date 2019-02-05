import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Repository {

    private static Repository instance = null;

    private Queue<Car> street;
    private Queue<Car> waitingRight;
    private Queue<Car> waitingLeft;
    private Boolean snowPlowIsDriving = false;
    private SnowPlow plow;

    private Repository(){
        street = new LinkedList<>();
        waitingRight = new LinkedList<>();
        waitingLeft = new LinkedList<>();

    }
    public static Repository getRepo() {
        if (instance == null) instance = new Repository();
        return instance;
    }

    public void drive(Car car, Character side) {
        if (side.equals('r')) {
            waitingRight.add(car);
        } else if (side.equals('l')){
            waitingLeft.add(car);
        }
    }
    public Queue<Car> getCars(Character queue) {
        switch (queue) {
            case 'l':
                return waitingLeft;
            case 'r':
                return waitingRight;
            default:
                return street;
        }
    }
    public Boolean isSnowPlowDriving(){return snowPlowIsDriving;}
    public SnowPlow getPlow() {return plow;}

    public void startCar(Character side) {
        Car car;
        if (waitingRight.size()!= 0) {
            car = waitingRight.remove();
            street.add(car);
            car.start();
        }
        if (waitingLeft.size() != 0){
            car = waitingLeft.remove();
            street.add(car);
            car.start();
        }
    }


    public void startSnowPlow() {
        plow = new SnowPlow();
        snowPlowIsDriving = true;
        plow.start();
    }

    public void stopCar() {
        street.remove();
    }

    public void stopSnowPlow() {

        snowPlowIsDriving = false;
    }
}
