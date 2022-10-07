public class Main {
    public static final int BUYERS_IN_HOUSE = 10;

    public static void main(String[] args) {
        final Dealership dealership = new Dealership();

        for (int i = 1; i <= BUYERS_IN_HOUSE; i++) {
            new Thread(null, dealership::sellCar, "Покупатель " + i).start();
        }
        new Thread(null, dealership::receiveCar, "Производитель").start();
    }
}