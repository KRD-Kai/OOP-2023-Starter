package ie.tudublin;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> follows = new ArrayList<Follow>();;

    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public ArrayList<Follow> getFollows() {
        return follows;
    }

    public void addFollow(Follow follow) {
        follows.add(follow);
    }

    @Override
    public String toString() {
        String followStr = "";
        for (Follow follow : follows) {
            followStr += follow.toString() + " ";
        }
        return word + ": " + followStr.trim(); // remove extra space with trim
    }
}
