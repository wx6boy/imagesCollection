package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner in = new Scanner(System.in);
	    int size;
	    ImageCollection collection;
	    System.out.println("Input images path(example: ./images/");
	    String path = in.nextLine();
	    System.out.println(path);
	    System.out.println("Input format of images(like '.png')");
	    String format = in.nextLine();
	    collection = new ImageCollection(path, format);
	    size = collection.images.size();
	    if(size > 0){
	        while(true){
	            System.out.println("\n1 - print filenames of sorted images\n" +
                        "2 - add new image\n"+
                        "3 - calcute all the distances between 1 pic and others\n" +
                        "4 - quit");
                int flag = in.nextInt();
                switch(flag){
                    case 1:
                        collection.printFilenames();
                        break;
                    case 2:
                        System.out.println("Input a filename:");
                        String filename = in.nextLine();
                        collection.addImage(filename);
                        break;
                    case 3:
                        System.out.println("Input number of image:");
                        int number = in.nextInt();
                        if((number < 0) || (number > collection.images.size()))
                            System.out.println("Out of range");
                        else
                            collection.getAvgDistanceFromAll(number - 1);
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("There is no such command");
                        break;
                }
            }
        }
	    else
	        System.out.println("Nothing was loaded from that path");
    }
}
