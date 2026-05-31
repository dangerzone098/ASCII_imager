## NOTE FOR UML DIAGRAM ##
method and field signatures are public unless written private


for storing the char brightness table, we will use a BST tree,
ordered based on brightness value as a tree
the tree will have key-value pairs:
    - int key = brightness value
    - char value = char itself

this way we get O(log(n)) insertion, removal and search.
which is a good balance between the three.



UML

classDiagram
    %% Package: ascii_art
    class AsciiArtAlgorithm {
        +AsciiArtAlgorithm(SubImage[][] subImages, SubImgCharMatcher subImgCharMatcher, boolean reverse)
        +run() char[][]
    }
    class Shell {
        +Shell()
        +run(String imageName) void
        +main(String[] args) void$
    }

    %% Package: image_char_matching
    class CharConverter {
        +convertToBoolArray(char c) boolean[][]$
    }
    class SubImgCharMatcher {
        +SubImgCharMatcher(char[] charset)
        +getCharByImageBrightness(double brightness) char
        +addChar(char c) void
        +removeChar(char c) void
    }

    %% Package: image
    class Image {
        +Image(String filename)
        +Image(Color[][] pixelArray, int width, int height)
        +getWidth() int
        +getHeight() int
        +getPixel(int row, int column) Color
        +getPixelArray() Color[][]
        +saveImage(String fileName) void
    }
    class SubImage {
        +SubImage(String filename)
        +SubImage(Color[][] pixelArray, int width, int height)
        +getBrightness() double
    }
    class ImagePad {
        +ImagePad(Image image)
        +pad() void
        +getPaddedImage() Image
    }
    class ImageSplitter {
        +ImageSplitter(int resolution)
        +split(Image image) SubImage[][]
    }
    class ImageProcessor {
        +ImageProcessor(Image image, int resolution)
        +getPaddedImage() Image
        +splitToSubImages(Image image) SubImage[][]
    }

    %% Inheritance
    SubImage --|> Image : Extends

    %% Dependencies and Associations
    AsciiArtAlgorithm ..> SubImage : Uses
    AsciiArtAlgorithm ..> SubImgCharMatcher : Uses

    Shell ..> Image : Uses
    Shell ..> ImageProcessor : Uses
    Shell ..> SubImage : Uses
    Shell ..> SubImgCharMatcher : Uses
    Shell ..> AsciiArtAlgorithm : Creates

    ImageProcessor ..> ImagePad : Uses
    ImageProcessor ..> ImageSplitter : Uses
    ImageProcessor ..> Image : Uses
    ImageProcessor ..> SubImage : Creates

    ImagePad ..> Image : Uses
    ImageSplitter ..> Image : Uses
    ImageSplitter ..> SubImage : Creates

    SubImgCharMatcher ..> CharConverter : Uses
