package image;

import java.awt.*;

/**
 * Represents the ImageSplitter.
 * @author username1, username2
 */
public class ImageSplitter {

	// remember that resolution is number of subImages in a row
	private int resolution;

    /**
     * dimension vector
     * @param subsPerRow
     * @param subsPerColumn
     */
	public record Dimensions(int subsPerRow, int subsPerColumn) {}

	/**
	 * Constructs ImageSplitter.
	 * @param resolution the resolution
	 */
	public ImageSplitter(int resolution){
		this.resolution = resolution;
	}

	/**
	 * Executes split.
	 * @param image the image
	 * @return the result
	 */
	public SubImage[][] split(Image image){
		Dimensions d = calculateSubImageDimensions(image.getWidth(), image.getHeight());
		SubImage[][] subImages = new SubImage[d.subsPerColumn][d.subsPerRow];
		int subImageSize = image.getWidth() / resolution;

		Color[][] original = image.getPixelArray();
		int totalRows = original.length;
		int totalCols = original[0].length;

		int subImagesRow = 0;

		// jump by chunkSize horizontally and vertically
		for (int r = 0; r < totalRows; r += subImageSize, subImagesRow++) {

			int subImagesColumn = 0;

			for (int c = 0; c < totalCols; c += subImageSize, subImagesColumn++) {

				Color[][] chunk = new Color[subImageSize][subImageSize];

				for (int i = 0; i < subImageSize; i++) {
					System.arraycopy(original[r + i], c, chunk[i], 0, subImageSize);
				}
				SubImage subImage = new SubImage(chunk, subImageSize, subImageSize);
				subImages[subImagesRow][subImagesColumn] = subImage;
			}
		}
		return subImages;
	}


	/* 
	 * Executes calculateSubImageDimensions.
	 * @param width the width
	 * @param height the height
	 * @return the result
	 */
	private Dimensions calculateSubImageDimensions(int width, int height){
		// the size of a side of a subimage
		// for example if a subimage is 16x16 then size = 16
		int size = width / resolution;
		int subsPerColumn = height / size;
		return new Dimensions(resolution, subsPerColumn);
	}
}
