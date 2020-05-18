package com.company;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.nio.file.Path;


public class NewImage {
    private int width;
    private int height;
    private String filename;
    private Color avgColor;
    public Date creationDate;

    public NewImage(String file)throws  IOException{
        filename = file;
        BufferedImage image = ImageIO.read(new File(filename));
        width = image.getWidth();
        height = image.getHeight();
        avgColor = AverageColor(image);
        creationDate = getDate(filename);
    }


    private Color AverageColor(BufferedImage image){
        int sum_r = 0, sum_g = 0, sum_b = 0;
        for(int x = 0; x < image.getWidth(); x++)
            for(int y = 0; y < image.getHeight(); y++){
                Color pixel = new Color(image.getRGB(x,y));
                sum_r += pixel.getRed();
                sum_g += pixel.getGreen();
                sum_b += pixel.getBlue();
            }
        return new Color(sum_r / (image.getHeight() * image.getWidth()),
                sum_g / (image.getHeight() * image.getWidth()),
                sum_b / (image.getHeight() * image.getWidth()));
    }


    private Date getDate(String filename){
        File file = new File(filename);
        Path filePath = file.toPath();

        BasicFileAttributes attributes = null;
        try{
            attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
            return new Date(attributes.creationTime().to(TimeUnit.MILLISECONDS));
        }
        catch (IOException exception){
            System.out.println("Exception handled while trying to get a file attributes:" + exception.getMessage());
            return null;
        }
    }


    public int getWidth(){
        return width;
    }


    public int getHeight(){
        return height;
    }


    public String getFilename(){
        return filename;
    }


    public Color getAvgColor(){
        return avgColor;
    }


    public Date getCreationDate(){
        return creationDate;
    }
}
