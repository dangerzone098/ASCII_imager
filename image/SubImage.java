package image;

import java.awt.*;
import java.io.IOException;

public class SubImage extends Image{

	private static final double RED_WEIGHT = 0.2126;
	private static final double GREEN_WEIGHT = 0.7152;
	private static final double BLUE_WEIGHT = 0.0722;
	private static final int MAX_RGB_VALUE = 255;

	private final double brightness;

	/**
	 * Constructs SubImage.
	 * @param filename the filename
	 * @throws IOException if an error occurs
	 */
	public SubImage(String filename) throws IOException {
		super(filename);
		this.brightness = calculateBrightness();
	}

	/**
	 * Constructs SubImage.
	 * @param pixelArray the pixelArray
	 * @param width the width
	 * @param height the height
	 */
	public SubImage(Color[][] pixelArray, int width, int height){
		super(pixelArray, width, height);
		this.brightness = calculateBrightness();
	}

	/**
	 * Gets brightness.
	 * @return the result
	 */
	public double getBrightness(){
		return brightness;
	}

	/* 
	 * Calculates the brightness.
	 * @return the result
	 */
	private double calculateBrightness(){
		Color color;
		double graySum = 0, grayPixel;
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				color = getPixel(i, j);
				grayPixel = color.getRed() * RED_WEIGHT + color.getGreen() * GREEN_WEIGHT + color.getBlue() * BLUE_WEIGHT;
				graySum += grayPixel;
			}
		}
		// normalizing over all pixels and over gray limit MAX_RGB_VALUE
		return graySum / (getWidth() * getHeight()) / MAX_RGB_VALUE;
	}
}
