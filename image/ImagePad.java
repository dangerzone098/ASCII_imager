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


    public void pad(){
        // calculate dimensions difference
        int heightDiff = paddedImage.getHeight() - image.getHeight();
        int widthDiff = paddedImage.getWidth() - image.getWidth();
        Color curr;

        whiteRectangle(heightDiff / 2,
                       widthDiff / 2,
                    heightDiff / 2 + image.getHeight(),
                    widthDiff / 2 + image.getWidth());


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
    private void whiteRectangle(int topX, int topY, int bottomX, int bottomY){
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
