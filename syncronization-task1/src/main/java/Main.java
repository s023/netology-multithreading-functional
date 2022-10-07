public class Main {
    public static final int BUYERS_IN_HOUSE = 10;

    public static void main(String[] args) throws InterruptedException {
        final Dealership dealership = new Dealership();

        for (int i = 1; i <= BUYERS_IN_HOUSE; i++) {
            new Thread(null, dealership::sellCar, "Покупатель " + i).start();
            Thread.sleep(1000);
            if (BUYERS_IN_HOUSE>3){
                new Thread(null, dealership::receiveCar, "Производитель").start();
            }
        }
    }
}