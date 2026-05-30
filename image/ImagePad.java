package image;

import java.awt.*;


public class ImagePad {

    private final Image image;
    private final Image paddedImage;
    private final Color[][] paddedImageArray;

    public record Dimensions(int width, int height) {}

    public ImagePad(Image image){
        // TODO: test

        this.image = image;

        Dimensions newDimensions = getNewDimensions();

        this.paddedImageArray = new Color[newDimensions.height][newDimensions.width];
        this.paddedImage = new Image(paddedImageArray, newDimensions.width, newDimensions.height);
    }


    public void pad() {
        // TODO: test

        int startY = (paddedImage.getHeight() - image.getHeight()) / 2;
        int startX = (paddedImage.getWidth() - image.getWidth()) / 2;

        for (int i = 0; i < paddedImage.getHeight(); i++) {
            for (int j = 0; j < paddedImage.getWidth(); j++) {

                // Check if the curr (i, j) coordinate is inside the bounds of original image
                boolean isInsideOriginalImageY = (i >= startY && i < startY + image.getHeight());
                boolean isInsideOriginalImageX = (j >= startX && j < startX + image.getWidth());

                if (isInsideOriginalImageY && isInsideOriginalImageX) {
                    int originalY = i - startY;
                    int originalX = j - startX;
                    paddedImageArray[i][j] = image.getPixel(originalY, originalX);
                } else {
                    // make the padding cell white
                    paddedImageArray[i][j] = new Color(255, 255, 255);
                }
            }
        }
    }

    public Image getPaddedImage(){
        return paddedImage;
    }

    /**
     * makes the cells white. the cells which are trapped by the triangle whose top left cell
     * is (topX, topY) and bottom right cell is (bottomX, bottomY)
     * IT IS INCLUSIVE
     * @param topX
     * @param topY
     * @param bottomX
     * @param bottomY
     */
    private void whiteRectangle(int topY, int topX, int bottomY, int bottomX){
        for (int i = topY; i <= bottomY; i++) {
            for (int j = topX; j <= bottomX; j++) {
                paddedImageArray[i][j] = new Color(255, 255, 255); // makes it a white pixel
            }
        }
    }

    private boolean isPowOf2(int num){
        return num > 0 && Integer.bitCount(num) == 1;
    }

    private int nextPowOf2(int num){
        if (num <= 1){
            return 1;
        }
        return Integer.highestOneBit(num - 1) << 1;
    }

    private Dimensions getNewDimensions(){
        // TODO: test

        int height = image.getHeight();
        int width = image.getWidth();
        if (isPowOf2(height) && isPowOf2(width)){
            return new Dimensions(width, height);
        }
        else{
            return new Dimensions(nextPowOf2(width), nextPowOf2(height));
        }
    }







}
