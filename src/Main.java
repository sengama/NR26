import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);

        for (int i = 0; i < processors; i++) {
            executor.submit(Main::incrementCounter);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("Contor final: " + counter.get());
    }

    private static synchronized void incrementCounter() {
        for (int i = 0; i < 1000; i++) {
            counter.incrementAndGet();
        }
    }
}
