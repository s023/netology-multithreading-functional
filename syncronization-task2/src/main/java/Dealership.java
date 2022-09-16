import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dealership {
    private static final int TIME_TO_SALE = 1000;
    private static final int TIME_TO_RECEIVE = 3000;
    private static final int CARS_TO_SELL = 10;
    private final List<Car> carsAvailable = new ArrayList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public synchronized void sellCar() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон.");
            while (carsAvailable.size() == 0) {
                System.out.println("Машин нет!");
                condition.await();
            }
            Thread.sleep(TIME_TO_SALE);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто.");
            carsAvailable.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void receiveCar() {
        for (int i = 0; i < CARS_TO_SELL; i++) {
            try {
                Thread.sleep(TIME_TO_RECEIVE);
                carsAvailable.add(new Car());
                System.out.println(Thread.currentThread().getName() + " выпустил 1 авто.");
                lock.lock();
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}