// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

// imports
import java.util.*;
import java.util.Scanner;

public class CourseCombinations {

    public static void main(String[] args)
    {
        Scanner newScan = new Scanner(System.in); // create scanner
        int numStudents = newScan.nextInt(); // get number of students
        newScan.nextLine(); // eat the newline character

        HashMap<String, String> mainHash = new HashMap<>(); // create hash map
        String[] students = new String[numStudents]; // create string that holds students and their classes
        int totalSame = 0; // counts how many share a class

        // populates the array of students and their classes
        for(int i = 0; i < numStudents; i++) // for the number of students
        {
            students[i] = newScan.nextLine(); // get name and classes
        }

        String[] keys = new String[numStudents]; // creates array that holds students first names

        // puts the keys (names) into hashmap
        // and their corresponding values (classes)
        for(int i = 0; i < numStudents; i++) // for the number of students
        {
            String[] tempArray = students[i].split(" "); // breaks string into mulitple strings
            String classes = ""; // this will hold the classes each person takes
            for(int j = 1; j < tempArray.length; j++) // for the number of classes
            {
                classes += tempArray[j] + " "; // adds class to classes string
            }

            keys[i] = tempArray[0]; // adds the first name to the keys array
            mainHash.put(tempArray[0], classes); // add to hashmap
        }

        // searches for each class shared between the students
        for(int i = 0; i < mainHash.size(); i++) // for the number of students
        {
            String[] values = mainHash.get(keys[i]).split(" "); // holds each class that the i student takes

            for(int j = 0; j < values.length; j++) // for the number of classes
            {
                for(int v = i + 1; v < mainHash.size(); v++) // for the number of students that need to be checked
                {
                    if(mainHash.get(keys[v]).contains(values[j])) // if there is a shared class
                    {
                        totalSame++; // add 1
                    }
                }
            }
        }

        System.out.println(totalSame); // print total number of classes shared

        newScan.close(); // closes scanner
    }
    
}
