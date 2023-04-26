package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {
	private ArrayList<Word> model = new ArrayList<>();

	public void loadFile(String filename) {
		String[] lines = loadStrings(filename);

		for (String line : lines) {
			String[] words = split(line, ' ');
			for (int i = 0; i < words.length; i++) {
				String currWord = cleanWord(words[i]);
				if (currWord.length() == 0) {
					break;
				}
				Word word = findWord(currWord);
				if (word == null) {
					word = new Word(currWord);
					model.add(word);
				}
				if (i + 1 == words.length) {
					break;
				}
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
		size(1000, 1000);
		loadFile("small.txt");
		printModel();
		// fullScreen(SPAN);
	}

	String[] sonnet;

	public String[] writeSonnet() {
		return null;
	}

	public void setup() {
		colorMode(HSB);

	}

	public void keyPressed() {

	}

	float off = 0;

	public void draw() {
		background(0);
		fill(255);
		noStroke();
		textSize(20);
		textAlign(CENTER, CENTER);

	}
}
