package ascii_art;

import image.SubImage;
import image_char_matching.SubImgCharMatcher;

/**
 * Runs a single execution of the ASCII art conversion algorithm.
 */
public class AsciiArtAlgorithm {
	private final SubImage[][] subImages;
	private final SubImgCharMatcher subImgCharMatcher;
	private final boolean reverse;

	/**
	 * Constructs AsciiArtAlgorithm.
	 * @param subImages the subImages
	 * @param subImgCharMatcher the subImgCharMatcher
	 * @param reverse the reverse
	 */
	public AsciiArtAlgorithm(SubImage[][] subImages, SubImgCharMatcher subImgCharMatcher, boolean reverse) {
		this.subImages = subImages;
		this.subImgCharMatcher = subImgCharMatcher;
		this.reverse = reverse;
	}

	/**
	 * Executes run.
	 * @return the result
	 */
	public char[][] run() {
		char[][] result = new char[subImages.length][subImages[0].length];

		for (int row = 0; row < subImages.length; row++) {
			for (int col = 0; col < subImages[row].length; col++) {
				double brightness = subImages[row][col].getBrightness();

				if (reverse) {
					brightness = 1 - brightness;
				}

				result[row][col] = subImgCharMatcher.getCharByImageBrightness(brightness);
			}
		}

		return result;
	}
}