// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

import java.util.Scanner; // Import the Scanner class to read text files
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class GreedyRobots {
    
    int[] maxTravelRange;
    int[] distanceFromDispatch;
    int numDelivered = 0;
    int numLeftEmpty = 0;

    public GreedyRobots(int numAvailable, int numThatNeedDelivery, String file_maxTravelRange, String file_distanceOfBuildings){

        int numRobots = numAvailable;
        int numBuildings = numThatNeedDelivery;
        numLeftEmpty = numThatNeedDelivery;
        String file_maxTravel = file_maxTravelRange;
        String file_buildingDistance = file_distanceOfBuildings;

        maxTravelRange = new int[numRobots];
        distanceFromDispatch = new int[numBuildings];

        readFiles(file_maxTravel, file_buildingDistance);

    }

    public void readFiles(String file_maxTravel, String file_buildingDistance) {

        try {

            // Corrected file reading
            Scanner scan1 = new Scanner(new File(file_maxTravel)); // Open file properly
            int i = 0;
            while (scan1.hasNextInt()) { // Read integers correctly

                maxTravelRange[i++] = scan1.nextInt();

            }

            scan1.close();

            Scanner scan2 = new Scanner(new File(file_buildingDistance)); // Open file properly
            i = 0;

            while (scan2.hasNextInt()) { // Read integers correctly

                distanceFromDispatch[i++] = scan2.nextInt();

            }

            scan2.close();

            Arrays.sort(distanceFromDispatch);
            reverseList(maxTravelRange);

        } catch (FileNotFoundException e) {

            System.out.println("Error: File not found - " + e.getMessage());
            e.printStackTrace();
            
        }

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

        int buildingsDelivered = 0;

        for (int i = 0; i < maxTravelRange.length && buildingsDelivered < distanceFromDispatch.length; i++){

            int rangeLeft = maxTravelRange[i];

            while(buildingsDelivered < distanceFromDispatch.length && rangeLeft >= distanceFromDispatch[buildingsDelivered]){

                rangeLeft -= distanceFromDispatch[buildingsDelivered];
                numDelivered++;
                buildingsDelivered++;
                numLeftEmpty--;

            }
        }

    }

    public void displayResults(){

        System.out.println("Successful Deliveries: " + numDelivered);
        System.out.println("Unserved Buildings: " + numLeftEmpty);

    }

}
