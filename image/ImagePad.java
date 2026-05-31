package image;

import java.awt.*;


public class ImagePad {

    private final Image image;
    private final Color[][] paddedImageArray;

    public record Dimensions(int width, int height) {}

    public ImagePad(Image image){
        // TODO: test

        this.image = image;

        Dimensions newDimensions = getNewDimensions();

        this.paddedImageArray = new Color[newDimensions.height][newDimensions.width];
    }


        public Image pad() {
        int startY = (paddedImageArray.length - image.getHeight()) / 2;
        int startX = (paddedImageArray[0].length - image.getWidth()) / 2;

        for (int i = 0; i < paddedImageArray.length; i++) {
            for (int j = 0; j < paddedImageArray[i].length; j++) {

                boolean isInsideOriginalImageY = i >= startY && i < startY + image.getHeight();
                boolean isInsideOriginalImageX = j >= startX && j < startX + image.getWidth();
                if (isInsideOriginalImageY && isInsideOriginalImageX) {
                    int originalY = i - startY;
                    int originalX = j - startX;
                    paddedImageArray[i][j] = image.getPixel(originalY, originalX);
                } else {
                    paddedImageArray[i][j] = Color.WHITE;
                }
            }
        }

        return new Image(paddedImageArray, paddedImageArray[0].length, paddedImageArray.length);
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
