package ascii_art;

import image.Image;
import image.SubImage;
import image.ImageProcessor;
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
    private boolean reverse;

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
        ImageProcessor imageProcessor = new ImageProcessor(image, resolution);
        Image padded = imageProcessor.getPaddedImage();
        SubImage[][] subImages = imageProcessor.splitToSubImages(padded);
        
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

    public void setReverse(boolean rev) {
        reverse = rev;
    }

}
