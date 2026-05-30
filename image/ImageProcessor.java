package image;

public class ImageProcessor {

    private ImagePad imagePadder;
    private ImageSplitter imageSplitter;
    private Image image;

    public ImageProcessor(Image image, int resolution){

        this.image = image;
        this.imagePadder = new ImagePad(image);
        this.imageSplitter = new ImageSplitter(resolution);
    }

    public Image getPaddedImage(){
        imagePadder.pad();
        return imagePadder.getPaddedImage();
    }

    public SubImage[][] splitToSubImages(Image image){
        return ImageSplitter.split(image);
    }
}
