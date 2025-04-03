// Author: Dylan McIntee
// Course: CS2
// Semester: Spring 2025

public class TreasureHunt {

    public int findMinRiskRecursive(int[][] grid, int row, int col) {

        int totalRow = grid.length;
        int totalCol = grid[0].length;

        if (col >= totalCol || row >= totalRow){

            return Integer.MAX_VALUE;

        } 

        if (col == totalCol - 1 && row == totalRow - 1){

            return grid[row][col];

        } 

        int riskOfRightStep = findMinRiskRecursive(grid, row, col + 1);
        int riskOfDownStep = findMinRiskRecursive(grid, row + 1, col);

        return grid[row][col] + Math.min(riskOfRightStep, riskOfDownStep);

    }

    public int findMinRiskMemoization(int[][] grid, int row, int col, int[][] memo) {

        int totalRow = grid.length;
        int totalCol = grid[0].length;

        if (row >= totalRow || col >= totalCol){

            return Integer.MAX_VALUE;

        } 

        if (row == totalRow - 1 && col == totalCol - 1){

            return grid[row][col];

        } 

        if (memo[row][col] != -1){

            return memo[row][col];

        } 

        int riskOfRightStep = findMinRiskMemoization(grid, row, col + 1, memo);
        int riskOfDownStep = findMinRiskMemoization(grid, row + 1, col, memo);

        memo[row][col] = grid[row][col] + Math.min(riskOfRightStep, riskOfDownStep);
        return memo[row][col];

    }

    public int findMinRiskTabulation(int[][] grid) {

        int totalRow = grid.length;
        int totalCol = grid[0].length;

        int[][] minRisk = new int[totalRow][totalCol];
        minRisk[0][0] = grid[0][0];

        for (int i = 1; i < totalRow; i++){

            minRisk[i][0] = minRisk[i - 1][0] + grid[i][0];

        }

        for (int j = 1; j < totalCol; j++){

            minRisk[0][j] = minRisk[0][j - 1] + grid[0][j];

        }

        for (int i = 1; i < totalRow; i++) {

            for (int j = 1; j < totalCol; j++) {

                minRisk[i][j] = grid[i][j] + Math.min(minRisk[i - 1][j], minRisk[i][j - 1]);

            }

        }

        return minRisk[totalRow - 1][totalCol - 1];

    }
}
