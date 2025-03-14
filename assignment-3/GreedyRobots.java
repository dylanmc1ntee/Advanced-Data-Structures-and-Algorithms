// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

import java.util.Scanner; // scanner import
import java.util.Arrays; // arrays import
import java.io.File; // file import
import java.io.FileNotFoundException; // error handling import

public class GreedyRobots {
    
    int[] maxTravelRange; // array to hold robots
    int[] distanceFromDispatch; // array to hold distances
    int numDelivered = 0; // number of buildings delivered
    int numLeftEmpty = 0; // number of buildings left empty handed

    public GreedyRobots(int numAvailable, int numThatNeedDelivery, String file_maxTravelRange, String file_distanceOfBuildings){

        int numRobots = numAvailable; // number of robots
        int numBuildings = numThatNeedDelivery; // number of buildings
        numLeftEmpty = numThatNeedDelivery; // sets buildings left to deliver to initial number of buildings
        String file_maxTravel = file_maxTravelRange; // stores file name of robots
        String file_buildingDistance = file_distanceOfBuildings; // stores file name of buildings

        maxTravelRange = new int[numRobots]; // creates array
        distanceFromDispatch = new int[numBuildings]; // creates array

        readFiles(file_maxTravel, file_buildingDistance); // pass files into function

    }

    public void readFiles(String file_maxTravel, String file_buildingDistance){

        try {

            Scanner scan1 = new Scanner(new File(file_maxTravel)); // opens file in scanner
            int i = 0;

            while (scan1.hasNextInt()){ // while not at the end of file

                maxTravelRange[i] = scan1.nextInt(); // populate array
                i++;

            }

            scan1.close(); // close scanner

            Scanner scan2 = new Scanner(new File(file_buildingDistance)); // opens file in scanner
            i = 0;

            while (scan2.hasNextInt()) { // while not at the end of file

                distanceFromDispatch[i] = scan2.nextInt(); // populate array
                i++;

            }

            scan2.close(); // close scanner

            Arrays.sort(distanceFromDispatch); // sort building array from least to greatest
            reverseList(maxTravelRange); // pass array into reverse sort function

        } catch (FileNotFoundException e){ // catch error

            e.printStackTrace(); // print errors

        }

    }

    private void reverseList(int[] arr){

        for(int i = 0; i < arr.length; i++){ // for the length of the array
            arr[i] = arr[i] * -1; // multiply all by -1 so the greatest number becomes the least
        }

        Arrays.sort(maxTravelRange); // sort from least to greatest

        for(int i = 0; i < arr.length; i++){ // for the length of the array
            arr[i] = arr[i] * -1; // multiply by -1 to get origional array back
        }

    }

    public void assignDeliveries(){

        int buildingsDelivered = 0; // buildings that have been delivered in this run

        // for every robot && we have not been to every single building
        for (int i = 0; i < maxTravelRange.length && buildingsDelivered < distanceFromDispatch.length; i++){

            int rangeLeft = maxTravelRange[i]; // amount of gas the current robot has

            // while we have not been to every building && we have enough gas to go to the next one
            while(buildingsDelivered < distanceFromDispatch.length && rangeLeft >= distanceFromDispatch[buildingsDelivered]){

                rangeLeft -= distanceFromDispatch[buildingsDelivered]; // subtract the current fuel
                numDelivered++; // add 1 to the delivered counter
                buildingsDelivered++; // add 1 to the counter for this run
                numLeftEmpty--; // subtract 1 from the buildings left empty

            }
        }

    }

    public void displayResults(){

        System.out.println("Successful Deliveries: " + numDelivered); // print succesful deliveries
        System.out.println("Unserved Buildings: " + numLeftEmpty); // print buildings left empty

    }

}
