// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

import java.util.ArrayList;
import java.util.List;

public class ParenthesesCombinations{

    int numOpen = 0; // number of open brackets in stringbuilder
    int numClose = 0; // number of closed brackets in stringbuilder

    public ParenthesesCombinations(){ // constructor
        // here
    }

    public List<String> generateParentheses(int n){

        ArrayList<String> solutions = new ArrayList<String>(); // creates new arraylist to hold solutions
        StringBuilder newSolution = new StringBuilder(); // creates new stringbuilder to build solutions

        backtrackToFindCombinations(solutions, newSolution, numOpen, numClose, n); // passes the arraylist and stringbuilder into the helper
        
        return solutions; // returns arraylist full of all solutions
    }

    private void backtrackToFindCombinations(ArrayList<String> solutions, StringBuilder newCombination, int numO, int numC, int n){

        if(numO == n && numC == n){ // if the string is at max length

            String answer = newCombination.toString(); // creates a string of the solution
            solutions.add(answer); // puts solution into solutions list

            return; // get out
        }

        if(numO < n){ // if we can add an open bracket
            newCombination.append("("); // adds open bracket to stringbuilder

            backtrackToFindCombinations(solutions, newCombination, numO + 1, numC, n); // recurse

            newCombination.deleteCharAt(newCombination.length() - 1); // backtrack portion
        }

        if(numC < numO){ // if we have less closed brackets than open
            newCombination.append(")"); // adds closed bracket to stringbuilder

            backtrackToFindCombinations(solutions, newCombination, numO, numC + 1, n); // recurse

            newCombination.deleteCharAt(newCombination.length() - 1); // backtrack portion
        }
    }
}