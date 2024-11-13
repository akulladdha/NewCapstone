import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        List<Future<Integer>> futures = new ArrayList<>();

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
            futures.add(service.submit(task));
        }


        for (Future<Integer> f : futures) {

            Integer result = f.get();
            System.out.println(result);
        }


        service.shutdown();
    }
}
