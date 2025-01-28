// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

// Imports
//import java.io.*;
import java.util.*;
import java.util.Scanner;

public class CourseCombinations {

    public static void main(String[] args)
    {
        Scanner newScan = new Scanner(System.in);
        int numStudents = newScan.nextInt();
        newScan.nextLine();

        HashMap<String, String> mainHash = new HashMap<>();
        String[] students = new String[numStudents];
        int totalSame = 0;

        for(int i = 0; i < numStudents; i++)
        {
            students[i] = newScan.nextLine();
        }

        String[] keys = new String[numStudents];

        for(int i = 0; i < numStudents; i++)
        {
            String[] tempArray = students[i].split(" ");
            String classes = "";
            for(int j = 1; j < tempArray.length; j++)
            {
                classes += tempArray[j] + " ";
            }

            keys[i] = tempArray[0];
            mainHash.put(tempArray[0], classes);
        }

        for(int i = 0; i < mainHash.size(); i++)
        {
            String[] values = mainHash.get(keys[i]).split(" ");

            for(int j = 0; j < values.length; j++)
            {
                for(int v = i + 1; v < mainHash.size(); v++)
                {
                    if(mainHash.get(keys[v]).contains(values[j]))
                    {
                        totalSame++;
                    }
                }
            }
        }

        System.out.println(totalSame);

        newScan.close();
    }
    
}
