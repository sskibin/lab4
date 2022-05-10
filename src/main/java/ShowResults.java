import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 */
public class ShowResults implements Runnable {

    private BlockingQueue<Results> results = new ArrayBlockingQueue<>(1024);
    private int numberOfFiles;

    public ShowResults(BlockingQueue<Results> results, int numberOfFiles) {
        this.results = results;
        this.numberOfFiles = numberOfFiles;
    }

    @Override
    public void run() {
        while (numberOfFiles != 0) {
            if (!results.isEmpty()) {
                try {
                    System.out.println(results.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                numberOfFiles--;
            }
        }
    }
}