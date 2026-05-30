package image;

import java.awt.*;


public class ImagePad {

    private final Image image;
    private final Image paddedImage;

    public record Dimensions(int width, int height) {}

    public void pad(){

    }

    public ImagePad(Image image){
        // TODO: test

        this.image = image;

        Dimensions newDimensions = getNewDimensions();

        Color[][] paddedImageArray = new Color[newDimensions.height][newDimensions.width];
        this.paddedImage = new Image(paddedImageArray, newDimensions.width, newDimensions.height);
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
