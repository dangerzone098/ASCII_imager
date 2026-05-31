package ascii_art;

import ascii_art.errors.ExceededBoundariesException;
import ascii_art.errors.UndersizedCharsetException;
import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import image.Image;
import image.ImageProcessor;
import image.SubImage;
import image_char_matching.SubImgCharMatcher;

import java.util.TreeSet;

public class AsciiArtState {
    private static final int DEFAULT_RESOLUTION = 2;
    private static final String DEFAULT_CHARS = "0123456789";
    private static final String HTML_OUTPUT_FILE = "html.out";
    private static final String HTML_FONT = "New Courier";
    private static final int MIN_ASCII = 32;
    private static final int MAX_ASCII = 126;

    private final Image image;
    private int resolution;
    private boolean reverse;
    private AsciiOutput output;
    private final TreeSet<Character> charset;
    private final SubImgCharMatcher subImgCharMatcher;

    private ImageProcessor imageProcessor;
    private Image paddedImage;
    private SubImage[][] cachedSubImages;
    private int cachedResolution;

    public AsciiArtState(Image image) {
        this.image = image;
        this.resolution = DEFAULT_RESOLUTION;
        this.reverse = false;
        this.output = new ConsoleAsciiOutput();

        this.charset = new TreeSet<>();
        for (char c : DEFAULT_CHARS.toCharArray()) {
            charset.add(c);
        }

        this.subImgCharMatcher = new SubImgCharMatcher(toCharArray());

        this.imageProcessor = new ImageProcessor(image, resolution);
        this.paddedImage = imageProcessor.getPaddedImage();
        this.cachedSubImages = null;
        this.cachedResolution = -1;
    }

    public void printChars() {
        for (char c : charset) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    public boolean addChars(String arg) {
        return applyCharCommand(arg, true);
    }

    public boolean removeChars(String arg) {
        return applyCharCommand(arg, false);
    }

    public void printResolution() {
        System.out.println("Resolution set to " + resolution + ".");
    }

    public boolean changeResolution(String direction) throws ExceededBoundariesException {
        int newResolution;

        if (direction.equals("up")) {
            newResolution = resolution * 2;
        } else if (direction.equals("down")) {
            newResolution = resolution / 2;
        } else {
            return false;
        }

        int minResolution = Math.max(1, paddedImage.getWidth() / paddedImage.getHeight());
        int maxResolution = paddedImage.getWidth();

        if (newResolution < minResolution || newResolution > maxResolution) {
            throw new ExceededBoundariesException
                    ("Did not change resolution due to exceeding boundaries.");
        }

        resolution = newResolution;
        cachedSubImages = null;
        cachedResolution = -1;

        return true;
    }

    public void setConsoleOutput() {
        output = new ConsoleAsciiOutput();
    }

    public void setHtmlOutput() {
        output = new HtmlAsciiOutput(HTML_OUTPUT_FILE, HTML_FONT);
    }

    public void toggleReverse() {
        reverse = !reverse;
    }

    public void runAsciiArt() throws UndersizedCharsetException {
        if (charset.size() < 2) {
            throw new UndersizedCharsetException("Did not execute. Charset is too small.");
        }

        SubImage[][] subImages = getSubImages();
        AsciiArtAlgorithm algorithm = new AsciiArtAlgorithm(subImages, subImgCharMatcher, reverse);

        char[][] result = algorithm.run();
        output.out(result);
    }

    private SubImage[][] getSubImages() {
        if (cachedSubImages == null || cachedResolution != resolution) {
            imageProcessor = new ImageProcessor(image, resolution);
            paddedImage = imageProcessor.getPaddedImage();
            cachedSubImages = imageProcessor.splitToSubImages(paddedImage);
            cachedResolution = resolution;
        }

        return cachedSubImages;
    }

    private boolean applyCharCommand(String arg, boolean add) {
        if (arg.equals("all")) {
            for (char c = MIN_ASCII; c <= MAX_ASCII; c++) {
                updateCharset(c, add);
            }
            return true;
        }

        if (arg.equals("space")) {
            updateCharset(' ', add);
            return true;
        }

        if (arg.length() == 1) {
            char c = arg.charAt(0);

            if (!isValidAscii(c)) {
                return false;
            }

            updateCharset(c, add);
            return true;
        }

        if (arg.length() == 3 && arg.charAt(1) == '-') {
            char from = arg.charAt(0);
            char to = arg.charAt(2);

            if (!isValidAscii(from) || !isValidAscii(to)) {
                return false;
            }

            char start = (char) Math.min(from, to);
            char end = (char) Math.max(from, to);

            for (char c = start; c <= end; c++) {
                updateCharset(c, add);
            }

            return true;
        }

        return false;
    }

    private void updateCharset(char c, boolean add) {
        if (add) {
            charset.add(c);
            subImgCharMatcher.addChar(c);
        } else {
            charset.remove(c);
            subImgCharMatcher.removeChar(c);
        }
    }

    private boolean isValidAscii(char c) {
        return c >= MIN_ASCII && c <= MAX_ASCII;
    }

    private char[] toCharArray() {
        char[] chars = new char[charset.size()];
        int i = 0;

        for (char c : charset) {
            chars[i] = c;
            i++;
        }

        return chars;
    }
}