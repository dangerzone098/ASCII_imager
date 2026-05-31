package image;

public class ImageProcessor {

    private final ImagePad imagePadder;
    private final ImageSplitter imageSplitter;
    private final Image image;

    public ImageProcessor(Image image, int resolution){

        this.image = image;
        this.imagePadder = new ImagePad(image);
        this.imageSplitter = new ImageSplitter(resolution);
    }

    public Image getPaddedImage(){
        return imagePadder.pad();
    }

    public SubImage[][] splitToSubImages(Image image){
        return imageSplitter.split(image);
    }
}
