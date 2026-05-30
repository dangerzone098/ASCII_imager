package ascii_art;

import image.Image;
import image_char_matching.SubImgCharMatcher;

/**
 * Runs a single execution of the ASCII art conversion algorithm.
 */
public class AsciiArtAlgorithm {

    /**
     * Constructs an AsciiArtAlgorithm with the given parameters.
     * @param image the source image to convert.
     * @param resolution the number of ASCII characters per row in the output.
     * @param subImgCharMatcher the matcher used to map brightness to characters.
     */
    private final Image image;
    private final int resolution;
    private final SubImgCharMatcher subImgCharMatcher;
    public AsciiArtAlgorithm(Image image, int resolution, SubImgCharMatcher subImgCharMatcher) {
        this.image = image;
        this.resolution = resolution;
        this.subImgCharMatcher = subImgCharMatcher;
    }

    /**
     * Runs the algorithm and returns the resulting ASCII art.
     * @return a 2D char array representing the ASCII art image.
     */
    public char[][] run() {
        Image padded = ImageProcessor.ImagePad(image);
        Image[][] subImages = ImageProcessor.splitToSubImages(padded, resolution);
        double brightness = ImageProcessor.calculateBrightness(subImages[row][col]);
        return null;
    }
}
