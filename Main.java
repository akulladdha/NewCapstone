import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors();
        int x=100;
        ExecutorService service = Executors.newFixedThreadPool(x);
        List<Callable<Integer>> tasks = new ArrayList<>();
        long startTime = System.nanoTime();


        for (int i = 0; i < 1000; i++) {
            Callable<Integer> task = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int count = 0;
                    for (int i = 0; i < 1000000; i++) {
                        count++;
                    }
                    return count;
                }
            };
            tasks.add(task);
        }
        List<Future<Integer>> results = service.invokeAll(tasks);

        int result = 0;
        for (Future<Integer> f : results) {
            result += f.get();
        }
        System.out.println(result);

        service.shutdown();
        long endTime = System.nanoTime();

        long elapsedTime = endTime - startTime;
        System.out.println("Total execution time: " + elapsedTime / 1_000_000 + " milliseconds");
    }
}
