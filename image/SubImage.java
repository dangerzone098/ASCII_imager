package image;

import java.awt.*;
import java.io.IOException;

public class SubImage extends Image{

    private final double brightness;

    public SubImage(String filename) throws IOException {
        super(filename);
        this.brightness = calculateBrightness();
    }

    public SubImage(Color[][] pixelArray, int width, int height){
        super(pixelArray, width, height);
        this.brightness = calculateBrightness();
    }

    public double getBrightness(){
        return brightness;
    }

    private double calculateBrightness(){
        Color color;
        double graySum = 0, grayPixel;
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                color = getPixel(i, j);
                grayPixel = color.getRed() * 0.2126 + color.getGreen() * 0.7152 + color.getBlue() * 0.0722;
                graySum += grayPixel;
            }
        }
        // normalizing over all pixels and over gray limit 255
        return graySum / (getWidth() * getHeight()) / 255;
    }
}
