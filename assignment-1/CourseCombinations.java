// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

// Imports
//import java.io.*;
//import java.util.*;
import java.util.Scanner;

public class CourseCombinations {

    public static void main(String[] args)
    {
        Scanner newScan = new Scanner(System.in);
        int numStudents = newScan.nextInt();
        newScan.nextLine();

        //HashMap<String, String> mainHash = new HashMap<>();
        String[] students = new String[numStudents];

        for(int i = 0; i < numStudents; i++)
        {
            students[i] = newScan.nextLine();
        }

        

        newScan.close();
    }
    
}
