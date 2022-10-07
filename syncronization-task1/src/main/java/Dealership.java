import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private static final int TIME_TO_SALE = 1000;
    private static final int TIME_TO_RECEIVE = 3000;
    private static final int CARS_TO_SELL = 10;
    private final List<Car> carsAvailable = new ArrayList<>();

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            Thread.sleep(TIME_TO_SALE);
            while (carsAvailable.size() == 0) {
                System.out.println("Машин нет!");
                wait();
            }
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто.");
            carsAvailable.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void receiveCar() {
        for (int i = 0; i < CARS_TO_SELL; i++) {
            try {
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто.");
                carsAvailable.add(new Car());
                synchronized (this) {
                    notify();
                }
                Thread.sleep(TIME_TO_RECEIVE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}