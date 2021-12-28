import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Создаю потоки...");

        Callable<Integer> myCallable1 = new MyCallable("1");
        Callable<Integer> myCallable2 = new MyCallable("2");
        Callable<Integer> myCallable3 = new MyCallable("3");
        Callable<Integer> myCallable4 = new MyCallable("4");

        List<Callable<Integer>> listOfCallables = new ArrayList<>();
        listOfCallables.add(myCallable1);
        listOfCallables.add(myCallable2);
        listOfCallables.add(myCallable3);
        listOfCallables.add(myCallable4);

        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        final Future<Integer> thread1 = threadPool.submit(myCallable1);
        final Future<Integer> thread2 = threadPool.submit(myCallable2);
        final Future<Integer> thread3 = threadPool.submit(myCallable3);
        final Future<Integer> thread4 = threadPool.submit(myCallable4);
        Integer result = thread1.get() + thread2.get() + thread3.get() + thread4.get();
        System.out.println("Итого сообщений: " + result);

        List<Future<Integer>> futList = threadPool.invokeAll(listOfCallables);
        result = 0;
        for (Future<Integer> i : futList) {
            result += i.get();
        }
        System.out.println("Итого сообщений: " + result);

        result = threadPool.invokeAny(listOfCallables);
        System.out.println("Минимальный результат: " + result);

        System.out.println("Завершаю все потоки...");
        threadPool.shutdown();
    }
}
