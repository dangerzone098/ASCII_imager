package image;

import java.awt.*;


public class ImagePad {

    private final Image image;
    private final Image paddedImage;

    public record Dimensions(int height, int width) {}

    public void pad(){

    }

    public ImagePad(Image image){
        this.image = image;

        Dimensions newDimensions = getNewDimensions();

        Color[][] paddedImageArray = new Color[][]();
        this.paddedImage = new Image(newDimensions.height, newDimensions.width);
    }


    private Dimensions getNewDimensions(){
        int height, width;
        return new Dimensions(height, width);
    }





}
