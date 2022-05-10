public class Results {
    private String filename;
    private int count;

    public Results(String filename, int count) {
        this.filename = filename;
        this.count = count;
    }

    @Override
    public String toString() {
        return "Results{" +
                "filename='" + filename + '\'' +
                ", count=" + count +
                '}';
    }
}