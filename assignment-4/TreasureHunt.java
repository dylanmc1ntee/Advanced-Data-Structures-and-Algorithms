// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

public class TreasureHunt {

    public int findMinRiskRecursive(int[][] grid, int row, int col) {

        int totalRow = grid.length; // total amount matrix rows
        int totalCol = grid[0].length; // total amount matrix columms

        if (col >= totalCol || row >= totalRow){ // if the pointers go outside matrix

            return Integer.MAX_VALUE; // return massive number

        } 

        if (col == totalCol - 1 && row == totalRow - 1){ // if the pointers reach the end

            return grid[row][col]; // return value at position

        } 

        int riskOfRightStep = findMinRiskRecursive(grid, row, col + 1); // recurse right
        int riskOfDownStep = findMinRiskRecursive(grid, row + 1, col); // recurse down

        return grid[row][col] + Math.min(riskOfRightStep, riskOfDownStep); // return whichever path has less risk

    }

    public int findMinRiskMemoization(int[][] grid, int row, int col, int[][] memo) {

        int totalRow = grid.length; // total amount matrix rows
        int totalCol = grid[0].length; // total amount matrix columms

        if (row >= totalRow || col >= totalCol){ // if the pointers go outside matrix

            return Integer.MAX_VALUE; // return massive number

        } 

        if (row == totalRow - 1 && col == totalCol - 1){ // if the pointers reach the end

            return grid[row][col]; // return value at position

        } 

        if (memo[row][col] != -1){ // if their is already a saved result

            return memo[row][col]; // return that

        } 

        int riskOfRightStep = findMinRiskMemoization(grid, row, col + 1, memo); // recurse right
        int riskOfDownStep = findMinRiskMemoization(grid, row + 1, col, memo); // recurse down

        memo[row][col] = grid[row][col] + Math.min(riskOfRightStep, riskOfDownStep); // save in memo
        return memo[row][col]; // return path

    }

    public int findMinRiskTabulation(int[][] grid) {

        int totalRow = grid.length; // number of rows
        int totalCol = grid[0].length; // number of columns

        int[][] minRisk = new int[totalRow][totalCol]; // create 2d array the size of matrix
        minRisk[0][0] = grid[0][0]; // set the starting points equal

        for (int i = 1; i < totalRow; i++){ // go through row

            minRisk[i][0] = minRisk[i - 1][0] + grid[i][0];

        }

        for (int j = 1; j < totalCol; j++){ // go through column

            minRisk[0][j] = minRisk[0][j - 1] + grid[0][j];

        }

        for (int i = 1; i < totalRow; i++) { // for the rest of rows

            for (int j = 1; j < totalCol; j++) { // for the rest of columns

                int currentMin = Math.min(minRisk[i - 1][j], minRisk[i][j - 1]); // set to whichever risk is lesser
                minRisk[i][j] = grid[i][j] + currentMin; // save

            }

        }

        return minRisk[totalRow - 1][totalCol - 1]; // return path with least risk

    }
}
