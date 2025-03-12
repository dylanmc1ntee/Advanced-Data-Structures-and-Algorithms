// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Arrays;
import java.util.Collections;

public class GreedyRobots {
    
    int[] maxTravelRange;
    int[] distanceFromDispatch;
    int numDelivered = 0;
    int numLeftEmpty = 0;

    public GreedyRobots(int numAvailable, int numThatNeedDelivery, String file_maxTravelRange, String file_distanceOfBuildings){

        int numRobots = numAvailable;
        int numBuildings = numThatNeedDelivery;
        String file_maxTravel = file_maxTravelRange;
        String file_buildingDistance = file_distanceOfBuildings;

        maxTravelRange = new int[numRobots];
        distanceFromDispatch = new int[numBuildings];

        readFiles(file_maxTravel, file_buildingDistance);

    }

    public void readFiles(String file_maxTravel, String file_buildingDistance){

        Scanner scan1 = new Scanner(file_maxTravel);
        int i = 0;

        while (scan1.hasNextLine()){
            
            String data = scan1.nextLine();
            maxTravelRange[i] = Integer.parseInt(data);
            i++;
            
        }

        scan1.close();

        Scanner scan2 = new Scanner(file_buildingDistance);
        i = 0;

        while (scan2.hasNextLine()){
            
            String data = scan2.nextLine();
            distanceFromDispatch[i] = Integer.parseInt(data);
            i++;
            
        }

        scan2.close();

        Arrays.sort(distanceFromDispatch);
        reverseList(maxTravelRange);

    }

    private void reverseList(int[] arr){

        for(int i = 0; i < arr.length; i++){
            arr[i] = arr[i] * -1;
        }

        Arrays.sort(maxTravelRange);

        for(int i = 0; i < arr.length; i++){
            arr[i] = arr[i] * -1;
        }

    }

    public void assignDeliveries(){

    }

    public void displayResults(){

        System.out.println("Successful Deliveries: " + numDelivered);
        System.out.println("Unserved Buildings: " + numLeftEmpty);

    }

}
