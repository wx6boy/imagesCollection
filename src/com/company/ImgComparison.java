package com.company;

import java.util.Comparator;
import java.awt.*;


class ImgComparator implements Comparator<NewImage> {
    public int compare(NewImage first, NewImage second){
        int colorCompare = colorComparison(first.getAvgColor(), second.getAvgColor());
        if(colorCompare != 0){
            return colorCompare;
        }
        else{
            if(first.getHeight()*first.getWidth() > second.getHeight()*second.getWidth())
                return 1;
            if(first.getHeight()*first.getWidth() < second.getHeight()*second.getWidth())
                return -1;
            if(first.getCreationDate().getTime() < second.getCreationDate().getTime())
                return 1;
            if(first.getCreationDate().getTime() > second.getCreationDate().getTime())
                return -1;
            return 0;
        }
    }


    private int colorComparison(Color color1, Color color2){
        float[] HSB1 = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), null);
        float[] HSB2 = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
        if(HSB1[0] > HSB2[0])
            return 1;
        if(HSB1[0] < HSB2[0])
            return -1;
        if(HSB1[1] > HSB2[1])
            return 1;
        if(HSB1[1] < HSB2[1])
            return -1;
        if(HSB1[2] > HSB2[2])
            return 1;
        if(HSB1[2] < HSB2[2])
            return -1;
        return 0;
    }
}
