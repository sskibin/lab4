import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 */
public class EntryFinder {

    public static void main(String[] args) {

        String seeking = "Станислав Скибин";
        String[] files = {"123.txt",
                "234.txt"};
        List<Future<Integer>> futures = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        final ExecutorService service = Executors.newFixedThreadPool(3);
        BlockingQueue<Results> results = new ArrayBlockingQueue<>(1024);


        try {
            service.submit(new ShowResults(results, files.length));
            for (int i = 0; i < files.length; i++) {
                String path = files[i];
                futures.add(service.submit(new StringEntry(seeking, path, results)));
            }

            for (Future<Integer> future : futures) {
                result.add(future.get());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
        }

        int amount = 0;
        for (Integer i : result) {
            amount += i;
        }
        System.out.println("All entries: " + amount);
    }
}