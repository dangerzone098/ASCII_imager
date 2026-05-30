package image_char_matching;
import com.sun.source.tree.Tree;

import java.util.Map;
import java.util.TreeMap;
/**
 * Matches an ASCII character to a sub-image based on brightness.
 */
public class SubImgCharMatcher {

    TreeMap<Double, Character> charMaxTree = new TreeMap<>();
    boolean treeNormalized = false;

    /**
     * Constructs a SubImgCharMatcher with the given character set.
     * @param charset the characters to use for matching.
     */
    public SubImgCharMatcher(char[] charset) {
        // TODO: test
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
        // TODO: test
        // normalize the tree for proper output
        if (!treeNormalized){
            NormalizeTree();
            treeNormalized = true;
        }
        // because we use a BST, the search for closest value doesn't
        // allow for direct closest key, but rather the closest
        // key which is larger, and closest which is smaller
        // therefore we take both and check which one is closer
        // to our desired value
        double closestFloor = charMaxTree.floorKey(brightness);
        double closestCeiling = charMaxTree.ceilingKey(brightness);
        double floorDistance = Math.abs(closestFloor - brightness);
        double ceilingDistance = Math.abs(closestCeiling - brightness);

        if (floorDistance < ceilingDistance){
            return charMaxTree.get(closestFloor);
        }
        else{
            return charMaxTree.get(closestCeiling);
        }

    }

    /**
     * Adds a character to the character set.
     * @param c the character to add.
     */
    public void addChar(char c) {
        // TODO: test
        double inputCharBrightness = charBrightnessCalculator(c);

        // we check if there exists a char in the tree with the exact same brightness
        // if so, we choose to keep the one with the higher ASCII value
        if (charMaxTree.containsKey(inputCharBrightness)) {
            if (charMaxTree.get(inputCharBrightness) < c){
                // insert the new node
                charMaxTree.put(inputCharBrightness, c);
            }
        }
        else{
            charMaxTree.put(inputCharBrightness, c);
        }
    }

    /**
     * Removes a character from the character set.
     * @param c the character to remove.
     */
    public void removeChar(char c) {
        // TODO: test
        double inputCharBrightness = charBrightnessCalculator(c);
        // remove given node
        charMaxTree.remove(inputCharBrightness);
    }


    private void NormalizeTree(){
        int treeSize = charMaxTree.size();
        double[] brightArray = new double[treeSize];
        char[] chars = new char[treeSize];
        int i = 0;
        // get all nodes and insert in arrays
        for (Map.Entry<Double, Character> entry : charMaxTree.entrySet()) {
            Double brightness = entry.getKey();
            Character c = entry.getValue();
            brightArray[i] = brightness;
            chars[i] = c;
            i++;
        }
        // normalize the array
        NormalizeBrightnessArray(brightArray);
        // remove all entries
        charMaxTree.clear();

        // insert the normalized nodes back into the tree
        for (int j = 0; j < treeSize; j++) {
            charMaxTree.put(brightArray[j], chars[j]);
        }
    }

    private void NormalizeBrightnessArray(double[] brightnessArray){
        // TODO: test
        // get minimum and maximum brightness values in O(1)
        // using the tree (which is sorted)
        double maxBright = charMaxTree.lastKey();
        double minBright = charMaxTree.firstKey();

        // we are allowed to assume maxBright != minBright so
        // division by 0 is impossible
        for (int i = 0; i < brightnessArray.length; i++) {
            brightnessArray[i] = (brightnessArray[i] - minBright) / (maxBright - minBright);
        }
    }

    private double charBrightnessCalculator(char c){
        // TODO: test
        boolean[][] boolArray = CharConverter.convertToBoolArray(c);
        int arraySize = boolArray.length;
        double whiteCells = 0;

        // find number of white cells (true cells)
        for (boolean[] row : boolArray){
            for (boolean cell: row){
                if (cell){
                    whiteCells++;
                }
            }
        }
        // normalize the whiteCells value
        whiteCells = whiteCells / (Math.pow(arraySize, 2));
        return whiteCells;
    }
}
