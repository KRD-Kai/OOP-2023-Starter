package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {
	private ArrayList<Word> model = new ArrayList<>();
	private String sonnetText = "";

	public void loadFile(String filename) {
		String[] lines = loadStrings(filename);

		// Iterate through all words for each line
		for (String line : lines) {
			String[] words = split(line, ' ');

			for (int i = 0; i < words.length; i++) {
				String currWord = cleanWord(words[i]);
				if (currWord.length() == 0) {
					break;
				}

				// Find/create word if it does not yet exist
				Word word = findWord(currWord);
				if (word == null) {
					word = new Word(currWord);
					model.add(word);
				}

				// end of line check
				if (i + 1 == words.length) {
					break;
				}

				// Add/increment next word to follows
				String nextWord = cleanWord(words[i + 1]);
				Follow follow = word.findFollow(nextWord);
				if (follow == null) {
					follow = new Follow(nextWord);
					word.addFollow(follow);
				}
				follow.incrementCount();
			}
		}
	}

	public String cleanWord(String s) {
		s = s.toLowerCase();
		s = s.replaceAll("[^\\w\\s]", "");
		return s;

	}

	public Word findWord(String str) {
		for (Word word : model) {
			if (word.getWord().equals(str)) {
				return word;
			}
		}
		return null;
	}

	public void printModel() {
		for (Word word : model) {
			System.out.println(word.toString());
		}
	}

	public void settings() {
		size(700, 700);
		// fullScreen(SPAN);
	}

	public String writeSonnet() {
		String sonnet = "";
		for (int i = 0; i < 14; i++) {
			// Pick a random starting word
			int index = (int) random(model.size());
			Word currWord = model.get(index);

			// Initialize sentence with starting word
			String sentence = currWord.getWord();

			// keep adding words until we have a complete sentence or reach 8 words
			for (int j = 0; j < 7; j++) {
				// Randomly pick one of the follow words
				ArrayList<Follow> follows = null;
				if (currWord != null) {
					follows = currWord.getFollows();
				}

				// End sentence if no follows
				if (follows == null || follows.isEmpty()) {
					break;
				}

				int followIndex = (int) random(follows.size());
				Follow follow = follows.get(followIndex);
				String followWord = follow.getWord();
				sentence += " " + followWord;

				currWord = findWord(followWord);
			}
			// Add completed sentence to sonnet
			sonnet += sentence + "\n";
		}
		return sonnet;
	}

	public void setup() {
		loadFile("shakespere.txt");
		colorMode(HSB);
	}

	public void keyPressed() {
		// SPACE only
		if (keyCode == 32) {
			this.sonnetText = writeSonnet();
			System.out.println(sonnetText);
		}

	}

	float off = 0;

	public void draw() {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
		textAlign(CENTER, CENTER);

		if (sonnetText.length() == 0) {
			text("Press SPACE to generate a magnificent sonnet", width / 2, height / 2);
		} else {
			text(sonnetText, width / 2, height / 2);
		}

	}
}
