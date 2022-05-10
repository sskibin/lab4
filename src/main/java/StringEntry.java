import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class StringEntry implements Callable<Integer> {

    private String string;
    private String path;
    private BlockingQueue<Results> results = new ArrayBlockingQueue<>(1024);

    public StringEntry(String string, String path, BlockingQueue<Results> results) {
        this.string = string;
        this.path = path;
        this.results = results;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        Pattern pattern = Pattern.compile(string);
        StringBuilder stringBuilder = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.next()).append(" ");
            }

            Matcher matcher = pattern.matcher(stringBuilder.toString());

            while (matcher.find()) {
                count++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        results.put(new Results(path, count));

        return count;
    }
}