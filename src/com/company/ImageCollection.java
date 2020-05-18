package com.company;

import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class ImageCollection {
    SortedSet<NewImage> images;
    private String collection_path;
    private String format;

    ImageCollection(String path, String imageFormat){
        collection_path = path;
        format = imageFormat;
        images = new TreeSet(new ImgComparator());
        readData();
    }


    public void readData(){
        File folder = new File(collection_path);
        String[] files = folder.list(new FilenameFilter() {
            @Override
            public boolean accept(File folder, String name) {
                return name.endsWith(format);
            }
        });
        try{
            for(String file: files){
                images.add(new NewImage(collection_path + file));
            }
        }
        catch (IOException exception){
            System.out.println("Not available to read images");
        }
    }


    public void addImage(String filename){
        try{
            images.add(new NewImage(filename));
        }
        catch(IOException exception){
            System.out.println("Can't load this file");
        }
    }


    public NewImage getImage(int number){
        if((number < 0) || (number > images.size())){
            System.out.println("Out of range");
            return null;
        }
        else{
            Iterator<NewImage> it = images.iterator();
            for(int i = 0; i < number; i++){
                it.next();
            }
            return it.next();
        }
    }


    public void printFilenames(){
        int i = 1;
        for(NewImage image: images){
            System.out.println(i + " - " + image.getFilename());
            i++;
        }
    }


    private double getSizeDistance(int first, int second){
        int width1 = getImage(first).getWidth();
        int height1 = getImage(first).getHeight();
        int width2 = getImage(second).getWidth();
        int height2 = getImage(second).getHeight();
        double distance;
        if( height1 > height2 )
            distance = (double)height2/height1;
        else
            distance = (double)height1/height2;
        if( width1 > width2 )
            distance += (double)width2/width1;
        else
            distance += (double)width1/width2;
        return distance/2;
    }


    private double getDateDistance(int first, int second){
        Date date1 = getImage(first).getCreationDate();
        Date date2 = getImage(second).getCreationDate();
        if( date1.before(date2))
            return  (double)date1.getTime()/date2.getTime();
        else
            return  (double)date2.getTime()/date1.getTime();
    }


    private double getRGBdistance(int first, int second){
        Color color1 = getImage(first).getAvgColor();
        Color color2 = getImage(second).getAvgColor();
        double distance;
        if(color1.getRed() > color2.getRed())
            distance = color2.getRed()/(double)color1.getRed();
        else
            distance = color1.getRed()/(double)color2.getRed();
        if(color1.getGreen() > color2.getGreen())
            distance += color2.getGreen()/(double)color1.getGreen();
        else
            distance += color1.getGreen()/(double)color2.getGreen();
        if(color1.getBlue() > color2.getBlue())
            distance += color2.getBlue()/(double)color1.getBlue();
        else
            distance += color1.getBlue()/(double)color2.getBlue();
        return distance/3;
    }


    private double getHSBdistance(int first, int second){
        Color color1 = getImage(first).getAvgColor();
        Color color2 = getImage(second).getAvgColor();
        float[] hsb1 = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), null);
        float[] hsb2 = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
        double distance;
        if(hsb1[0] > hsb2[0])
            distance = hsb2[0]/hsb1[0];
        else
            distance = hsb1[0]/hsb2[0];
        if(hsb1[1] > hsb2[1])
            distance += hsb2[1]/hsb1[1];
        else
            distance += hsb1[1]/hsb2[1];
        if(hsb1[2] > hsb2[2])
            distance += hsb2[2]/hsb1[2];
        else
            distance += hsb1[2]/hsb2[2];
        return distance/3;
    }


    public double getAverageDistance(int first, int second){
        return (getDateDistance(first,second) + getSizeDistance(first,second) + getHSBdistance(first, second) +
                getRGBdistance(first, second))/4;
    }


    public void getAvgDistanceFromAll(int number){
        System.out.println("All the distances for image " + number);
            for(int i=0; i<images.size(); i++)
                if(number != i)
                    System.out.println("File" + number + "/File" + i + "/DateDistance - " + getDateDistance(number,i)
                    + "/SizeDistance - " + getSizeDistance(number, i) + "/RGB distance - " + getRGBdistance(number, i)
                    + "/HSB distance -" + getHSBdistance(number, i) + "/avg distance - " +
                            getAverageDistance(number, i));
    }
}
