package image;

/**
 * Represents the ImageProcessor.
 * @author username1, username2
 */
public class ImageProcessor {

	private final ImagePad imagePadder;
	private final ImageSplitter imageSplitter;
	private final Image image;

	/**
	 * Constructs ImageProcessor.
	 * @param image the image
	 * @param resolution the resolution
	 */
	public ImageProcessor(Image image, int resolution){

		this.image = image;
		this.imagePadder = new ImagePad(image);
		this.imageSplitter = new ImageSplitter(resolution);
	}

	/**
	 * Gets paddedimage.
	 * @return the result
	 */
	public Image getPaddedImage(){
		return imagePadder.pad();
	}

	/**
	 * Executes splitToSubImages.
	 * @param image the image
	 * @return the result
	 */
	public SubImage[][] splitToSubImages(Image image){
		return imageSplitter.split(image);
	}
}
