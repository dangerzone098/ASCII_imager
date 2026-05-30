package image;

public class ImageSplitter {

    // remember that resolution is number of subImages in a row
    int resolution;
    public record Dimensions(int subsPerRow, int subsPerColumn) {}

    public ImageSplitter(int resolution){
        this.resolution = resolution;
    }

    public split(Image image){
        Dimensions d = calculateSubImageDimensions(image.getWidth(), image.getHeight());

        
    }

    private Dimensions calculateSubImageDimensions(int width, int height){
        // the size of a side of a subimage
        // for example if a subimage is 16x16 then size = 16
        int size = width / resolution;
        int subsPerColumn = height / size;
        return new Dimensions(resolution, subsPerColumn);
    }
}
