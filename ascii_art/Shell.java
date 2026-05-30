package ascii_art;

import image.Image;

import java.io.IOException;
import java.util.TreeSet;

import ascii_output.AsciiOutput;
import ascii_output.ConsoleAsciiOutput;
import image_char_matching.SubImgCharMatcher;
import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;
import java.io.IOException;
import java.util.TreeSet;

/**
 * Interactive command-line interface for the ASCII art application.
 */
public class Shell {

    /**
     * Constructs a new Shell with default settings.
     */
    private static final int DEFAULT_RESOLUTION = 2;
    private static final String DEFAULT_CHARS = "0123456789";
    private static final String PROMPT = ">>> ";


    private int resolution;
    private SubImgCharMatcher matcher;
    private AsciiOutput output;
    private boolean reverse;
    private TreeSet<Character> charset;
    private SubImgCharMatcher subImgCharMatcher;

    public Shell() {
        this.resolution = DEFAULT_RESOLUTION;
        this.reverse = false;
        this.output = new ConsoleAsciiOutput();


        this.charset = new TreeSet<>();

        for (char c : DEFAULT_CHARS.toCharArray()) {
                charset.add(c);
        }

        this.subImgCharMatcher = new SubImgCharMatcher(toCharArray());
    }

    /**
     * Starts the interactive shell loop, reading and executing user commands.
     * @param imageName the file path of the image to convert.
     */
    public void run(String imageName) {
        Image image;

        try {
            image = new Image(imageName);
        } catch (IOException e) {
            return;
        }

        while (true) {
            System.out.print(">>> ");
            String input = KeyboardInput.readLine();
            
            if (input.isEmpty()) {
                System.out.println("Did not execute due to incorrect command.");
                continue;
            }

            String[] parts = input.split("\\s+");

            switch (parts[0]) {
                case "exit":
                    return;
                case "chars":
                    handleChars();
                    break;
                case "add":
                    handleAdd(parts);
                    break;
                case "remove":
                    handleRemove(parts);
                    break;
                case "res":
                    handleRes(parts, image);
                    break;
                case "output":
                    handleOutput(parts);
                    break;
                case "reverse":
                    handleReverse();
                    break;
                case "asciiArt":
                    handleAsciiArt(image);
                    break;
                default:
                    handleInvalidCommand();
            }
        }

    }

    /**
     * Main entry point for the application.
     * @param args command-line arguments; args[0] is the image path.
     */
    public static void main(String[] args) {
        Shell shell = new Shell();
        shell.run(args[0]);
    }



    private void handleChars() {
        for (char c : charset) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private void handleAdd(String[] parts) {
        if (parts.length < 2 || !applyCharCommand(parts[1], true)) {
            System.out.println("Did not add due to incorrect format.");
        }
    }

    private void handleRemove(String[] parts) {
        if (parts.length < 2 || !applyCharCommand(parts[1], false)) {
            System.out.println("Did not remove due to incorrect format.");
        }
    }

    private boolean applyCharCommand(String arg, boolean add) {
        if (arg.equals("all")) {
            for (char c = 32; c <= 126; c++) {
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
        return c >= 32 && c <= 126;
    }

    private void handleRes(String[] parts, Image image) {
        ImageProcessor imageProcessor = new ImageProcessor(image);
        Image padded = imageProcessor.getPaddedImage();

        int minResolution = Math.max(1, padded.getWidth() / padded.getHeight());
        int maxResolution = padded.getWidth();

        if (parts.length == 1) {
            printResolution();
            return;
        }

        int newResolution;

        if (parts[1].equals("up")) {
            newResolution = resolution * 2;
        } else if (parts[1].equals("down")) {
            newResolution = resolution / 2;
        } else {
            System.out.println("Did not change resolution due to incorrect format.");
            return;
        }

        if (newResolution < minResolution || newResolution > maxResolution) {
            System.out.println("Did not change resolution due to exceeding boundaries.");
            return;
        }

        resolution = newResolution;
        printResolution();
    }

    private void printResolution() {
        System.out.println("Resolution set to " + resolution + ".");
    }

    private void handleOutput(String[] parts) {
        if (parts.length < 2) {
            System.out.println("Did not change output method due to incorrect format.");
            return;
        }

        if (parts[1].equals("console")) {
            output = new ConsoleAsciiOutput();
        } else if (parts[1].equals("html")) {
            output = new HtmlAsciiOutput("out.html", "Courier New");
        } else {
            System.out.println("Did not change output method due to incorrect format.");
        }
    }

    private void handleReverse() {
        reverse = !reverse;
    }

    private void handleInvalidCommand() {
        System.out.println("Did not execute due to incorrect command.");
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

    private void handleAsciiArt(Image image) {
        if (charset.size() < 2) {
            System.out.println("Did not execute. Charset is too small.");
            return;
        }

        AsciiArtAlgorithm algorithm =
                new AsciiArtAlgorithm(image, resolution, subImgCharMatcher);
                
        algorithm.setReverse(reverse);

        char[][] result = algorithm.run();
        output.out(result);
    }


}