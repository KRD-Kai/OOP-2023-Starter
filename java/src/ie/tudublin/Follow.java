package ie.tudublin;

public class Follow {
    private String word;
    private int count = 0;

    public Follow(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        count++;
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", word, count);
    }
}
