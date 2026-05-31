package image_char_matching;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Matches an ASCII character to a sub-image based on brightness.
 */
public class SubImgCharMatcher {
	private final Map<Character, Double> rawBrightnessByChar;
	private final TreeMap<Double, Character> normalizedBrightnessToChar;
	private boolean normalizedTreeDirty;

	/**
	 * Constructs a SubImgCharMatcher with the given character set.
	 * @param charset the characters to use for matching.
	 */
	public SubImgCharMatcher(char[] charset) {
		this.rawBrightnessByChar = new HashMap<>();
		this.normalizedBrightnessToChar = new TreeMap<>();
		this.normalizedTreeDirty = true;

		for (char c : charset) {
			addChar(c);
		}
	}

	/**
	 * Returns the character whose brightness is closest to the given value.
	 * @param brightness a brightness value in [0,1].
	 * @return the best-matching character.
	 */
	public char getCharByImageBrightness(double brightness) {
		if (normalizedTreeDirty) {
			rebuildNormalizedTree();
		}

		Double floorKey = normalizedBrightnessToChar.floorKey(brightness);
		Double ceilingKey = normalizedBrightnessToChar.ceilingKey(brightness);

		if (floorKey == null) {
			return normalizedBrightnessToChar.get(ceilingKey);
		}

		if (ceilingKey == null) {
			return normalizedBrightnessToChar.get(floorKey);
		}

		double floorDistance = Math.abs(floorKey - brightness);
		double ceilingDistance = Math.abs(ceilingKey - brightness);

		if (floorDistance <= ceilingDistance) {
			return normalizedBrightnessToChar.get(floorKey);
		}

		return normalizedBrightnessToChar.get(ceilingKey);
	}

	/**
	 * Adds a character to the character set.
	 * @param c the character to add.
	 */
	public void addChar(char c) {
		if (rawBrightnessByChar.containsKey(c)) {
			return;
		}

		rawBrightnessByChar.put(c, charBrightnessCalculator(c));
		normalizedTreeDirty = true;
	}

	/**
	 * Removes a character from the character set.
	 * @param c the character to remove.
	 */
	public void removeChar(char c) {
		rawBrightnessByChar.remove(c);
		normalizedTreeDirty = true;
	}

	/* 
	 * Executes rebuildNormalizedTree.
	 */
	private void rebuildNormalizedTree() {
		normalizedBrightnessToChar.clear();

		double minBrightness = Double.MAX_VALUE;
		double maxBrightness = Double.MIN_VALUE;

		for (double brightness : rawBrightnessByChar.values()) {
			minBrightness = Math.min(minBrightness, brightness);
			maxBrightness = Math.max(maxBrightness, brightness);
		}

		for (Map.Entry<Character, Double> entry : rawBrightnessByChar.entrySet()) {
			char c = entry.getKey();
			double rawBrightness = entry.getValue();
			double normalizedBrightness =
					(rawBrightness - minBrightness) / (maxBrightness - minBrightness);

			insertNormalizedChar(normalizedBrightness, c);
		}

		normalizedTreeDirty = false;
	}

	/* 
	 * Executes insertNormalizedChar.
	 * @param brightness the brightness
	 * @param c the c
	 */
	private void insertNormalizedChar(double brightness, char c) {
		if (!normalizedBrightnessToChar.containsKey(brightness)) {
			normalizedBrightnessToChar.put(brightness, c);
			return;
		}

		char existingChar = normalizedBrightnessToChar.get(brightness);

		if (c < existingChar) {
			normalizedBrightnessToChar.put(brightness, c);
		}
	}

	/* 
	 * Executes charBrightnessCalculator.
	 * @param c the c
	 * @return the result
	 */
	private double charBrightnessCalculator(char c) {
		boolean[][] boolArray = CharConverter.convertToBoolArray(c);
		int arraySize = boolArray.length;
		double trueCells = 0;

		for (boolean[] row : boolArray) {
			for (boolean cell : row) {
				if (cell) {
					trueCells++;
				}
			}
		}

		return trueCells / Math.pow(arraySize, 2);
	}
}