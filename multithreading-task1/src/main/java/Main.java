public class Main {

    public static void main(String[] args) {

        ThreadGroup mainGroup = new ThreadGroup("Main group");
        System.out.println("Создаю потоки...");
        final Thread thread1 = new MyThread(mainGroup, "1");
        final Thread thread2 = new MyThread(mainGroup, "2");
        final Thread thread3 = new MyThread(mainGroup, "3");
        final Thread thread4 = new MyThread(mainGroup, "4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Завершаю все потоки...");
        mainGroup.interrupt();
    }
}
